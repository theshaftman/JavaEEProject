package softuni.areas.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.areas.chat.entities.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByDate(Long senderId, Long receiverId, Long rSenderId, Long rReceiverId);
}
