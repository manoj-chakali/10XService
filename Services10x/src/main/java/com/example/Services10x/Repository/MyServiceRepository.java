package com.example.Services10x.Repository;

import com.example.Services10x.Model.MyServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyServiceRepository extends JpaRepository<MyServices,Long> {
}
