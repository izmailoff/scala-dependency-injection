package com.izmailoff.reader

import com.izmailoff.shared.{UserCrud, User, SqlStorage, Storage}

trait MyReaderMonadService extends UserCrud[User] {
  def findUserM(id: Long): MyReader[Storage[User], Option[User]] =
    MyReader(storage => storage.findById(id))

  def createUserM(user: User): MyReader[Storage[User], Long] =
    MyReader(storage => storage.insert(user))
}

object MyReaderUserService {
  def readerUserService = new MyReaderMonadService {
    val userStorage = new SqlStorage {}

    override def findUser(id: Long) = findUserM(id)(userStorage)

    override def createUser(user: User) = createUserM(user)(userStorage)
  }
}
