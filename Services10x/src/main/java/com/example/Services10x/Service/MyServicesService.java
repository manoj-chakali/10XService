package com.example.Services10x.Service;

import com.example.Services10x.DTO.MyServiceDTO;
import com.example.Services10x.Model.MyServices;
import com.example.Services10x.Model.ServiceCategory;
import com.example.Services10x.Model.User;
import com.example.Services10x.Model.UserRoles;
import com.example.Services10x.Repository.MyServiceRepository;
import com.example.Services10x.Repository.ServiceCategoryRepository;
import com.example.Services10x.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class MyServicesService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    MyServiceRepository myServiceRepository;

    public MyServices serviceRegistry(MyServiceDTO myServices, Authentication auth) {

        ServiceCategory serviceCategory = serviceCategoryRepository.findById(myServices.getCategoryId()).orElseThrow(()->new RuntimeException("Category not found"));

        String user = auth.getName();
        User provider = userRepository.findByusername(user).orElseThrow(()->new RuntimeException("user not found"));
        if(provider.getRole() != UserRoles.ROLE_PROVIDER) {
            throw new RuntimeException("Only provider can create service");
        }



        System.out.println("Provider fetched: " + provider);
        System.out.println("Provider ID: " + provider.getId());


        MyServices myServices1 = new MyServices();
        myServices1.setName(myServices.getName());
        myServices1.setCategory(serviceCategory);
        myServices1.setProvider(provider);
        myServices1.setPrice(myServices.getPrice());
        myServices1.setDescription(myServices.getDescription());
       return myServiceRepository.save(myServices1);

    }
}
