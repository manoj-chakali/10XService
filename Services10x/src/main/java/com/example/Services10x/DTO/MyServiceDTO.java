package com.example.Services10x.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyServiceDTO {

    private String name;
    private String description;
    private Double price;
    private Long categoryId;

}
