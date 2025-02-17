package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT('%', :userEmail, '%')")
    List<User> findUser(@Param("userEmail") String userEmail);


}
