package com.knoldus

import com.typesafe.scalalogging.Logger
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

//Main Driver object to run our FutureGrades class
object Driver extends App {

  implicit val ec: ExecutionContext = ExecutionContext.global
  val futureGrades = new FutureGrades
  val logger = Logger(getClass.getName)
  val filePath = "/home/knoldus/scala programs/Session5Assignment2/src/main/scala/com/knoldus/Grades.csv"
 // val filePath =""
  if(filePath.isEmpty) logger.warn("enter a valid path")
  else {
    val parsedData = futureGrades.parseCsv(filePath)
    val wait1 = Await.result(parsedData, 5.seconds)
    val studentAverages = futureGrades.calculateStudentAverages(parsedData)
    val wait2 = Await.result(parsedData, 5.seconds)
    val classAverage = futureGrades.calculateGrades(filePath)
    val wait3 = Await.result(parsedData, 5.seconds)

    Thread.sleep(100)
    logger.info("Students Average")
    studentAverages.onComplete {
      case Success(value) => print(value.foreach(println))
      case Failure(exception) => println("Failed to calculate student averages: " + exception.getMessage)
    }
    Thread.sleep(100)
    classAverage.onComplete {
      case Success(value) => logger.info(s"Class Average is : $value")
      case Failure(exception) => println("Can't find ")
    }
  }
  // Sleep for a while to allow the Future to complete before the application exits
  Thread.sleep(1000)

}
