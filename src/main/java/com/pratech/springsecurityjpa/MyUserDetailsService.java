package com.pratech.springsecurityjpa;

import com.pratech.springsecurityjpa.models.MyUserDetails;
import com.pratech.springsecurityjpa.models.User;
import com.pratech.springsecurityjpa.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User newuser=restTemplate.getForObject("http://localhost:8080/rest/users/get/newuser/"+username,User.class);
        logger.info("newuser for api: "+newuser.getUserName());
//        logger.info("calling from loadUserByUserName "+username);
//          Optional<User> user= userRepository.findByUserName(username);
//
//        ArrayList<User> userList = (ArrayList<User>)userRepository.findAll();
//
//        for (User u : userList) {
//            logger.info("from db user object "+u.getUserName());
//        }
//          logger.info(userList.toString());
//
//        logger.info("calling from loadUserByUserName done for "+username+" "+user.get().toString());
//          //if Optional doesnt have a value, throw a exception
//        user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        UserDetails user1 = user.map(MyUserDetails::new).get();
//        logger.info("getting from UserDetails "+user1.getUsername());
//        logger.info("getting from UserDetails "+user1.getPassword());

//           return user.map(MyUserDetails::new).get(); //mapping optional type to User type
//        user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//
//        UserDetails user1 = newuser.map(MyUserDetails::new).get();
        UserDetails user1=new MyUserDetails(newuser);

        return user1;
    }
}
