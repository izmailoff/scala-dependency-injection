package com.izmailoff.reader

import com.izmailoff.shared.{SqlStorage, Storage, User, UserCrud}

import scalaz.Reader

trait ScalazReaderMonadService extends UserCrud[User] {
  def findUserM(id: Long): Reader[Storage[User], Option[User]] =
    Reader(storage => storage.findById(id))

  def createUserM(user: User): Reader[Storage[User], Long] =
    Reader(storage => storage.insert(user))
}

object ScalazReaderUserService {
  def readerUserService = new ScalazReaderMonadService {
    val userStorage = new SqlStorage {}

    override def findUser(id: Long) = findUserM(id)(userStorage)

    override def createUser(user: User) = createUserM(user)(userStorage)
  }
}