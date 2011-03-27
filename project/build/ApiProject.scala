import sbt._

class SimplenoteApiProject(info: ProjectInfo) extends DefaultProject(info) {
  val httpClient = "org.apache.httpcomponents" % "httpclient" % "4.1"
  val jodaTime = "joda-time" % "joda-time" % "1.6.2"
  val guava = "com.google.guava" % "guava" % "r08"
  // SLF4J for a nicer logging interface
  val slf4j = "org.slf4j" % "slf4j-api" % "1.6.1"

  val junitInterface = "com.novocode" % "junit-interface" % "0.6" % "test->default"
  val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test->default"
  val slf4jJDK = "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "test->default"

  // override looking for jars in ./lib
  override def dependencyPath = sourceDirectoryName / mainDirectoryName / "lib"
  // override path to managed dependency cache
  override def managedDependencyPath = "project" / "lib_managed"
  // java compile options
  override def javaCompileOptions = super.javaCompileOptions ++ List(JavaCompileOption("-g"))
}