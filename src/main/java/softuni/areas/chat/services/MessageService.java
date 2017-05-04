package softuni.areas.chat.services;

import softuni.areas.chat.models.binding.MessageBindingModel;
import softuni.areas.chat.models.view.MessageHistoryModel;
import softuni.areas.chat.models.view.MessageViewModel;

import java.util.List;

public interface MessageService {
    void create(MessageBindingModel mbm, String senderEmail);

    List<MessageHistoryModel> getHistory(Long id, String name);
}
