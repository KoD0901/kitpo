package git.group.test.java;

import git.group.java.Builder.UserType;
import git.group.java.List.TList;
import git.group.java.UserFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ListTest {

    private UserFactory factory;
    private UserType builder;
    private TList act, expect;

    @Before
    public void setUp() throws Exception {
        factory = new UserFactory();
        builder = factory.getBuilderByName("Integer");
        act = new TList(builder);
        expect = new TList(builder);
    }

    @Test
    public void identicalValues() {
        for (int i = 0; i <= 10; i++) {
            act.pushEnd(builder.parseValue(0 + ""));
            expect.pushEnd(builder.parseValue(0 + ""));
        }
        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void dirOrder() {
        for (int i = 0; i <= 10; i++) {
            act.pushEnd(builder.parseValue(i + ""));
        }
        for (int i = 0; i <= 10; i++) {
            expect.pushEnd(builder.parseValue(i + ""));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void reversOrder() {
        for (int i = 10; i >= 0; i--) {
            act.pushEnd(builder.parseValue(i + ""));
        }
        for (int i = 0; i <= 10; i++) {
            expect.pushEnd(builder.parseValue(i + ""));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void duplicates() {
        ArrayList<String> elements = new ArrayList<>(Arrays.asList("5", "5", "7", "1", "2"));
        for (String element : elements) {
            act.pushEnd(builder.parseValue(element));
        }
        Collections.sort(elements);
        for (String element : elements) {
            expect.pushEnd(builder.parseValue(element));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void multipleDuplicates() {
        ArrayList<String> elements = new ArrayList<>(Arrays.asList("5", "5", "6", "4", "4"));
        for (String element : elements) {
            act.pushEnd(builder.parseValue(element));
        }
        Collections.sort(elements);
        for (String element : elements) {
            expect.pushEnd(builder.parseValue(element));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void extremeValueBeg() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(9999999);
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);
        values.add(15);

        for (Integer value : values) {
            act.pushEnd(builder.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expect.pushEnd(builder.parseValue(value.toString()));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void extremeValueMid() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(9999999);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);
        values.add(15);

        for (Integer value : values) {
            act.pushEnd(builder.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expect.pushEnd(builder.parseValue(value.toString()));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void extremeValueEnd() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(8);
        values.add(9);
        values.add(14);
        values.add(15);
        values.add(9999999);

        for (Integer value : values) {
            act.pushEnd(builder.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expect.pushEnd(builder.parseValue(value.toString()));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void multiExtreme() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(9999999);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(9999999);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);
        values.add(15);
        values.add(9999999);

        for (Integer value : values) {
            act.pushEnd(builder.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expect.pushEnd(builder.parseValue(value.toString()));
        }

        act.sort(builder.getTypeComparator());
        assertEquals(expect.toString(), act.toString());
    }

    @Test
    public void sort() {
        for (int i = 1; i < 1025; i *= 2) {
            int n = i * 100000;
            System.out.print(n + "\t");
            TList list = new TList(builder);

            for (int j = 0; j < n; j++) {
                list.pushEnd(builder);
            }

            long start = System.nanoTime();
            try {
                list.sort(builder.getTypeComparator());
            } catch (StackOverflowError err) {
                System.out.println("Error");
            }
            list.sort(builder.getTypeComparator());
            long end = System.nanoTime();

            System.out.println((end - start) * 1.0 / 1_000_000);
        }
    }
}
