name := "XmlSparkProcessor"

version := "0.1"

scalaVersion := "2.11.11"

val sparkVersion = "2.2.1"
val log4j2Version = "2.8.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  "org.apache.logging.log4j" %% "log4j-api-scala" % "11.0",
  "org.apache.logging.log4j" % "log4j-api" % log4j2Version,
  "org.apache.logging.log4j" % "log4j-core" % log4j2Version,
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4j2Version,
  "org.slf4j" % "slf4j-api" % "1.7.25",

  "org.mockito" % "mockito-all" % "1.10.19" % Test
)
