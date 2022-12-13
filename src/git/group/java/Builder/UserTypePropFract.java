package git.group.java.Builder;

import git.group.java.Comparator.Comparator;
import git.group.java.Comparator.ComparatorPropFract;

public class UserTypePropFract implements UserType {
    public static final String typename = new String("ProperFraction");
    public int intPart;
    public int numerator; //числитель
    public int denominator; //знаменатель

    public UserTypePropFract() {}

    public UserTypePropFract(int intPart, int num, int denom) {
        num = Math.abs(num);
        denom = Math.abs(denom);
        if (num >= denom) {
            this.numerator = denom;
            this.denominator = num;
        } else {
            this.numerator = Math.abs(num);
            this.denominator = Math.abs(denom);
        }
        this.intPart = intPart;
    }

    @Override
    public String typeName() {
        return "ProperFraction";
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public Object readValue() {
        return null;
    }

    @Override
    public Object parseValue(String ss) {
        String[] numStr = ss.split("/|'");
        intPart = Integer.parseInt(numStr[0]);
        numerator = Integer.parseInt(numStr[1]);
        denominator = Integer.parseInt(numStr[2]);
        return this;
    }

    @Override
    public String toString() {
        return intPart+"'"+numerator+"/"+denominator;
    }

    @Override
    public Comparator getTypeComparator() {
        //return this;
        Comparator comparator = new ComparatorPropFract();
        return comparator;
    }
}
