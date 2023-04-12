


## What is scala

Scala is a general-purpose programming language providing support for both object-oriented programming and functional programming. The language has a strong static type system. Designed to be concise, many of Scala's design decisions are aimed to address criticisms of Java.

## Question
You have been given a CSV file containing student grades in the following format:

StudentID,English,Physics,Chemistry,Maths
1,80,70,90,85

2,75,80,70,90

3,85,90,80,75

4,70,85,90,80

5,60,75,70,90

Write a program to calculate the average grade for each student and then the class average. The program should use the Future API to perform the calculations asynchronously and implement the following functions:

 parseCsv: This function takes a path to the CSV file and returns a Future [List[Map[String, String]]] representing the parsed CSV data. The Map object should contain the column name as the key and the cell value as the value. If the file does not exist or cannot be read, the Future should fail with an appropriate error message.

 calculateStudentAverages: This function takes a Future[List[Map[String, String]]] representing the parsed CSV data and returns a Future[List[(String, Double)]] representing the average grade for each student. The List object contains a tuple for each student, where the first element is the student ID and the second element is the average grade. If the input Future fails, the calculateStudentAverages function should return a failed Future with the same error.

 calculateClassAverage: This function takes a Future[List[(String, Double)]] representing the student averages and returns a Future[Double] representing the class average. The class average should be calculated as the arithmetic mean of the student averages. If the input Future fails, the calculateClassAverage function should return a failed Future with the same error

 calculateGrades: Use the first three functions to implement this function that takes a path to the CSV file and returns a Future[Double] representing the class average. This function should use the map, flatMap, and recover callbacks of the Future API to chain the asynchronous operations together and handle errors. Alternatively, you can also use for-comprehension to chain together multiple map and flatMap calls.

### We have to calculate student marks average and calculate class average marks

### For Logger
Use Dependencies

libraryDependencies ++= Seq(

  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % "1.3.5"
)


### output 


13:35:43.049 [main] INFO com.knoldus.Driver$ - Students Average

(1,81.25)

(2,78.75)

(3,82.5)

(4,81.25)

(5,73.75)

- Class Average is : 79.5

for printing some output i use logger



## Run Locally

Clone the project

```bash
  https://github.com/ajit1865/Session5Assignment1.git
```

