package com.daniel.eMusicStore.services;

import java.util.List;

import com.daniel.eMusicStore.entities.Product;

public interface ProductService {
	
	Product saveProduct(Product product);

	List<Product> getProductList();

    Product getProductById(int id);

   Product updateProduct(Product product);

    void deleteProduct(Product product);
}
