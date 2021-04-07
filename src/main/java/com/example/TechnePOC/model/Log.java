package com.example.TechnePOC.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Builder
@Data
@Document(collection = "log")
public class Log {

    @Id
    String Id;

    Date date;

    String serviceName;

    String params;

}
