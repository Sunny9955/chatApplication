package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection,Long> {
}
