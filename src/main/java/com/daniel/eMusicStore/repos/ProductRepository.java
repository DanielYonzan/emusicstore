package com.daniel.eMusicStore.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.eMusicStore.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
