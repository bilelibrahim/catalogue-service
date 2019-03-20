package org.sid;

import org.sid.dao.CategoryRepository;
import org.sid.dao.ProductRepository;
import org.sid.entities.Category;
import org.sid.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository){
		return args ->{
			categoryRepository.deleteAll();
			Stream.of("C1 Ordinateurs", "C2 Imprimantes", "C3 Ecrans").forEach(c -> {
				Category category = new Category(c.split(" ")[0], c.split(" ")[1], new ArrayList<Product>());
					categoryRepository.save(category);
				});
			categoryRepository.findAll().forEach(System.out::println);
		};
	}


}
