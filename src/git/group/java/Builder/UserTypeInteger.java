package git.group.java.Builder;

import git.group.java.Comparator.Comparator;
import git.group.java.Comparator.ComparatorInteger;

public class UserTypeInteger implements UserType {
    public static final String typename = new String("Integer");
    public int value;

    public UserTypeInteger(int val) {
        value = val;
    }
    public UserTypeInteger() {}

    @Override
    public String typeName() {
        return "Int";
    }

    @Override
    public Object create() {
        return null;
    }

    @Override
    public Object clone() {
        return this;
    }

    @Override
    public Object readValue() {
        return value;
    }

    @Override
    public Object parseValue(String ss) {
        value = Integer.parseInt(ss);
        return this;
    }

    @Override
    public Comparator getTypeComparator() {
        //return this;
        Comparator comparator = new ComparatorInteger();
        return comparator;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
