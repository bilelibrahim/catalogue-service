package org.sid.dao;

import org.sid.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by A662292 on 20/03/2019.
 */
// Pour permet un acces REST
@RepositoryRestResource
public interface CategoryRepository extends MongoRepository<Category, String> {

}
