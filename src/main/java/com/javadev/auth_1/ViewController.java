package com.javadev.auth_1;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties.UiService.LOGGER;

@Controller
public class ViewController {

    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken token, Model model) {


        if (token == null) {
            model.addAttribute("error", "You are not authenticated. Please log in.");
            return "custom_login"; // Redirect to login page with error message
        }

        System.out.println("Token attributes: " + token.getPrincipal().getAttributes());

        model.addAttribute("name", token.getPrincipal().getAttribute("name"));
        model.addAttribute("email", token.getPrincipal().getAttribute("email"));
        model.addAttribute("photo", token.getPrincipal().getAttribute("picture"));
        return "user-profile";
    }

    @GetMapping("/login")
    public String login() {
        return "custom_login";
    }
}