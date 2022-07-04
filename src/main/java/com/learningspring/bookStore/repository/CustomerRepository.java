package com.learningspring.bookStore.repository;

 
import com.learningspring.bookStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.username = :username")
    public Customer getUserByUsername(@Param("username") String username);
}
  