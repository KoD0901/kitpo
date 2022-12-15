package git.group.kotlin

import git.group.java.UI.Gui
import git.group.java.Builder.UserTypeInteger
import git.group.java.Builder.UserType
import git.group.java.Test

fun main() {
    val builder: UserType = UserTypeInteger()
    val list: TList = TList(builder)
    Gui(list)
    val test: Test = Test()
    test.run()
}