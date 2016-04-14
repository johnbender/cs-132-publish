package analysis;

import syntaxtree.*;
import visitor.GJDepthFirst;
import java.util.HashMap;
import java.util.Vector;
import analysis.Type;
import analysis.ConstType;
import java.util.Enumeration;

public class TypeVisitor
    extends GJDepthFirst<Vector<Type>, HashMap<String, Type>> {

    Vector<Type> buildType(Type t){
       Vector<Type> typeVec = new Vector<Type>();
       typeVec.add(t);
       return typeVec;
    }

    Vector<Type> buildType(ConstType t){
        return buildType(new Type(t));
    }

    /**
     * f0 -> Expr()
     * f1 -> <EOF>
     */
    public Vector<Type> visit(Prog n, HashMap env) {
        return n.f0.accept(this, env);
    }

    /**
     * f0 -> <N>
     */
    public Vector<Type> visit(Int n, HashMap env) {
        return buildType(ConstType.INTEGER);
    }

    /**
     * f0 -> <VAR>
     */
    public Vector<Type> visit(Var n, HashMap<String, Type> env){
        String varname = n.f0.toString();

        if( env.containsKey(varname) ){
            return buildType(env.get(varname));
        } else {
            return buildType(ConstType.NOPE);
        }
    }
   /**
    * f0 -> <FN>
    * f1 -> "("
    * f2 -> RType()
    * f3 -> <VAR>
    * f4 -> RParam()
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> Expr()
    * f8 -> "}"
    */
    public Vector<Type> visit(Abs n, HashMap env){
        // get the type of the parameter
        Vector<Type> paramTypes = n.f2.accept(this, env);
        Type paramType  = paramTypes.elementAt(0);

        // add param id and type to the type environment (mapping)
        env.put(n.f3.toString(), paramType);

        // using the new environment get the type of the expression
        Vector<Type> exprTypes = n.f7.accept(this, env);
        Type exprType = exprTypes.elementAt(0);

        // construct the arrow type for the abstraction
        return buildType(new Type(paramType, exprType));
    }

    /**
     * f0 -> TInt()
     * f1 -> ( <ARROW> RType() )?
     */
    public Vector<Type> visit(RType n, HashMap env) {
        Vector<Type> intType = n.f0.accept(this, env);
        Vector<Type> rType = n.f1.accept(this, env);

        if( rType.elementAt(0).isEmpty() ){
            return intType;
        }

        return buildType(new Type(intType.elementAt(0), rType.elementAt(0)));
    }

    /**
     * f0 -> <TINT>
     */
    public Vector<Type> visit(TInt n, HashMap env) {
        return buildType(ConstType.INTEGER);
    }

    /**
     * f0 -> ( "(" Expr() ")" RApp() )?
     */
    public Vector<Type> visit(RApp n, HashMap env){
        // NOTE returns null if the optional bit is not there (epsilon)
        Vector<Type> t = n.f0.accept(this, env);

        return n.f0.accept(this, env);
    }


    /**
     * f0 -> Abs() RApp()
     *       | Int()
     *       | Var() RApp()
     */
    public Vector<Type> visit(Expr n, HashMap env) {
        Type first, arg, arrow;

        Vector<Type> list = n.f0.accept(this, env);

        first = list.remove(0);

        if ( list.size() == 0 ){
            // Int() only
            return buildType(first);
        }

        // if we're dealing with one of the RApp() cases
        // while we have parameters to check
        while( ! list.isEmpty() ){
            // grab the next argument type
            arg = list.remove(0);

            // we have more args than params
            if( first == null ){
                return buildType(ConstType.NOPE);
            }

            // the RApp() is empty we're at the end
            // eg, Integer, Empty
            // eg, Integer -> Integer, Empty
            if ( arg.isEmpty() ) {
                return buildType(first);
            }

            // if the arg ever doesn't match the param it's broken
            if(! first.t1.equals(arg) ){
                return buildType(ConstType.NOPE);
            }

            // grab the next part of the arrow
            // may return null, see first check above
            first = first.t2;
        }

        // java bro
        return buildType(ConstType.NOPE);
    }


    private Vector<Type> elementsToCollection(Enumeration<Node> e, HashMap env){
        Vector<Type> interim, result = new Vector<Type>();

        while ( e.hasMoreElements() ) {
            interim = e.nextElement().accept(this, env);

            if( interim != null ){
                result.addAll(interim);
            }
        }

        return result;
    }

    public Vector<Type> visit(NodeSequence n, HashMap env) {
        return elementsToCollection(n.elements(), env);
    }

    public Vector<Type> visit(NodeList n, HashMap env) {
        return elementsToCollection(n.elements(), env);
    }

    public Vector<Type> visit(NodeListOptional n, HashMap env) {
        if ( n.present() ) {
            return elementsToCollection(n.elements(), env);
        } else {
            return buildType(ConstType.EMPTY);
        }
    }

    public Vector<Type> visit(NodeOptional n, HashMap env) {
        if ( n.present() ) {
            Vector<Type> t = n.node.accept(this, env);

            return t;
        } else {
            return buildType(ConstType.EMPTY);
        }
    }

    public Vector<Type> visit(NodeToken n, HashMap env) {
        return null;
    }
}
