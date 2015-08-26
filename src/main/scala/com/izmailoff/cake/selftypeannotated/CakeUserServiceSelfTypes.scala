package com.izmailoff.cake.selftypeannotated

import com.izmailoff.shared._

trait UserServiceST {
  self: UserCrud[User] =>
    //with Storage[User] =>
}

object CakeUserServiceSelfTypes {
  def userServiceSt = new UserServiceST with SqlStorage with UserCrudDb

  // this method is available from Storage
  val leakedMethod = userServiceSt.insert _
}
