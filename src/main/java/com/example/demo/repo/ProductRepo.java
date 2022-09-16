package com.example.demo.repo;

import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductDetails;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ProductRepo extends JpaRepository<ProductDetails, Integer>{

}
