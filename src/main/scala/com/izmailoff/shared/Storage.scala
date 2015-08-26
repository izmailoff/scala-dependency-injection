package com.izmailoff.shared

trait Storage[T] {
  def findById(id: Long): Option[T]

  def insert(entity: T): Long
}

trait MutableStorage extends Storage[User] {
  def defaultTestUserName: String

  var maxDbId: Long = 0

  def nextId() = {
    maxDbId += 1
    maxDbId
  }

  var users: Map[Long, User] = Map()

  override def findById(id: Long) = users.get(id)

  override def insert(entity: User): Long = {
    val newId = nextId()
    users += (newId -> entity.copy(id = newId))
    newId
  }
}

trait SqlStorage extends MutableStorage {
  override val defaultTestUserName = "test-SQL"
}

trait LdapStorage extends MutableStorage {
  override val defaultTestUserName = "test-LDAP"
}
