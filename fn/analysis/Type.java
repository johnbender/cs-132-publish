package analysis;

import analysis.ConstType;

public class Type {
    public ConstType t;
    public Type t1;
    public Type t2;

    public Type(ConstType t){
        this.t = t;
    }

    public Type(Type t1, Type t2){
        this.t1 = t1;
        this.t2 = t2;
    }

    public boolean isArrow(){
        return t == null;
    }

    public boolean isNope(){
        return t == ConstType.NOPE;
    }

    public boolean isEmpty(){
        return t == ConstType.EMPTY;
    }

    public boolean isInteger(){
        return t == ConstType.INTEGER;
    }

    public String toString(){
        if( t == null ){
            return t1.toString() + "->" + t2.toString();
        } else {
            return t.toString();
        }
    }

    public boolean equals(Type other){
        if ( t == null && other.t == null ){
            return t1.equals(other.t1) && t2.equals(other.t2);
        }

        if ( t != null && other.t != null ) {
            return t == other.t;
        }

        return false;
    }
}
