package com.ecommerce.dev.ecommerceserveur;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.ecommerce.dev.ecommerceserveur.dao.CategoryRepository;
import com.ecommerce.dev.ecommerceserveur.dao.ProductRepository;
import com.ecommerce.dev.ecommerceserveur.entity.Category;
import com.ecommerce.dev.ecommerceserveur.entity.Product;

@SpringBootApplication
public class ECommerceServeurApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static String getStringRandom(int length) {

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {

			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		return sb.toString();

	}

	public static void main(String[] args) {
		SpringApplication.run(ECommerceServeurApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);

		categoryRepository.save(new Category(null, "Computers", null, null, null));
		categoryRepository.save(new Category(null, "Printers", null, null, null));
		categoryRepository.save(new Category(null, "Smart phones", null, null, null));
		Random rnd = new Random();
		categoryRepository.findAll().forEach(c -> {
			for (int i = 0; i < 10; i++) {
				Product p = new Product();
				p.setName(getStringRandom(6));
				p.setCurrentPrice(100 + rnd.nextInt(10000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("unknown.png");
				productRepository.save(p);
			}
		});
	}

}
