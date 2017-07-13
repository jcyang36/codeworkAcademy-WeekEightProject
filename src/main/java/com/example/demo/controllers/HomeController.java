package com.example.demo.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.example.demo.configs.CloudinaryConfig;
import com.example.demo.models.Photo;
import com.example.demo.models.Post;
import com.example.demo.models.User;

import com.example.demo.repositories.PhotoRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * Created by student on 7/10/17.
 */
@Controller
public class HomeController {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PostRepository postRepository;


    @Autowired
    CloudinaryConfig cloudc;

    /*    FIRST STEPS        */
    @RequestMapping("/login")
    public String login(){
        return "login";

    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registration";
        }
        if(user.getRoleName().equals("user"))
        {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        if(user.getRoleName().equals("admin"))
        {
            userService.saveAdmin(user);
            model.addAttribute("message", "Admin Account Successfully Created");
        }
        return "home";
    }
    public UserValidator getUserValidator() {
        return userValidator;
    }
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }


    /*    FIRST STEPS        */


    @RequestMapping("/")
    public String home(Model model){

        return "home";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model){
        model.addAttribute("photo", new Photo());
        return "upload";
    }
    @PostMapping("/upload")
    public String singleImageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, @ModelAttribute Photo photo, Model model){
        if (file.isEmpty()){
            model.addAttribute("message","Please select a file to upload");
            return "upload";
        }
        try {
            Map uploadResult =  cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));

            model.addAttribute("imageurl", uploadResult.get("url"));
            String filename = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
            model.addAttribute("sizedimageurl", cloudc.createUrl(filename,300,400, "fill", "saturation:0"));
            photo.setPhotoname(filename);
            photo.setPhotosrc((String)  cloudc.createUrl(filename,300,400, "fill","saturation:0"));
            photoRepository.save(photo);
            Photo photo1=new Photo();
            photo1.setPhotoname(filename+"sepia");
            model.addAttribute("sepiaimageurl", cloudc.createUrl(filename,300,400, "fill", "sepia"));
            String photosrc=cloudc.createUrl(filename,300,400, "fill", "sepia");
            photo1.setPhotosrc(photosrc);
            photoRepository.save(photo1);
            Photo photo2=new Photo();
            photo2.setPhotoname(filename+"pixelate");
            model.addAttribute("pixelateimageurl", cloudc.createUrl(filename,300,400, "fill", "pixelate"));
            photosrc=cloudc.createUrl(filename,300,400, "fill", "pixelate");
            photo2.setPhotosrc(photosrc);
            photoRepository.save(photo2);
            Photo photo3 = new Photo();
            photo3.setPhotoname(filename+"red");
            model.addAttribute("redimageurl", cloudc.createUrl(filename,300,400, "fill", "red:50"));
            photosrc= cloudc.createUrl(filename,300,400, "fill", "red:50");
            photo3.setPhotosrc(photosrc);
            photoRepository.save(photo3);
        } catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "Sorry I can't upload that!");
        }
        return "upload";
    }



    @GetMapping("/makepost")
    public String postMaker(Model model) {
        model.addAttribute("post", new Post());
        /*model.addAttribute("photos", photoRepository.findAll());*/

        return "mypost";
    }
    @RequestMapping("/makepost/{id}")
    public String postform(@PathVariable("id") int id, Model model)
    {
        Photo img=photoRepository.findOne(id);
        Post post=new Post();
        post.setImageUrl(img.getPhotosrc());
        model.addAttribute("post", post);


        return "postconfirm";
    }


    @RequestMapping("/posts")
    public String viewposts(Model model) {
        //Find all by username
        /*String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer id =  userRepository.findByUsername(username).getId();
        model.addAttribute("postList", postRepository.findAllByUserId(id.intValue()));
        */
        //Find all
        model.addAttribute("postList", postRepository.findAll());
        return "viewposts";
    }

    @PostMapping("/new_post")
    public String addpost(@Valid @ModelAttribute("post")Post post, BindingResult result, Model model) {
        model.addAttribute("post", post);
        if (result.hasErrors()) {
            return "postconfirm";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByUsername(username);
        Long id = user.getId();
        post.setUserId(id.intValue());
        postRepository.save(post);
        post=postRepository.findTop1ByUserIdOrderByIdDesc((int) user.getId()).get(0);

        return "redirect:/posts";

    }


    @RequestMapping("/like/{id}")
    public String likepost(@PathVariable("id") int id, Model model)
    {
        Post post=postRepository.findOne(id);
        model.addAttribute("post",post);
        int likecount=1;
        model.addAttribute("userlike", likecount);
        return "postz";
    }
    @RequestMapping("/showposts/{id}")
    public String showpost(@PathVariable("id") int id, Model model)
    {
        Post post=postRepository.findOne(id);
        model.addAttribute("post",post);

        return "postz";
    }



     /* @RequestMapping("/showposts/sepia")
    public String showsepia(@RequestParam("sepia")){
    }*/


    @RequestMapping("/myfriends")
    public String showMyFriends(Model model){
        model.addAttribute("friendList", userRepository.findAll());
        int likecount=1;
        model.addAttribute("userlike", likecount);


        return "myfriends";
    }





}
