package com.pratech.springsecurityjpa;

import com.pratech.springsecurityjpa.models.User;
import com.pratech.springsecurityjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class HomeResource {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

//    @RequestMapping("/")
//    public String home(){
//        return ("<h1>WELCOME</h1>");
//    }

    @RequestMapping("/")
    public RedirectView home(){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8087/");
        return redirectView;
    }


//    @RequestMapping("/user")
//    public RedirectView user(){
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("http://localhost:8087/");
//        return redirectView;
//    }

//    @RequestMapping("/admin")
//    public String admin(){
//        return ("<h1>WELCOME ADMIN</h1>");
//    }

    @PostMapping(value="/add/user", consumes = "application/json")
    public void addComUser(@RequestBody User user){
        userRepository.save(user);
    }

    @RequestMapping("/get/user/{user}")
    public Optional<User> getUser(@PathVariable("user") String user){
        return userRepository.findByUserName(user);
    }

}
