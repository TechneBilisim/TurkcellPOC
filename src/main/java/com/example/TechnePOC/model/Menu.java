package com.example.TechnePOC.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Builder
@Data
@Document(collection = "menu")
public class Menu {

    @Id
    private String id;

    private String name;

}
