package com.izmailoff.reader

case class MyReader[-From, +To](wrappedF: From => To) {
  def apply(c: From): To = wrappedF(c)

  def map[ToB](transformF: To => ToB): MyReader[From, ToB] =
    MyReader(c => transformF(wrappedF(c)))

  def flatMap[FromB <: From, ToB](f: To => MyReader[FromB, ToB]): MyReader[FromB, ToB] =
    MyReader(c => f(wrappedF(c))(c))
}

