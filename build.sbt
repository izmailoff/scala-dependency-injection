
name := "scala-dependency-injection"

version := "0.1"
     
scalaVersion := "2.11.7"

javacOptions ++= Seq("-target", "1.7")

libraryDependencies ++= {
  Seq(
    "org.specs2" %% "specs2-core" % "3.6.4" % "test"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")

