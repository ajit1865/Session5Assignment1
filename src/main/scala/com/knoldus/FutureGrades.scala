package com.knoldus

import com.typesafe.scalalogging.Logger
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.io.Source

case class StudentData(id: String, english: Double, physics: Double, chemistry: Double, maths: Double)

class FutureGrades {
  val logger = Logger(getClass.getName)
  implicit val ec: ExecutionContext = ExecutionContext.global

  //method parseCsv to convert path of string in Future[List[Map[String, String]]]
  def parseCsv(path: String): Future[List[Map[String, String]]] = Future {
      val lines = Source.fromFile(path).getLines().toList
      val header = lines.head.split(",").toList
      lines.tail.map { line =>
        val values = line.split(",").toList
        val data = header.zip(values).toMap
        data
      }
  }

  // method calculatedStudedAverages calculates student average
  def calculateStudentAverages(convertedCsvData: Future[List[Map[String, String]]]): Future[List[(String, Double)]] = {
    convertedCsvData.map { students =>
      students.map { student =>
        val id = student("StudentID")
        val english = student("English").toDouble
        val physics = student("Physics").toDouble
        val chemistry = student("Chemistry").toDouble
        val maths = student("Maths").toDouble
        val average = (english + physics + chemistry + maths) / 4.0
        (id, average)
      }
    }
  }
  // method to caluculate class average
  def calculateClassAverage(calculatedStudentAverage: Future[List[(String, Double)]]): Future[Double] = {
    calculatedStudentAverage.map { averages =>
      val sum = averages.map(_._2).sum
      val count = averages.length
      sum / count
    }
  }

  //method to calculate grades
  def calculateGrades(path: String): Future[Double] = {
    val parsedData = parseCsv(path)
    val studentAvg = calculateStudentAverages(parsedData)
    val classAvg = calculateClassAverage(studentAvg)
    classAvg.recoverWith {
      case e => Future.failed(new Exception(s"Unable to calculate class average! ${e.getMessage}"))
    }
  }
}
