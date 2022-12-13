package git.group.java.List;

import git.group.java.Builder.UserType;
import git.group.java.Comparator.Comparator;
import git.group.scala.TList;
import java.util.function.Consumer;

public interface TListInterface {
    boolean clear();
    boolean pushFront(Object obj);
    boolean pushEnd(Object data);
    boolean add(Object data, int index);
    boolean delete(int index);
    Object find(int index);
    //void forEach(DoIt action);
    void forEach(Consumer<UserType> action);
    TList sort(Comparator comparator);
    UserType getBuilder();
    String toString();
}
