package git.group.scala

import git.group.java.List.{DoIt, TListInterface}
import git.group.java.Builder.UserType
import git.group.java.Comparator.Comparator

import java.util.function.Consumer

class TList() extends TListInterface
{
  class Node(data1:Any)
  {
    var data:Any = data1
    var next:Node = null
  }

  private var head:Node = null
  private var tail:Node = null
  private var size:Int = 0
  private var builder:UserType = null

  def this(build:UserType)
  {
    this()
    builder = build
  }

  def this(arr: Array[UserType])
    {
      this()
      for (i <- 0 until arr.length) {
        pushEnd(arr(i))
      }
    }

  def clear():Boolean=
  {
    if (head == null)
      return false

    while (head != null) delete(0)
    true
  }

  def pushFront(data:Any):Boolean =
  {
    var nNode:Node = new Node(data)

    if (head == null)
    {
      head = nNode
      tail = nNode
    }
    else
    {
      val tmp:Node = head


      head = nNode
      head.next = tmp
    }
    size = size + 1

    true
  }

  def pushEnd(data:Any):Boolean =
  {
    val nNode:Node = new Node(data)

    if (head == null){
      head = nNode
      tail = nNode
    }
    else {
      tail.next = nNode
      tail = nNode
    }
    size = size + 1
    true
  }

  def add(data:Any, index:Int):Boolean =
  {
    if (index == 0){
      return pushFront(data)
    }
    val nNode:Node = new Node(data)

    if (head == null) {
      head = nNode
      tail = nNode
    }
    else {
      var tmp: Node = head
      var current: Node = null
      var n: Int = 0
      while (n < index) {
        current = tmp
        tmp = tmp.next
        n = n + 1
      }
      current.next = nNode
      nNode.next = tmp
    }
    size = size + 1
    true
  }

  def delete(index:Int):Boolean = {
    if (index < 0) {
      return false
    }
    var toDel: Node = null
    var toDelPrev: Node = null

    if (head == null) {
      println("List is empty")
      return false
    }
    else {
      if (head != tail) {
        if (index > 0) {
          toDelPrev = findNode(index-1)
          toDel = toDelPrev.next
        }
        else toDel = head

        if (toDelPrev != null) {
          toDelPrev.next = toDel.next
          toDel = null
          if(toDelPrev.next == null)
            tail = toDelPrev
        }
        else {
          head = toDel.next
          toDel = null
        }

      }
      else {
        head = null
        tail = null
      }

    }
    size = size - 1
    true
  }

  private def findNode(id:Int):Node = {
    var res:Node = head
    var n:Int = 0
    while (n < id){
      res = res.next
      n = n + 1
    }
    res
  }

  def find(index:Int):Any = {
    var current:Node = head
    var dataNode:Any = current.data
    if (index == 0){
      dataNode = current.data
      return dataNode
    }
    var n:Int = 0
    while (n < index && current.next != null){
      current = current.next
      n = n + 1
    }
    dataNode = current.data
    dataNode
  }

  def sort2(xs: TList, comparator: Comparator): TList ={
    if(xs.size <= 1) xs
    else{
      val pivot = xs.find(size/2)
      concat(
        sort2(xs.filter((x: UserType) => comparator.compare(pivot, x) > 0), comparator),
        xs.filter((x: UserType) => comparator.compare(pivot, x) == 0),
        sort2(xs.filter((x: UserType) => comparator.compare(pivot, x) < 0), comparator),
      )
    }
  }

  def sort(comparator: Comparator): TList ={
    return sort2(this, comparator)
  }

  def forEach(action: Consumer[UserType]): Unit = {
    var i:Int = 0
    var cur:Node = head
    while (i < size)
    {
      action.accept(cur.data.asInstanceOf[UserType])
      cur = cur.next
      i += 1
    }
  }

  def getSize:Int = size

  def getBuilder:UserType = builder

  def filter(typeToBoolean: UserType => Boolean): TList = {
    val l1 = new TList(builder)
    this.forEach((x : UserType) => {
      if(typeToBoolean(x))
      {
        l1.pushEnd(x)
      }
    })
    return l1
  }

  override def toString: String = {
    var cur = head
    var str = ""
    for (i <- 0 until size) {
      str += cur.data.toString
      str += "\n"
      cur = cur.next
    }
    str
  }

  def concat(l1 : TList, l2 : TList, l3 : TList) : TList =
  {
    val l = new TList(builder)
    l1.forEach((x: UserType) => l.pushEnd(x));
    l2.forEach((x: UserType) => l.pushEnd(x));
    l3.forEach((x: UserType) => l.pushEnd(x));
    return l;
  }

}
