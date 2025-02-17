package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u WHERE u.email = :userEmail")
    User findByEmail(@Param("userEmail") String userEmail);
}
