package git.group.scala

import git.group.java.UI.Gui
import git.group.java.Builder.{UserType, UserTypeInteger}
import git.group.java.Comparator.Comparator
import git.group.java.UserFactory
import git.group.java.Test

object Main {
  def main(args:Array[String]): Unit = {
    val builder: UserType = new UserTypeInteger()
    val list: TList = new TList(builder)
    new Gui(list)
    val test: Test = new Test()
    test.run()
  }
}
