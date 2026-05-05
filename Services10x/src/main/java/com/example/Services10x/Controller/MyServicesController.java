package com.example.Services10x.Controller;

import com.example.Services10x.DTO.MyServiceDTO;
import com.example.Services10x.Model.MyServices;
import com.example.Services10x.Service.MyServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyServicesController {

    @Autowired
    MyServicesService myServicesService;

    @PostMapping("/provider/service")
    MyServices registerService(@RequestBody MyServiceDTO myServiceDTO,Authentication auth){
        return myServicesService.serviceRegistry(myServiceDTO, auth);

    }
}
