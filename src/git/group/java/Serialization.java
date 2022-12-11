package git.group.java;

import git.group.java.Builder.UserType;
import git.group.scala.TList;
import git.group.java.List.TListInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Objects;

public class Serialization {
    public  static void saveToFile(TListInterface list, String filename, String type) throws Exception {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(type);
            list.forEach(writer::print);
        }
        System.out.println("\nList was saved!");
    }

    public static TList loadFile(String filename) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String type = br.readLine();
            if (!UserFactory.getTypeNameList().contains(type)) {
                throw new Exception("Wrong file structure");
            }
            String line = br.readLine();
            String[] items = line.split(" ");
            UserType[] arr = new UserType[items.length];

            for (int i = 0; i < arr.length; i++)
                if (!Objects.equals(items[i], "null"))
                    arr[i] = (UserType) UserFactory.getBuilderByName(type).parseValue(items[i]);

            System.out.println("\nList was loaded!");
            return new TList(arr);
        }
    }
}
