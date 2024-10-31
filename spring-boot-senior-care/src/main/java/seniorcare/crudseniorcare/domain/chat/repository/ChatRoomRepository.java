package seniorcare.crudseniorcare.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import seniorcare.crudseniorcare.domain.chat.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(Integer senderId, Integer recipientId);

    @Query("SELECT cr FROM ChatRoom cr WHERE cr.senderId = :userId OR cr.recipientId = :userId")
    List<ChatRoom> findChatsByUserId(Integer userId);

    List<ChatRoom> findBySenderId(Integer senderId);

    Optional<ChatRoom> findByChatId(String chatId);


    @Query(value = """
       SELECT c.id AS chatRoomId, c.chat_id AS chatId, c.sender_id AS senderId, c.recipient_id AS recipientId,
              m.id AS messageId, m.content AS content, m.timestamp AS timestamp
       FROM tb_chat_room c
       JOIN (
           SELECT m1.id, m1.chat_id, m1.content, m1.timestamp
           FROM tb_chat_message m1
           INNER JOIN (
               SELECT chat_id, MAX(id) AS last_message_id
               FROM tb_chat_message
               GROUP BY chat_id
           ) AS m2 ON m1.chat_id = m2.chat_id AND m1.id = m2.last_message_id
       ) AS m ON c.chat_id = m.chat_id
       """, nativeQuery = true)
    List<Object[]> findFirstChatAndLastMessage();

    @Query(value = """
       SELECT c.id AS chatRoomId, c.chat_id AS chatId, c.sender_id AS senderId, c.recipient_id AS recipientId,
              m.id AS messageId, m.content AS content, m.timestamp AS timestamp
       FROM tb_chat_room c
       LEFT JOIN (
           SELECT m1.id, m1.chat_id, m1.content, m1.timestamp
           FROM tb_chat_message m1
           INNER JOIN (
               SELECT chat_id, MAX(timestamp) AS latestTimestamp
               FROM tb_chat_message
               GROUP BY chat_id
           ) AS latest_messages ON m1.chat_id = latest_messages.chat_id AND m1.timestamp = latest_messages.latestTimestamp
       ) AS m ON c.chat_id = m.chat_id
       WHERE c.sender_id = :senderId
       ORDER BY m.timestamp DESC
       """, nativeQuery = true)
    List<Object[]> findChatsWithLastMessagesBySenderId(@Param("senderId") Integer senderId);
}
