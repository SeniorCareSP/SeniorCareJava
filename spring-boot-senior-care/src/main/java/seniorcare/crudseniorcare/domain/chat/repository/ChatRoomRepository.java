package seniorcare.crudseniorcare.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.chat.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.senderId = :userId OR cr.recipientId = :userId")
    List<ChatRoom> findChatsByUserId(Integer userId);

    List<ChatRoom> findBySenderId(String senderId);

}
