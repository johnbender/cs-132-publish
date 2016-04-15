//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
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
public class Abs implements Node {
   public NodeToken f0;
   public NodeToken f1;
   public RType f2;
   public NodeToken f3;
   public RParam f4;
   public NodeToken f5;
   public NodeToken f6;
   public Expr f7;
   public NodeToken f8;

   public Abs(NodeToken n0, NodeToken n1, RType n2, NodeToken n3, RParam n4, NodeToken n5, NodeToken n6, Expr n7, NodeToken n8) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
      f3 = n3;
      f4 = n4;
      f5 = n5;
      f6 = n6;
      f7 = n7;
      f8 = n8;
   }

   public Abs(RType n0, NodeToken n1, RParam n2, Expr n3) {
      f0 = new NodeToken("fn");
      f1 = new NodeToken("(");
      f2 = n0;
      f3 = n1;
      f4 = n2;
      f5 = new NodeToken(")");
      f6 = new NodeToken("{");
      f7 = n3;
      f8 = new NodeToken("}");
   }

   public void accept(visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}
