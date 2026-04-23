package com.example.Services10x.Controller;

import com.example.Services10x.DTO.UserDetailResponse;
import com.example.Services10x.Model.User;
import com.example.Services10x.Model.UserRoles;
import com.example.Services10x.Repository.UserRepository;
import com.example.Services10x.Service.UserService;
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



    @PostMapping("/admin/register")
    public String RegisterAdmin(@RequestBody User user){
        if(userRepository.findByusername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.ROLE_ADMIN);
        userRepository.save(user);
        return "Admin Saved Successfully";
    }

    @PostMapping("/provider/register")
    public String RegisterProvider(@RequestBody User user){
        if(userRepository.findByusername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.ROLE_PROVIDER);
        userRepository.save(user);
        return "User Saved Successfully";
    }
    @PostMapping("/customer/register")
    public String RegisterCustomer(@RequestBody User user){
        if(userRepository.findByusername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.ROLE_CUSTOMER);
        userRepository.save(user);
        return "User Saved Successfully";
    }

    @GetMapping("/profile")
    public Optional<UserDetailResponse> GettingByUsername(Authentication auth){
         User user1=userRepository.findByusername(auth.getName()).orElseThrow(()->new RuntimeException("User not found"));
         UserDetailResponse userDetailResponse1 = new UserDetailResponse();
         userDetailResponse1.setUsername(user1.getUsername());
         userDetailResponse1.setPassword(user1.getPassword());
         userDetailResponse1.setRole(String.valueOf(user1.getRole()));

         return Optional.of(userDetailResponse1);

    }

    @GetMapping("/GetAllAdmins")
    public List<User> GetAllAdmins(){
        return userRepository.findByRole(UserRoles.ROLE_ADMIN);
    }

    //Added by Siva
    @GetMapping("/GetAllProviders")
    public List<User>GetAllProviders(){
      //  System.out.println("entered in Getallusers");
        return userRepository.findByRole(UserRoles.ROLE_PROVIDER);

    }


    @GetMapping("/GetAllCustomers")
    public List<User>GetAllCustomers(){
      //  System.out.println("entered in Getallusers");
        return userRepository.findByRole(UserRoles.ROLE_CUSTOMER);

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
