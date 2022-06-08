# BookStore
•	BookStore application using Java 11, Maven and Spring Boot

•	Initial setup done with configuring basic dependencies such as Spring web, Spring Data JPA, H2 DataBase and Lombok

•	Set the application.properties file to configure Spring Datasource, JPA, Hibernate

•	Created a BookStore Entity which maps to the Database table. Annotations from Lombok library are used to reduce the boilerplate code in Entity class

•	Created BookRepository interface that extends from JpaRepository Interface with methods supporting CRUD functions declared in it

•	Created a BookStoreService Interface with methods supporting Read, Write, Delete, Update declared in it

•	BookStoreServiecImpl class implements the functionalities of the BookService Interface

•	BookController class which handles the HTTP requests and sends back response to the client
