package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import softuni.areas.characters.models.view.CharacterAjaxModel;
import softuni.areas.characters.models.view.CharacterMoneyModel;
import softuni.constants.Config;


@Service
public class SocketServiceImpl implements SocketService {
    private final SimpMessagingTemplate template;

    @Autowired
    public SocketServiceImpl(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void sendMoneyUpdate(CharacterMoneyModel character) {
        this.template.convertAndSend(Config.CharacterMoneyDestination, character);
    }

    @Override
    public void sendCharUpdate(CharacterAjaxModel character) {
        this.template.convertAndSend(Config.CharacterInfoDestination, character);
    }
}
