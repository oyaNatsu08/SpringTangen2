package com.example.springwebtask.controller;

import com.example.springwebtask.entity.UsersRecord;
import com.example.springwebtask.form.LoginForm;
import com.example.springwebtask.service.ManagementService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManagementController {

    @Autowired
    private ManagementService mgmtService;

    @Autowired
    private HttpSession session;

    @GetMapping("login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm ) {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/login";
        } else {
            UsersRecord user = mgmtService.findById(loginForm.getId());

            //ログインチェック
            if (user == null) {
                model.addAttribute("error", "IDまたはパスワードが不正です");
                return "/login";
            } else {
                session.setAttribute("name", user.name());
                return "/menu";
            }

        }
    }

    @GetMapping("/logout")
    public String logout(@ModelAttribute("loginForm") LoginForm loginForm) {
        session.invalidate(); // セッションを無効化する
        return "/login";
    }

    @GetMapping("/menu")
    public String top() {
        return "/menu";
    }
}
