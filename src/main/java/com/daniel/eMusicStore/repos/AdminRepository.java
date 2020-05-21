package com.daniel.eMusicStore.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.eMusicStore.entities.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, Integer> {

	AdminModel findByEmail(String email);

	AdminModel findByEmailAndPassword(String email, String password);
}
