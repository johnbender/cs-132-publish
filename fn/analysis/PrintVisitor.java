package analysis;

import syntaxtree.*;
import visitor.DepthFirstVisitor;

public class PrintVisitor extends DepthFirstVisitor {
   /**
    * f0 -> <FN>
    * f1 -> "("
    * f2 -> <ID>
    * f3 -> ")"
    * f4 -> "{"
    * f5 -> Expr()
    * f6 -> "}"
    */
    public void visit(Abs n){
        System.out.println("param: " + n.f2.toString());

        // what's wrong here ??
    }
}
