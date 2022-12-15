package git.group.java;

import git.group.java.Builder.UserType;
import git.group.java.Builder.UserTypeInteger;
import git.group.java.Builder.UserTypePropFract;

import java.util.ArrayList;
import java.util.Arrays;

public class UserFactory {


    public static ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Integer","ProperFraction"));
        return list;
    }

    public static UserType getBuilderByName(String name) throws Exception
    {
        if (name.equals(UserTypePropFract.typename))
        {
            return new UserTypePropFract();
        }
        else if (name.equals(UserTypeInteger.typename))
        {
            return new UserTypeInteger();
        }
        else
        {
            return null;
        }
    }

}
