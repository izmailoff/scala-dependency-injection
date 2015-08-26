package com.izmailoff.shared

import com.izmailoff.cake.inherrited.{CakeUserService, InitOrderComparison}
import com.izmailoff.cake.selftypeannotated.CakeUserServiceSelfTypes
import com.izmailoff.reader.{ScalazReaderUserService, MyReaderUserService}

import scala.util.control.NonFatal

object ServiceTest extends App {
  def test(service: => UserCrud[User], serviceName: String): Unit = {
    try {
      println(s"SERVICE: $serviceName")
      val s = service
      println("FIND: " + s.findUser(1))
      println("CREATE: " + s.createUser(User(0, "some_user")))
      println("FIND AGAIN: " + s.findUser(1))
    }
    catch {
      case NonFatal(e) =>
        println(s"Service threw an exception:\n${e.getMessage}")
    }
    println("\n" * 2)
  }

  // CAKES
  test(CakeUserService.userService, "cake-inherritance")
  test(CakeUserServiceSelfTypes.userServiceSt, "cake-self-type")

  // CAKE WITH FIELD - TESTING INIT ORDER
  test(InitOrderComparison.correctInitOrder, "cake-with-field-correct-init-order")
  test(InitOrderComparison.wrongInitOrder, "cake-with-field-wrong-init-order")
  test(InitOrderComparison.overrideIsTooLate, "cake-with-field-override-is-too-late")

  // READER MONADS
  test(MyReaderUserService.readerUserService, "my-reader-monad")
  test(ScalazReaderUserService.readerUserService, "scalaz-reader-monad")
}
