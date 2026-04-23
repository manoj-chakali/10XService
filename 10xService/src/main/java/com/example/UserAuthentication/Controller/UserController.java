package com.example.UserAuthentication.Controller;

import com.example.UserAuthentication.DTO.UserDetailResponse;
import com.example.UserAuthentication.Model.User;
import com.example.UserAuthentication.Repository.UserRepository;
import com.example.UserAuthentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @Autowired
    PasswordEncoder passwordEncoder;



    @PostMapping("/admin/create")
    public String RegisterAdmin(@RequestBody User user){
        if(userRepository.findByusername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
        return "Admin Saved Successfully";
    }

    @PostMapping("/user/create")
    public String RegisterUser(@RequestBody User user){
        if(userRepository.findByusername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "User Saved Successfully";
    }

    @GetMapping("/profile")
    public Optional<UserDetailResponse> GettingByUsername(Authentication auth){
         User user1=userRepository.findByusername(auth.getName()).orElseThrow(()->new RuntimeException("User not found"));
         UserDetailResponse userDetailResponse1 = new UserDetailResponse();
         userDetailResponse1.setUsername(user1.getUsername());
         userDetailResponse1.setPassword(user1.getPassword());
         userDetailResponse1.setRole(user1.getRole());

         return Optional.of(userDetailResponse1);

    }

    @GetMapping("/GetAllAdmins")
    public List<User> GetAllAdmins(){
        return userRepository.findByRole("ROLE_ADMIN");
    }

    @GetMapping("/GetAllUsers")
    public List<User>GetAllUsers(){
        return userRepository.findByRole("ROLE_USER");
    }

    @DeleteMapping("/admin/Deleting/{username}")
    public String DeleteByUserName(@PathVariable String username){
        if(userRepository.findByusername(username).isPresent()) {
            userRepository.deleteByusername(username);
            return "user deleted";
        }
        else{
            return "user not found";
        }

    }

}
