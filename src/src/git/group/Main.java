package git.group;

import git.group.Builder.UserType;
import git.group.UserFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        UserType builder = UserFactory.getBuilderByName("Integer");

        new Gui();
        Test test = new Test();
        test.run();
    }
}
