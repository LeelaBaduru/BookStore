package com.learningspring.bookStore.repository;

import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository  extends JpaRepository<Role, Long> {


 /*   @Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role findByName(@Param("name") String name);  */

    public Role findByName(String name);

}
