package com.daniel.eMusicStore.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.eMusicStore.entities.Product;
import com.daniel.eMusicStore.repos.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping
	public List<Product> getProduct(){
		return productRepository.findAll();
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product, MultipartFile file) {
		Product img = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		try {
//			img.setProductImage(file.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.path(fileName).path("/db")
				.toUriString();
		String string = (fileDownloadUri);
		return productRepository.save(product);
	}

	@PutMapping
	public Product updateProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable int id) {
		return productRepository.findById(id).get();
	}
}
