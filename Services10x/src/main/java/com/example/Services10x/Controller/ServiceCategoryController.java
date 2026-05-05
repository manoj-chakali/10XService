package com.example.Services10x.Controller;

import com.example.Services10x.Model.ServiceCategory;
import com.example.Services10x.Service.ServiceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServiceCategoryController {

    @Autowired
    ServiceCategoryService serviceCategoryService;

//    @PostMapping("/admin/categories")
//    ServiceCategory ServiceCategoryCreation(@RequestBody ServiceCategory serviceCategory){
//
//        return serviceCategoryService.CreateService(serviceCategory);
//
//    }
    @PostMapping("/admin/categories")
    public ResponseEntity<?> createCategory(@RequestBody ServiceCategory serviceCategory) {
        try {
            return ResponseEntity.ok(serviceCategoryService.CreateService(serviceCategory));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", ex.getMessage()));
        }
    }

    @GetMapping("/services")
    List<ServiceCategory> Services(){
        return serviceCategoryService.AllServices();
    }
}
