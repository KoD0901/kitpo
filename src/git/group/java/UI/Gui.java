package git.group.java.UI;

import git.group.java.Builder.UserType;
import git.group.java.Builder.UserTypeInteger;
import git.group.java.Builder.UserTypePropFract;
import git.group.java.List.TListInterface;
import git.group.java.UserFactory;
import git.group.java.Serialization;
import git.group.scala.TList;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame{
    private UserType builder;
    private TListInterface list;

    public Gui(TListInterface lt) {
        super("Laba1");
        list = lt;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //окно вывода
        JTextArea out = new JTextArea();
        out.setEditable(false);
        out.setFont(new Font("Consolas", Font.PLAIN, 22));
        JPanel menu = new JPanel();
        Box box = Box.createVerticalBox();
        JComboBox userType = new JComboBox(UserFactory.getTypeNameList().toArray());
        userType.addActionListener(view -> {
            System.out.println(userType.getSelectedItem());
            Object type = userType.getSelectedItem();
            String type1 = type.toString();
            try
            {
                builder = UserFactory.getBuilderByName(type1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //list = new TList(builder);
            list = new TList(builder);
        });

        //loadsave
        JPanel workFiles = new JPanel(new FlowLayout());
        workFiles.setBorder(BorderFactory.createEmptyBorder(10,0,30,0));
        JButton load = new JButton("Load");
        load.addActionListener(v -> {
            try {
                list = Serialization.loadFile("file.txt");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "\nList was loaded!";
            out.setText(str);
        });
        JButton save = new JButton("Save");
        save.addActionListener(v -> {
            try {
                Serialization.saveToFile(list, "file.txt", userType.getSelectedItem().toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "\nList was saved!";
            out.setText(str);
        });
        workFiles.add(load);
        workFiles.add(save);

        //insert
        JPanel insertIndexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel insertValuePanel = new JPanel();
        JLabel insertIndexLabel = new JLabel("index: ");
        JTextField insertIndexField = new JTextField(4);
        JLabel insertValueLabel = new JLabel("value: ");
        JTextField insertValueField = new JTextField(10);
        insertValueField.setToolTipText("Int: int    ProperFraction: int'int/int");

        insertIndexPanel.add(insertIndexLabel);
        insertIndexPanel.add(insertIndexField);
        insertValuePanel.add(insertValueLabel);
        insertValuePanel.add(insertValueField);
        JPanel insert = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton insertBtn = new JButton("Insert front");
        insertBtn.addActionListener(view -> {
            UserType obj = null;
            try {
                obj = (UserType) UserFactory
                        .getBuilderByName(userType.getSelectedItem().toString())
                        .parseValue(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertValueField.setText("");
            list.pushFront(obj);
            String str = out.getText() + "\nInsert front " + obj.toString();
            out.setText(str);
        });

        JPanel insertbyIndex = new JPanel(new FlowLayout(FlowLayout.LEFT));
        insertbyIndex.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton insertBtnbyIndex = new JButton("Insert by Index");
        insertBtnbyIndex.addActionListener(view -> {
            UserType obj = null;
            try {
                obj = (UserType) UserFactory
                        .getBuilderByName(userType.getSelectedItem().toString())
                        .parseValue(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            list.add(obj, index);
            String str = out.getText() + "\nInsert by Index " + obj.toString() + " at " + index;
            out.setText(str);
        });

        JButton insertBtnBack = new JButton("Insert end");
        insertBtnBack.addActionListener(view -> {
            UserType obj = null;
            try {
                obj = (UserType) UserFactory
                        .getBuilderByName(userType.getSelectedItem().toString())
                        .parseValue(insertValueField.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
            insertValueField.setText("");
            list.pushEnd(obj);
            String str = out.getText() + "\nInsert end " + obj.toString();
            out.setText(str);
        });

        JButton deleteBtn = new JButton("Delete by Index");
        deleteBtn.addActionListener(view -> {
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            list.delete(index);
            String str = out.getText() + "\n" + "Index: " + index + " deleted";
            out.setText(str);
        });

        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT));
        search.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton searchBtn = new JButton("Search by Index");
        searchBtn.addActionListener(view -> {
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            String str = out.getText() + "\n" + "Index " + index + ": " + list.find(index);
            out.setText(str);
        });

        JButton sortBtn = new JButton("quickSort");
        sortBtn.addActionListener(view -> {
            list = list.sort(builder.getTypeComparator());
            String str = out.getText() + "\n" + "List sorted! ";
            out.setText(str);
        });

        JButton clrBtn = new JButton("Clear List");
        clrBtn.addActionListener(view -> {
            list.clear();
            String str = out.getText() + "\n" + "List cleared! ";
            out.setText(str);

        });

        JPanel show = new JPanel(new FlowLayout(FlowLayout.LEFT));
        show.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton showBtn = new JButton("Show List");
        showBtn.addActionListener(view -> {
            String str = out.getText() + "\n" + list.toString();
            out.setText(str);
        });

        /*box.add(userType);
        insert.add(insertBtn);
        insert.add(insertBtnBack);
        insertbyIndex.add(insertBtnbyIndex);
        insertbyIndex.add(deleteBtn);
        search.add(searchBtn);
        show.add(sortBtn);
        show.add(showBtn);
        show.add(clrBtn);
        box.add(userType);
        box.add(workFiles);
        box.add(insertIndexPanel);
        box.add(insertValuePanel);
        box.add(insert);
        box.add(insertbyIndex);
        box.add(search);
        box.add(show);
        menu.add(box);*/
        box.add(userType);
        menu.add(box);

        userType.addActionListener(e -> {
            box.add(userType);
            insert.add(insertBtn);
            insert.add(insertBtnBack);
            insertbyIndex.add(insertBtnbyIndex);
            insertbyIndex.add(deleteBtn);
            search.add(searchBtn);
            show.add(sortBtn);
            show.add(showBtn);
            show.add(clrBtn);
            box.add(userType);
            box.add(workFiles);
            box.add(insertIndexPanel);
            box.add(insertValuePanel);
            box.add(insert);
            box.add(insertbyIndex);
            box.add(search);
            box.add(show);
            menu.add(box);
            box.remove(userType);
            revalidate();
            repaint();
        });

        JPanel frame = new JPanel();
        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.WEST);
        frame.add(new JScrollPane(out),BorderLayout.CENTER);

        setContentPane(frame);
        setSize(800,500);
        setResizable(false);
        setVisible(true);
    }
}
