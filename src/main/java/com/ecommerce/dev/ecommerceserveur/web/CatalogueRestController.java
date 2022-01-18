package com.ecommerce.dev.ecommerceserveur.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dev.ecommerceserveur.dao.ProductRepository;
import com.ecommerce.dev.ecommerceserveur.entity.Product;

@CrossOrigin("*")
@RestController
public class CatalogueRestController {
	private ProductRepository productRepository;

	public CatalogueRestController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		Product p = productRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ecom/products/" + p.getPhotoName()));

	}

	// Return the image from the classpath location using HttpServletResponse
	@GetMapping(value = "classpath1", produces = MediaType.IMAGE_PNG_VALUE)
	public void fromClasspathAsHttpServResp(HttpServletResponse response) throws IOException {

		ClassPathResource imageFile = new ClassPathResource("C:\\Users\\fujitsu\\Downloads\\ph3.png");

		StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
	}

}
