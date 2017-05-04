package softuni.areas.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import softuni.areas.chat.models.binding.MessageBindingModel;
import softuni.areas.chat.models.view.MessageHistoryModel;
import softuni.areas.chat.models.view.MessageViewModel;
import softuni.areas.chat.services.MessageService;
import softuni.constants.View;

import java.security.Principal;
import java.util.List;

@Controller
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    @PreAuthorize("isAuthenticated()")
    public String getChat(Model model) {
        model.addAttribute("view", "chat/window");
        model.addAttribute("title", "Chat");

        return View.BaseLayoutView;
    }

    // /app/chat
    @MessageMapping("chat")
    public void receiveMessage(MessageBindingModel mbm, Principal principal) {

        this.messageService.create(mbm, principal.getName());
    }

    @GetMapping("/chat/history/{id}")
    public ResponseEntity<List<MessageHistoryModel>> getHistory(@PathVariable Long id, Principal principal){
        List<MessageHistoryModel> messages = this.messageService.getHistory(id, principal.getName());

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
