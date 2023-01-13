package git.group.java.List;

import git.group.java.Builder.UserType;
import git.group.java.Comparator.Comparator;
import java.util.ArrayList;

public class TList implements TListInterface
{
	private class Node
	{
		public Node   next;
		public Object data;

		public Node(Object data)
		{
			this.data = data;
			this.next = null;
		}
	}

	private Node       head;
	private Node       tail;
	private int        size;
	private UserType builder;

	public TList(UserType builder)
	{
		this.builder    = builder;
		this.head       = null;
		this.tail       = null;
		this.size       = 0;
	}

	public TList(UserType[] arr) {
		for (int i=0; i<arr.length; i++)
		{
			pushEnd(arr[i]);
		}
	}

	public boolean clear()
	{
        if (head == null)
        {
            return false;
        }

        while (head != null)
        {
            delete(0);
        }

		return true;
	}

	public boolean pushFront(Object obj)
	{

		Node nNode = new Node(obj);

		if (head == null)
		{
			head = nNode;
			tail = nNode;
		}
		else
		{
			Node temp = head;
			head      = nNode;
			head.next = temp;
		}
		size++;
		return true;
	}

	public boolean pushEnd(Object data)
	{
		Node nNode = new Node(data);

		if (head == null)
		{
			head = nNode;
			tail = nNode;
		}
		else
		{
			tail.next = nNode;
			tail      = tail.next;
		}
		size++;
		return true;
	}

	public boolean add(Object data, int index)
	{
		Node nNode = new Node(data);

		if (head == null)
		{
			head = nNode;
			tail = nNode;
		}
		else
		{
			Node temp, current;
			temp    = head;
			current = null;

			for (int i = 0; i < index; i++)
			{
				current = temp;
				temp    = temp.next;
			}

			current.next = nNode;
			nNode.next   = temp;
		}
		size++;
		return true;
	}

	public boolean delete(int index)
	{
        if (size < 0 || index < 0)
        {
            return false;
        }

		Node toDel, toDelPrev = null;

		if (head == null)
		{
			System.out.println("List is empty");
			return false;
		}
		else
		{
			if (head != tail)
			{
                if (index > 0)
                {
                    toDelPrev = findNode(index - 1);
                    toDel     = toDelPrev.next;
                }
                else
                {
                    toDel = head;
                }

				if (toDelPrev != null)
				{
					toDelPrev.next = toDel.next;
					toDel          = null;
				}
				else
				{
					head  = toDel.next;
					toDel = null;
				}
			}
			else
			{
				head = tail = null;
			}
		}
		size--;
		return true;
	}

	public Object find(int index)
	{
		Object dataNode;
		Node   current = head;

		if (index == 0)
		{
			dataNode = current.data;
			return dataNode;
		}

        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
		dataNode = current.data;
		return dataNode;
	}

	public void forEach(DoIt action) {
		ArrayList arr = new ArrayList();

		for (Node cur = head; cur != null; cur = cur.next)
			arr.add(cur.data);

		for (int i=0; i<arr.size();i++) {
			String str;
			if (arr.get(i) == null) str = "null ";
			else str = arr.get(i).toString() + " ";
			action.doIt(str);
		}
	}

	public void sort(Comparator comparator)
	{
		head = mergeSort(comparator);
	}

	public Node mergeSort(Comparator comparator) {
		Node dummy = new Node(-1);
		dummy.next = this.head;
		Node left, right, tail;
		int n = this.size;
		for (int step = 1; step < n; step *= 2) {
			Node current = dummy.next;
			tail = dummy;
			while (current != null) {
				left = current;
				right = midd(left, step);
				current = midd(right, step);
				tail = merge(left, right, tail, comparator);
			}
		}
		return dummy.next;
	}

	public Node merge(Node left, Node right, Node tail, Comparator comparator) {
		while (left != null && right != null) {
			if (comparator.compare(left.data, right.data) <= 0) {
				tail.next = left;
				left = left.next;
			} else {
				tail.next = right;
				right = right.next;
			}
			tail = tail.next;
		}
		if (left != null) {
			tail.next = left;
		} else {
			tail.next = right;
		}
		while (tail.next != null) {
			tail = tail.next;
		}
		return tail;
	}

	public Node midd(Node head, int s) {
		if (head == null) {
			return null;
		}
		for (int i = 1; head.next != null && i < s; i++) {
			head = head.next;
		}
		Node right = head.next;
		head.next = null;
		return right;
	}

	private Node findNode(int id)
	{
		Node res = head;
        for (int i = 0; i < id; i++)
        {
            res = res.next;
        }
		return res;
	}

	public int getSize()
	{
		return this.size;
	}

	public UserType getBuilder()
	{
		return builder;
	}

	public String toString() {
		Node cur = head;
		String str ="";
		for (int i = 0; i < size; i++) {
			str += (cur.data.toString());
			str+="\n";
			cur = cur.next;
			}
		return str;
	}
}
