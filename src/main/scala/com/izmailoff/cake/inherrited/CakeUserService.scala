package com.izmailoff.cake.inherrited

import com.izmailoff.shared.{User, _}

trait UserService
  extends UserCrud[User]
  with Storage[User]


class SqlUserService
  extends UserService
  with SqlStorage
  with UserCrudDb


object CakeUserService {
  def userService = new SqlUserService

  // this method is available from Storage
  val leakedMethod = userService.insert _
}

