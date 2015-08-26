package com.izmailoff.shared

case class User(id: Long, username: String)

trait UserCrud[T] {
  def findUser(id: Long): Option[T]

  def createUser(user: T): Long
}

trait UserCrudDb extends UserCrud[User] with Storage[User] {
  override def findUser(id: Long): Option[User] = findById(id)

  override def createUser(user: User): Long = insert(user)
}

trait UserCrudLdap extends UserCrud[User] with Storage[User] {
  override def findUser(id: Long): Option[User] = findById(id)

  override def createUser(user: User): Long = insert(user)
}

