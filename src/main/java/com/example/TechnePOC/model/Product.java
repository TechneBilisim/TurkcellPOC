package com.example.TechnePOC.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Data
@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {


    @Id
    private String id;

    private String msisdn;

    private String username;

    private String lineType;

    private boolean lineStatus;

    private String paymentType;

    private String shortNumber;


}
