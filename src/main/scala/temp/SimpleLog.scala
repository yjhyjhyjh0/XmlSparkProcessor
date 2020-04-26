package temp

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.scala.Logging

class SimpleLog extends Logging {

  def test(): Unit = {
    //    val log = LogManager.getLogger(classOf[SimpleLog])
    //    val log = LogManager.getLogger("test")

//    val LOGGER: Logger = LogManager.getLogger("TestLogger")
//    //    val log = Logger.getLogger(classOf[SimpleLog])
//
//    LOGGER.info("Hello info")
//    LOGGER.debug("Hello from Log4j 2")
//    // in old days, we need to check the log level to increase performance
//    /*if (logger.isDebugEnabled()) {
//                logger.debug("{}", getNumber());
//            }*/
//    // with Java 8, we can do this, no need to check the log level
//    LOGGER.debug("{}", () => getNumber)
//    println("test")
    logger.info("Doing stuff")
    logger.error("Doing stuff")

    def getNumber = 5
  }
}

object SimpleLog {

  def main(args: Array[String]): Unit = {
    val myO = new SimpleLog()
    myO.test()
    println("helloi")
  }
}

//import org.apache.logging.log4j.scala.Logging
//import org.apache.logging.log4j.Level
//
//class MyClass extends BaseClass with Logging {
//  def doStuff(): Unit = {
//    logger.info("Doing stuff")
//  }
//  def doStuffWithLevel(level: Level): Unit = {
//    logger(level, "Doing stuff with arbitrary level")
//  }
//}