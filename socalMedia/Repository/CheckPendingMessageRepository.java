package com.socalMedia.socalMedia.Repository;

import com.socalMedia.socalMedia.Model.CheckPendingMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckPendingMessageRepository extends JpaRepository<CheckPendingMessage,Long> {

    @Query("SELECT cm FROM CheckPendingMessage cm WHERE cm.senderEmail = :senderEmail AND cm.receiverEmail = :receiverEmail")
    List<CheckPendingMessage> findAllBySenderEmailAndReceiverEmail(@Param("senderEmail") String senderEmail, @Param("receiverEmail") String receiverEmail);

    @Modifying
    @Transactional
    @Query("DELETE FROM CheckPendingMessage cm WHERE cm.senderEmail = :senderEmail AND cm.receiverEmail = :receiverEmail")
    void deleteBySenderEmailAndReceiverEmail(@Param("senderEmail") String senderEmail, @Param("receiverEmail") String receiverEmail);

    @Query("SELECT cm FROM CheckPendingMessage cm WHERE cm.receiverEmail=:receiverEmail")
    List<CheckPendingMessage>findAllByReceiverEmail(@Param("receiverEmail")String receiverEmail);

}
