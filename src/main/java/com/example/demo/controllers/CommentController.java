package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.models.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by student on 7/14/17.
 */
@Controller
public class CommentController {
@Autowired
UserRepository userRepository;
@Autowired
CommentRepository commentRepository;

    @RequestMapping("/comment")
    private String goComment(Model model, Principal principal, @ModelAttribute Comment comment){
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user",user);
        comment.setIdUsr(user.getId());
        commentRepository.save(comment);
        return "postz";
    }



}
