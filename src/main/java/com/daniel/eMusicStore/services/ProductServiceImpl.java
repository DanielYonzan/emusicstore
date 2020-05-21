package com.daniel.eMusicStore.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.eMusicStore.entities.Product;
import com.daniel.eMusicStore.repos.ProductRepository;

@Service

public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	@Transactional
	public List<Product> getProductList() {
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public Product getProductById(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
		
	}


	@Override
	@Transactional
	public Product updateProduct(Product product) {
				return updateProduct(product);
	}

}
