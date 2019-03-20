package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by A662292 on 20/03/2019.
 */
@Document
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Product {
    private String id;
    private String name;
    private double price;
    @DBRef
    private Category category;
}
