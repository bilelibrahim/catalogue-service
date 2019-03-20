package org.sid.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Bilel on 20/03/2019.
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
public class Category {
    @Id
    private String id;
    private String name;
    // je sauvegarde les produits dans un autre fichier, dans categorie je sauvegarde seulement l'id du produit
    @DBRef
    private Collection<Product> products = new ArrayList<Product>();
}
