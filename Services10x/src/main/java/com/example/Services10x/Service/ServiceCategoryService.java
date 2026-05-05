package com.example.Services10x.Service;

import com.example.Services10x.Model.ServiceCategory;
import com.example.Services10x.Repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceCategoryService {
    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    public ServiceCategory CreateService(ServiceCategory serviceCategory) {
        if(serviceCategoryRepository.findByName(serviceCategory.getName()).isPresent()) {
            throw new RuntimeException("Category already exists");
        }
        else {
            ServiceCategory serviceCategory1 = new ServiceCategory();
            serviceCategory1.setName(serviceCategory.getName());
            serviceCategory1.setDescription(serviceCategory.getDescription());
            return serviceCategoryRepository.save(serviceCategory1);
        }


    }

    public List<ServiceCategory> AllServices() {

        return serviceCategoryRepository.findAll();
    }
}
