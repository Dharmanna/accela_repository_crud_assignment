1.Create local folder where you can checkout the code from public git repository
  Example: /d/Temp
  
2.Clone the repository run below command
  git clone https://github.com/Dharmanna/accela_repository_crud_assignment.git
  
3.You will see /d/Temp/accela_repository_crud_assignment folder with (main) branch

4.Switch to master branch

  git checkout master
  
5.Pull all code just to make sure you have all code

  git pull
  
6.Import this project to your eclipse/sts IDE

  File -->Import -->Existing Maven Projects --> Browse (go to step 3 folder path)-->Finish
  
7.After successful import - build the project - can use maven command line or eclipse egit

  If command line - go to step 3 folder path and run : maven clean install
  
  If eclipse egit -
  
    Right click on project -->Run As --> maven buld
    
    In goals specify : clean install --> apply --> run
    
    Wait until project build
    
8.Upon success -- You will see 17 test ran successful and build completed

9.Go to Eclipse -->  CrudApplication in com.accela.crud package

  Right click --> Run As --> Spring Boot App
  
  You will see --> INFO 7408 --- [main] com.accela.crud.CrudApplication          : Started CrudApplication in 2.343 seconds (JVM running for 2.827)
  
10.Go to browser

  http://localhost:8080/
  
  Because this is entry point you'll see Add person link
  
11. Start performing all operations 
 
    1.	Add Person (id, firstName, lastName)
    2.	Edit Person (firstName, lastName)
    3.	Delete Person (id)
    4.	Add Address to person [multiple required] (id, street, city, state, postalCode)
    5.	Edit Address (street, city, state, postalCode)
    6.	Delete Address (id)
    7.	Count Number of Persons
    8.	List Persons

12.All fields are validated before creating record in in-memory database

13.Have used apache Derby in-memory database

14.Unit test cases  are created using @WebMvcTest, @SpringBootTest and Mockito

15 If you want to see test coverage

    Right click on project -->Coverage As --> Junit Test
    
    Over all Coverage is ~70% (beans and other few won't covered in overall so 70%) and service class coverage is 100%
    
