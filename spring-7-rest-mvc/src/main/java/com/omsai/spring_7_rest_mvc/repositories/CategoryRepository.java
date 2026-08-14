package com.omsai.spring_7_rest_mvc.repositories;

import com.omsai.spring_7_rest_mvc.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}