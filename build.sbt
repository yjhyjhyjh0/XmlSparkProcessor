name := "XmlSparkProcessor"

version := "0.1"

scalaVersion := "2.11.11"

val sparkVersion = "2.2.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.logging.log4j" % "log4j-api" % "2.13.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.13.1",
  "org.mockito" % "mockito-all" % "1.10.19" % Test
)