package vaporgen;

import syntaxtree.*;
import visitor.GJDepthFirst;
import analysis.Type;
import java.util.HashMap;
import java.util.Vector;

public class TypeVisitor
    extends GJDepthFirst<Vector<String>, HashMap<String, Type>> {

    /**
     * f0 -> Expr()
     * f1 -> <EOF>
     */
    public Vector<Type> visit(Prog n, HashMap env) {
        // function declarations?
        return n.f0.accept(this, env);
    }
}
