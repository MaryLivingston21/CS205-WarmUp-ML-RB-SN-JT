# CS205-WarmUp-ML-RB-SN-JT

## Meeting
Lets meet up Monday at the library. What times work for you?
* Stanhope: Noon 
* Junda: Noon


## Notice 0:
Assume you are using IntelliJIDEA, after download from master branch, you need to configure the project.
* click on edit configurations ->  "+" icon -> select "Application" -> name it as main, 
* under Main_class section -> click on "..." on the right of the line -> then choose UserInterface.

## Notice 1: Include sqlite-jdbc-3.30.1.jar file in classpath or libraries

### For IntelliJ-Idea users:
make sure that sqlite-jdbc library is added in the Project Structure. 
* File -> Project Structure -> Libraries -> New Projcet Library -> Java -> add sqlite-jdbc-3.30.1.jar

### For jGrasp users:
* Settings -> class/classPath -> workspace -> classpaths -> new -> add sqlite-jdbc-3.30.1.jar

### Linux terminal users:
Compile and Run in Linux terminal:
1) javac -classpath ".:sqlite-jdbc-3.30.1.jar:" OpSqliteDB.java
2) java -classpath ".:sqlite-jdbc-3.30.1.jar:" OpSqliteDB

## Notice 2: install python3-pandas library

### Windows:
* D:\Python36\Scripts\pip install pandas

### Linux:
* sudo pip install pandas

### Mac:
* pip install pandas
