ConsistencyChecker Documentation

Henry Bell
1-13-18

DESCRIPTION

The ConsistencyChecker is a program designed to model a checker for modules performing operations in a distributed database. The checker will open and parse files in a directory, validate the operation performed by the module and display the results. The results are based on comparing the operation and type based on the consistency level of the operation.

The checker was written with Java 1.8

DESIGN

The checker is composed of two primary classes to evaluate the operation for each module in a history. 

 -The Operation class reads the time, module and type for each operation. 
 -The Action class reads the action values for each operation.
 
The Operation class inherits Action class because the Consistency Level and Value in action is required to determine the pass or failure of the operation.

HOW TO RUN

1. Extract ConsistencyChecker.zip to a location on file system
2. From the terminal, cd into the location where ConsistencyChecker.zip was extracted
3. Type the following command 'java -jar ConsistencyChecker.jar'. The test results will be shown in the console

STRENGTHS

The checker is able to accept any number of history logs with the format of .txt. 
The operations in each log are clearly displayed, including the Time if and when there is a failed case.
There is a summary for the overall performance of all the history logs


