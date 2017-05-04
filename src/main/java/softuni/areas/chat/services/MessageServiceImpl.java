package softuni.areas.chat.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import softuni.areas.chat.entities.Message;
import softuni.areas.chat.models.binding.MessageBindingModel;
import softuni.areas.chat.models.view.MessageHistoryModel;
import softuni.areas.chat.models.view.MessageViewModel;
import softuni.areas.chat.repositories.MessageRepository;
import softuni.areas.users.entities.User;
import softuni.areas.users.services.GameUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final GameUserService gameUserService;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(GameUserService gameUserService, MessageRepository messageRepository, SimpMessagingTemplate simpMessagingTemplate, ModelMapper modelMapper) {
        this.gameUserService = gameUserService;
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(MessageBindingModel mbm, String senderEmail) {
        User sender = this.gameUserService.getByEmail(senderEmail);
        User receiver = this.gameUserService.getById(mbm.getReceiverId());

        Message message = new Message(mbm.getMessage(), sender, receiver);

        this.messageRepository.save(message);

        String url = "/chat/" + receiver.getEmail() + "/msg/" + sender.getId();

        MessageViewModel mvm = new MessageViewModel(message.getMessage(), message.getDate().getTime());

        this.simpMessagingTemplate.convertAndSend(url, mvm);
    }

    @Override
    public List<MessageHistoryModel> getHistory(Long id, String name) {
        User user = this.gameUserService.getByEmail(name);

        List<Message> messages = this.messageRepository.findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByDate(id, user.getId(), user.getId(), id);

        List<MessageHistoryModel> result = new ArrayList<>();

        for (Message message : messages) {
            MessageHistoryModel mhm = new MessageHistoryModel();

            this.modelMapper.map(message, mhm);

            mhm.setDate(message.getDate().getTime());

            result.add(mhm);
        }

        return result;
    }
}
