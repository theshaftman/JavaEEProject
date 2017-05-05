package softuni.areas.play.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.areas.play.models.binding.PlayWordModel;
import softuni.areas.play.services.PlayWordService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("play_words")
@PreAuthorize("isAuthenticated()")
public class PlayWordController {
    private final PlayWordService playWordService;

    private final Gson gson;

    @Autowired
    public PlayWordController(PlayWordService playWordService, Gson gson) {
        this.playWordService = playWordService;
        this.gson = gson;
    }

    @GetMapping("playWord")
    public String createView(Model model, PlayWordModel pwm) {
        model.addAttribute("view", "game/play");
        model.addAttribute("model", pwm);

        return "base-layout";
    }

    @PostMapping("checkAnswer")
    public String checkAnswer(@Valid PlayWordModel ccm) {
        this.playWordService.checkAnswer(ccm);

        return "redirect:/play_words/playWord";
    }
}
