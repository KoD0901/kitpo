package git.group;

import git.group.Builder.UserType;
import git.group.Builder.UserTypeInteger;
import git.group.Builder.UserTypePropFract;

import java.util.ArrayList;
import java.util.Arrays;

public class UserFactory {
    public static ArrayList<String> getTypeNameList() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Integer","ProperFraction"));
        return list;
    }

    public static UserType getBuilderByName(String name){
        switch (name) {
            case "Integer":
                return new UserTypeInteger();
            case "ProperFraction":
                return new UserTypePropFract();
            default:
                return null;
        }

    }

}
