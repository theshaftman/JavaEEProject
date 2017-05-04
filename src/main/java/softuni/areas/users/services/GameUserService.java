package softuni.areas.users.services;

import softuni.areas.tasks.models.view.ChatUserModel;
import softuni.areas.users.entities.User;

import java.util.List;


public interface GameUserService {

    User getByEmail(String email);
    List<ChatUserModel> getUsersChat(String name);

    User getById(Long id);
}
