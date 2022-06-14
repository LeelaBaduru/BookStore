# BookStore
•	BookStore application using Java 11, Maven and Spring Boot

•	Initial setup done with configuring basic dependencies such as Spring web, Spring Data JPA, H2 DataBase and Lombok

•	Set the application.yml file to configure Spring Datasource, JPA, Hibernate

•	Created a Book and Author Entities having OneToMany and ManyToOne relationship.  

•	Created BookRepository interface that extends from JpaRepository Interface with methods supporting CRUD functions declared in it

•	Created a BookStoreService Interface with methods supporting Read, Write, Delete, Update declared in it

•	BookStoreServiecImpl class implements the functionalities of the BookService Interface

•	BookController and AuthorController classes which handles the HTTP CREATE, READ, UPDATE, DELETE requests and sends back response to the client

•	Exception handled on request handler methods using @ControllerAdvice
