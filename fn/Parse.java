import parser.Fn;
import parser.ParseException;
import syntaxtree.Prog;
import analysis.PrintVisitor;
import analysis.TypeVisitor;
import analysis.Type;
import java.util.HashMap;

class Parse {
    public static void main(String args[]){
        try {
            // create the AST from the parse of stdin
            Prog p = new Fn(System.in).Prog();

            // set up a new visitor to traverse the tree
            TypeVisitor visitor = new TypeVisitor();

            // set up an empty type environment
            HashMap<String, Type> typeEnv= new HashMap<String, Type>();

            // traverse the tree and print out the returned type
            System.out.println(p.accept(visitor, typeEnv));
        } catch (ParseException e){
            System.out.println(e.toString());
        }
    }
}
