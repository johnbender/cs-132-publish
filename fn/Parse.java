import parser.Fn;
import parser.ParseException;
import syntaxtree.Prog;
import analysis.PrintVisitor;
import analysis.TypeVisitor;
import analysis.Type;
import java.util.HashMap;

class Parse {
    public static void main(String args[]){
        tree();
    }

    static void tree() {
        Fn fn = new Fn(System.in);

        try {
            // creates the visitor tree
            Prog p = Fn.Prog();

            // sets up a new visitor to traverse the tree
            TypeVisitor visitor = new TypeVisitor();

            // sets up an empty type environment
            HashMap<String, Type> typeEnv= new HashMap<String, Type>();

            // go!
            System.out.println(p.accept(visitor, typeEnv));
        } catch (ParseException e){
            System.out.println(e.toString());
        }
    }
}
