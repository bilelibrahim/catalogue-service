package org.sid.dao;

import org.sid.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Bilel on 20/03/2019.
 */
@RepositoryRestResource
public interface ProductRepository extends MongoRepository<Product, String>{
}
