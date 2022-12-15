package git.group.java.Comparator;

import git.group.java.Builder.UserTypePropFract;

import java.io.Serializable;

public class ComparatorPropFract implements Comparator, Serializable
{
    @Override
    public int compare(Object o1, Object o2) {
        if (((UserTypePropFract) o1).intPart == ((UserTypePropFract) o2).intPart) {
            if (((UserTypePropFract) o1).numerator * ((UserTypePropFract) o2).denominator ==
                    ((UserTypePropFract) o2).numerator * ((UserTypePropFract) o1).denominator) return 0;
            if (((UserTypePropFract) o1).numerator * ((UserTypePropFract) o2).denominator >
                    ((UserTypePropFract) o2).numerator * ((UserTypePropFract) o1).denominator) return 1;
            else return -1;
        }
        if (((UserTypePropFract) o1).intPart > ((UserTypePropFract) o2).intPart) return 1;
        else return -1;
    }
}
