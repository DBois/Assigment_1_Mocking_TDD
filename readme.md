# Mocking and TDD  
  * [Contract](https://github.com/DBois/BankContract)  
  * [Backend](https://github.com/DBois/Assigment_1_Mocking_TDD) 
## Assignment 3. Database Testing  
Our proposed solution of [this assignment](https://datsoftlyngby.github.io/soft2020spring/resources/db4fc3df-03-assignment-database.pdf)  

Inside the `src.main.java.dk.cphbusiness.banking.backend.datalayer` package, you will find an interface for the `DataAccessObject`, and implementation of select methods in the `DAO` class and a `DBConnector` class to establish a connection to the database.  

Inside the `src.main.java.dk.cphbusiness.banking.backend.datalayer` you will find a `TestDatabaseUtility` which holds utility methods used for testing. A `DatabaseTest` class which is used to test the connection to the database and creation and deletion of the database. And finally the `DAOTest` class, which tests methods from our `DAO` class.

Results for the tests:  
![](./db_test_results.png)
## Assignment 1. Test coverage and results

Our proposed solution of [this assignment](https://datsoftlyngby.github.io/soft2020spring/resources/85f09312-01-assignment-mocking.pdf).


### Our test coverage of the classes used for the project

![](./coverage.png)

### Our test results when run

![](./test_results.png)
