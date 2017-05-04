package softuni.areas.users.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import softuni.areas.tasks.models.view.ChatUserModel;
import softuni.areas.users.entities.User;
import softuni.areas.users.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameUserServiceImpl implements GameUserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public GameUserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        }

        return user;
    }

    @Override
    public List<ChatUserModel> getUsersChat(String email) {

        List<User> users = this.userRepository.findAllByEmailIsNot(email);
        List<ChatUserModel> result = new ArrayList<>();

        for (User user : users) {
            ChatUserModel chatUserModel = new ChatUserModel();
            this.modelMapper.map(user, chatUserModel);
            result.add(chatUserModel);
        }

        return result;
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findOne(id);
    }
}
