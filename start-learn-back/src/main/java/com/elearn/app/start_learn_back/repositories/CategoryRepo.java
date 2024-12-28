package com.elearn.app.start_learn_back.repositories;

import com.elearn.app.start_learn_back.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,String> {
}
