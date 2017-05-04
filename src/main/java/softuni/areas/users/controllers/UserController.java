package softuni.areas.users.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import softuni.areas.characters.models.view.CharacterViewModel;
import softuni.areas.characters.services.CharacterService;
import softuni.areas.tasks.models.view.ChatUserModel;
import softuni.areas.users.entities.User;
import softuni.areas.users.services.GameUserService;

import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("users")
public class UserController {

    private final CharacterService characterService;
    private final GameUserService gameUserService;

    @Autowired
    public UserController(CharacterService characterService, GameUserService gameUserService, Gson gson) {
        this.characterService = characterService;
        this.gameUserService = gameUserService;
    }

    @GetMapping("/characters")
    public String allCharacters(Model model, Principal principal) {
        User user = this.gameUserService.getByEmail(principal.getName());
        List<CharacterViewModel> characters = this.characterService.getByUserId(user.getId());

        model.addAttribute("view", "characters/list");
        model.addAttribute("characters", characters);

        return "base-layout";
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChatUserModel>> getUsers(Principal principal) {
        List<ChatUserModel> users = this.gameUserService.getUsersChat(principal.getName());

        return new ResponseEntity<List<ChatUserModel>>(users, HttpStatus.OK);
    }
}
