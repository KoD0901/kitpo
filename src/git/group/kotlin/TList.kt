package git.group.kotlin

import git.group.java.List.TListInterface
import git.group.java.Builder.UserType
import git.group.java.Comparator.Comparator
import git.group.java.List.DoIt

class TList() : TListInterface {
    class Node(data1:Any)
    {
        var data: Any = data1
        var next: Node? = null
    }
    var head: Node? = null
    var tail: Node? = null
    var size:Int = 0
    private var builder:UserType? = null

    internal constructor(build: UserType) :this() {
        builder = build
    }

    internal constructor(arr:Array<UserType>) : this() {
        for (i  in 0..arr.size) {
            pushEnd(arr.iterator())
        }
    }

    override fun clear():Boolean {
        if (head == null)
            return false
        while(head != null)
            delete(0)
        return true
    }

    override fun pushFront(data:Any):Boolean{
        var nNode: Node? = Node(data)
        if (head == null)
        {
            head = nNode
            tail = nNode
        }
        else
        {
            val tmp:Node? = head


            head = nNode
            head?.next = tmp
        }
        size += 1
        return true
    }

    override fun pushEnd(data:Any):Boolean
    {
        val nNode:Node? = Node(data)

        if (head == null){
            head = nNode
            tail = nNode
        }
        else {
            tail?.next = nNode
            tail = nNode
        }
        size = size + 1
        return true
    }

    override fun add(data:Any, index:Int):Boolean
    {
        if (index == 0){
            return pushFront(data)
        }
        val nNode:Node? = Node(data)

        if (head == null) {
            head = nNode
            tail = nNode
        }
        else {
            var tmp: Node? = head
            var current: Node? = null
            var n: Int = 0
            while (n < index) {
                current = tmp
                tmp = tmp?.next
                n = n + 1
            }
            current?.next = nNode
            nNode?.next = tmp
        }
        size = size + 1
        return true
    }

    override fun delete(index:Int):Boolean {
        if (index < 0) {
            return false
        }
        var toDel: Node? = null
        var toDelPrev: Node? = null

        if (head == null) {
            println("List is empty")
            return false
        }
        else {
            if (head != tail) {
                if (index > 0) {
                    toDelPrev = findNode(index-1)
                    toDel = toDelPrev?.next
                }
                else toDel = head

                if (toDelPrev != null) {
                    toDelPrev.next = toDel?.next
                    toDel = null
                    if(toDelPrev.next == null)
                        tail = toDelPrev
                }
                else {
                    head = toDel?.next
                    toDel = null
                }

            }
            else {
                head = null
                tail = null
            }

        }
        size = size - 1
        return true
    }

    private fun findNode(id:Int): Node? {
        var res:Node? = head
        var n:Int = 0
        while (n < id){
            res = res?.next
            n = n + 1
        }
        return res
    }

    override fun find(index:Int):Any {
        var current:Node? = head
        var dataNode:Any = current!!.data
        if (index == 0){
            dataNode = current.data
            return dataNode
        }
        var n:Int = 0
        while (n < index && current?.next != null){
            current = current.next
            n = n + 1
        }
        dataNode = current!!.data
        return dataNode
    }

    override fun forEach(action: DoIt) {
        var i:Int = 0
        var cur:Node? = head
        while (i < size)
        {
            action.doIt(cur?.data.toString() + " ")
            cur = cur?.next
            i += 1
        }
    }

    override fun sort(comparator: Comparator?) {
        head = quicksort(head, comparator)
    }

    fun quicksort(p3:Node?, comparator: Comparator?): Node? {
        var m:Node?
        var q:Node?
        var p1:Node?
        var p2:Node?
        var p:Node? = p3

        if(p==null || p.next == null)
        {
            return p
        }
        m = p
        p = p.next!!
        p1 = null
        p2 = null
        while(p != null)
        {
            q = p
            p = p.next
            var comp:Int = comparator!!.compare(q.data, m.data)
            if(comp < 0 || comp == 0)
            {
                q.next = p1
                p1 = q
            }
            else
            {
                q.next = p2
                p2 = q
            }
        }

        p1 = quicksort(p1, comparator)
        p2 = quicksort(p2, comparator)

        m.next = p2
        if (p1 == null)
        {
            return m
        }
        q = p1
        while (q!!.next != null) q = q.next
        q.next = m
        return p1
    }

    override fun getBuilder(): UserType? = builder

    @JvmName("getSize1")
    fun getSize():Int = size


    override fun toString(): String {
        var cur = head
        var str = ""
        for (i in 0..size-1) {
            str += cur?.data.toString()
            str += "\n"
            cur = cur?.next
        }
        return str
    }

}
