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
import java.util.List;
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

			productRepository.deleteAll();

			Category category = categoryRepository.findById("C1").get();
			Stream.of("P1", "P2", "P3", "P4").forEach(name->{
				//Product product = new Product(null, name, Math.random() * 1000, category);
				Product product = productRepository.save(new Product(null, name, Math.random() * 1000, category));
				category.getProducts().add(product);
				categoryRepository.save(category);
			});
			Category category1 = categoryRepository.findById("C2").get();
			Stream.of("P5", "P6").forEach(name->{
						//Product product = new Product(null, name, Math.random() * 1000, category1);
				Product product = productRepository.save(new Product(null, name, Math.random() * 1000, category1));
				category1.getProducts().add(product);
				categoryRepository.save(category1);
			});
			productRepository.findAll().forEach(System.out::println);

		};
	}


}
