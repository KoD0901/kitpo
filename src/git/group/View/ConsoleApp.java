package git.group.View;

import git.group.Builder.UserType;
import git.group.Builder.UserTypeInteger;
import git.group.Builder.UserTypePropFract;
import git.group.List.TList;

import java.io.*;
import java.util.Scanner;


public class ConsoleApp
{
	private       Scanner in = new Scanner(System.in);
	private final String  os = System.getProperty("os.name");

	private UserType builder;
	private TList   list;

	private boolean flag_menu;
	private int     switch_menu;
	private int     tmp_index;

	private void clr()
	{
		try
		{
            if (os.contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ConsoleApp()
	{
		flag_menu = true;
	}

	public boolean toBuilder(String name)
	{
		try
		{
			builder = settingBuilder(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}

		list = new TList(builder);
		return true;
	}

	private UserType settingBuilder(String name) throws Exception
	{
        if (name.equals("PropFract"))
        {
            return new UserTypePropFract();
        }
        else if (name.equals("Integer"))
        {
            return new UserTypeInteger();
        }
        else
        {
            Exception e = new Exception("OSHIBKA: нет такого типа");
            throw e;
        }
	}

	public void run()
	{
		while (flag_menu)
		{
			ConsoleMenu();
			System.out.print(">>");
			switch_menu = in.nextInt();
			in.nextLine();
			switch (switch_menu)
			{
				case 1:
					System.out.println("Введите данные");
					System.out.print(">>");
					list.pushFront(builder.parseValue(in.next()));

					clr();
					break;
				case 2:
					System.out.println("Введите данные");
					System.out.print(">>");
					list.pushEnd(builder.parseValue(in.next()));

					clr();
					break;
				case 3:
					System.out.println("Введите индекс элемента");
					System.out.print(">>");
					tmp_index = in.nextInt();
					in.nextLine();
					System.out.println("Введите данные");
					System.out.print(">>");
					list.add(builder.parseValue(in.next()), tmp_index);

					clr();
					break;
				case 4:
					list.pushFront(builder.create());

					clr();
					break;
				case 5:
					list.pushEnd(builder.create());

					clr();
					break;
				case 6:
					System.out.println("Введите индекс элемента");
					System.out.print(">>");
					tmp_index = in.nextInt();
					in.nextLine();
					list.add(builder.create(), tmp_index);

					clr();
					break;
				case 7:
					System.out.println("Введите индекс");
					System.out.print(">>");
					tmp_index = in.nextInt();
					in.nextLine();
					list.delete(tmp_index);

					clr();
					break;
				case 8:
					System.out.println("Введите индекс");
					System.out.print(">>");
					tmp_index = in.nextInt();
					in.nextLine();
					System.out.println("element: " + list.find(tmp_index));

					in.nextLine();
					clr();
					break;
				case 9:
					list.sort();

					clr();
					break;
				case 10:
					drawList();
					in.nextLine();

					clr();
					break;
				case 11:
					System.out.println("Введите кол-во лементов");
					int elem = in.nextInt();
					in.nextLine();
					testDriweStringList(elem);

					in.nextLine();
					clr();
					break;
				case 12:
					list.clear();

					clr();
					break;
				case 13:
					System.out.println("Введите тип списка ");
					try
					{
						UserType old = settingBuilder(builder.typeName());
						builder = settingBuilder(in.next());
						if (!list.setBuilder(builder))
						{
							System.out.println("Список не пустой. Очистите его перед сменой типа.");
							builder = old;
							in.nextLine();
						}
						clr();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					break;

				case 14:
					try (ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream("list.bin")))
					{
						out.writeObject(list);
						System.out.println("Успешная запись");

						in.nextLine();
						clr();
					}
					catch (Exception e)
					{
						System.out.println("Ошибка записи");
						in.nextLine();
						clr();
					}
					break;
				case 15:
					try (ObjectInputStream i = new ObjectInputStream(
							new FileInputStream("list.bin")))
					{
						TList loaded = (TList) i.readObject();
						builder = settingBuilder(loaded.getBuilder().typeName());
						list    = loaded;
						System.out.println("Успешное чтение");

						in.nextLine();
						clr();
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.out.println("Ошибка чтения");
					}
					break;

				case 0:
					flag_menu = false;
					System.out.println("Выход");

					clr();
					break;
				default:
					System.out.println("ochepyatka");

					in.nextLine();
					clr();
					break;
			}
		}
		in.close();
	}

	private void drawList()
	{
		list.forEach((name) ->
					 {
						 System.out.println(name);
					 });
	}

	private void drawList(TList otherlist)
	{
		otherlist.forEach((name) ->
						  {
							  System.out.println(name);
						  });
	}

	public void testDriweStringList(int maxElement)
	{
		TList testlist = new TList(builder);

        for (int i = 0; i < maxElement; i++)
        {
            testlist.pushEnd(builder.create());
        }
		System.out.println("\n\nСгенерированый список");
		drawList(testlist);

		System.out.println("\n\nПоиск каждого четвертого елемента");
        for (int i = 0; i < maxElement; i = i + 4)
        {
            System.out.println(testlist.find(i));
        }

		System.out.println("\n\nПроиизошла сортировка");
		testlist.sort();
		drawList(testlist);
		testlist.clear();
		System.out.println("Список удален");
	}

	private void ConsoleMenu()
	{
		System.out.println("----------------------------------");
		System.out.println("1 - Добавить в начало списка");
		System.out.println("2 - Добавить в конец списка");
		System.out.println("3 - Добавить по индексу в список");
		System.out.println("4 - Добавить в начало списка (случ.)");
		System.out.println("5 - Добавить в конец списка (случ.)");
		System.out.println("6 - Добавить по индексу в список (случ.)");
		System.out.println("7 - Удалить по индексу из списка");
		System.out.println("8 - Поиск по индексу");
		System.out.println("9 - Сортировка списка (quickSort)");
		System.out.println("10 - Вывод списка");
		System.out.println("11 - **testdrive** ");
		System.out.println("12 - Очистить список");
		System.out.println("13 - Изменить тип списка");
		System.out.println("14 - Записать в файл список");
		System.out.println("15 - Прочитать из файла список");
		System.out.println("0 - Выйти");
		System.out.println("----------------------------------");
	}
}
