package com.izmailoff.cake.inherrited

import com.izmailoff.shared._

object InitOrderComparison {
  // overrides too late (NPE)
  def overrideIsTooLate = new UserCrudWithFieldImpl[User] {
    override val storage = new LdapStorage {}
  }

  // storage first, then the service
  def correctInitOrder = new UserStorageProvider with UserCrudWithFieldImpl[User]

  // storage is not ready yet (NPE)
  def wrongInitOrder = new UserCrudWithFieldImpl[User] with UserStorageProvider
}


trait UserCrudWithFieldImpl[T] extends UserCrud[T] {
  val storage: Storage[T]

  override def findUser(id: Long): Option[T] = storage.findById(id)

  override def createUser(user: T): Long = storage.insert(user)

  val defaultUser = findUser(0) // lazy or def qualifier fixes it
}


trait UserStorageProvider {
  val storage: Storage[User] = new SqlStorage {}
}
