package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SignUpRepository extends JpaRepository<User,String> {

}
