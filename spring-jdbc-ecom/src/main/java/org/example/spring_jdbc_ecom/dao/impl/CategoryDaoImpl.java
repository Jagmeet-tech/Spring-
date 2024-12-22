package org.example.spring_jdbc_ecom.dao.impl;

import org.example.spring_jdbc_ecom.dao.CategoryDao;
import org.example.spring_jdbc_ecom.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createTableQuery = "Create Table If Not Exists Category(id int primary key,title varchar(200) not null, description varchar(600) not null)";
        this.jdbcTemplate.update(createTableQuery);
        System.out.println("Category table is created or already exists.");
    }

    @Override
    public Category create(Category category) {
        String query = "Insert into Category(id,title,description) values (?,?,?)";

        int affectedRows = jdbcTemplate.update(query,
                category.getId(),
                category.getTitle(),
                category.getDescription());

        System.out.print(affectedRows + " rows affected");
        return category;
    }
}
