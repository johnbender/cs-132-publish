//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * All GJ void visitors must implement this interface.
 */

public interface GJVoidVisitor<A> {

   //
   // GJ void Auto class visitors
   //

   public void visit(NodeList n, A argu);
   public void visit(NodeListOptional n, A argu);
   public void visit(NodeOptional n, A argu);
   public void visit(NodeSequence n, A argu);
   public void visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> Expr()
    * f1 -> <EOF>
    */
   public void visit(Prog n, A argu);

   /**
    * f0 -> Abs() RApp()
    *       | Var() RApp()
    *       | Int()
    */
   public void visit(Expr n, A argu);

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
   public void visit(Abs n, A argu);

   /**
    * f0 -> ( "," RType() <VAR> RParam() )?
    */
   public void visit(RParam n, A argu);

   /**
    * f0 -> ( "(" Expr() RArg() ")" RApp() )?
    */
   public void visit(RApp n, A argu);

   /**
    * f0 -> ( "," Expr() RArg() )?
    */
   public void visit(RArg n, A argu);

   /**
    * f0 -> TInt()
    * f1 -> ( <ARROW> RType() )?
    */
   public void visit(RType n, A argu);

   /**
    * f0 -> <VAR>
    */
   public void visit(Var n, A argu);

   /**
    * f0 -> <N>
    */
   public void visit(Int n, A argu);

   /**
    * f0 -> <TINT>
    */
   public void visit(TInt n, A argu);

}

