# CS205-WarmUp-ML-RB-SN-JT

## Meeting
Lets meet up Monday (2.17) at the library.  What times work for you?
* Stanhope: Noon 
* Junda: Noon
* Mary: 
* Robby: Noon

## Project Introduction
This is a CS205B warm up project. We focus on the database about Movie directors and details of the movie..... 
We use Java and Python two programming languages. 



## Notice 0: install sqlite3
1) install https://www.sqlite.org/index.html
2) include in your system environment path

## Notice 1:
Assume you are using IntelliJIDEA, after download from master branch, you need to configure the project.
1) click on edit configurations ->  "+" icon -> select "Application" -> name it as main
2) under Main_class section -> click on "..." on the right of the line -> then choose UserInterface.

## Notice 2: Include sqlite-jdbc-3.30.1.jar file in classpath or libraries

### For IntelliJ-Idea users:
make sure that sqlite-jdbc library is added in the Project Structure. 
* File -> Project Structure -> Libraries -> New Projcet Library -> Java -> add sqlite-jdbc-3.30.1.jar

### For jGrasp users:
* Settings -> class/classPath -> workspace -> classpaths -> new -> add sqlite-jdbc-3.30.1.jar

### Linux terminal users:
Compile and Run in Linux terminal:
1) javac -classpath ".:sqlite-jdbc-3.30.1.jar:" OpSqliteDB.java
2) java -classpath ".:sqlite-jdbc-3.30.1.jar:" OpSqliteDB

## Notice 3: install python3-pandas library

### Windows:
* D:\Python36\Scripts\pip install pandas

### Linux:
* sudo pip install pandas

### Mac:
* pip install pandas

