package git.group.java;

import git.group.java.Builder.UserType;
import git.group.java.Builder.UserTypeInteger;
import git.group.java.Builder.UserTypePropFract;
//import git.group.java.List.TList;
import git.group.scala.TList;

public class Test {
    public UserType settingBuilder(String name) throws Exception
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
            Exception e = new Exception("OSHIBKA: нет такого типа");
            throw e;
        }
    }

    public void run() throws Exception {
        testInt();
        testProperFraction();
    }

    private void drawList(TList otherlist)
    {
        otherlist.forEach((name) ->
        {
            System.out.println(name);
        });
    }

    void testInt() throws Exception {
        UserType builder = null;
        TList list;

        System.out.print("\tTest Integer");
        try {
            builder = settingBuilder("Integer");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = new TList(builder);
        System.out.print("\nСписок пуст\n");
        drawList(list);

        int max = (int) (Math.random() * 15) + 4;
        for (int i = 0; i < max; i++) {
            int value = (int) (Math.random() * 100) - 50;
            UserType obj = new UserTypeInteger(value);
            obj.toString();
            list.add(obj, i);
        }

        System.out.print("\nСгенерированный список\n");
        drawList(list);

        System.out.print("\nПоиск каждого четвертого элемента\n");
        for (int i = 0; i < max; i = i + 4)
        {
            System.out.println(list.find(i));
        }

        System.out.println("\nПроизошла сортировка\n");
        list = list.sort(builder.getTypeComparator());
        drawList(list);
        list.clear();
        System.out.println("Список удален");
    }

    void testProperFraction() throws Exception {
        UserType builder = null;
        TList list;
        System.out.print("\tTest ProperFraction");
        try {
            builder = settingBuilder("ProperFraction");
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = new TList(builder);
        System.out.print("\nСписок пуст\n");
        drawList(list);

        int max =(int) (Math.random() * 12) + 4;
        for (int i = 0; i < max; i++) {
            int intPart = (int) (Math.random() * 100) - 50;
            int num = (int) (Math.random() * 100);
            int denom = (int) (Math.random() * 500) + num;
            UserType obj = new UserTypePropFract(intPart,num,denom);
            obj.toString();
            list.add(obj, i);
        }

        System.out.print("\nСгенерированный список\n");
        drawList(list);

        System.out.print("\nПоиск каждого четвертого элемента\n");
        for (int i = 0; i < max; i = i + 4) {
            System.out.println(list.find(i));
        }

        System.out.println("\nПроизошла сортировка\n");
        list = list.sort(builder.getTypeComparator());
        drawList(list);
        list.clear();
        System.out.println("Список удален");
    }
}
