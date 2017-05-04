package softuni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.constants.PageTitle;
import softuni.constants.View;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("view", View.HomeIndexView);
        model.addAttribute("title", PageTitle.HomePageTitle);

        return View.BaseLayoutView;
    }
}
