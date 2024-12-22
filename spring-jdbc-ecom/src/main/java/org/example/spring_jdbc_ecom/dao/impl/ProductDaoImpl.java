package org.example.spring_jdbc_ecom.dao.impl;

import org.example.spring_jdbc_ecom.dao.ProductDao;
import org.example.spring_jdbc_ecom.helper.ProductMapper;
import org.example.spring_jdbc_ecom.model.Product;
import org.example.spring_jdbc_ecom.model.ProductWithCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// We can change the implementation of ProductDao.
@Repository
public class ProductDaoImpl implements ProductDao {


    private JdbcTemplate jdbcTemplate;

    //Construtor injection
    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createTableQuery = "Create Table If Not Exists Product(id int primary key,title varchar(200) not null, description varchar(600) not null,price int not null,cat_id int, FOREIGN KEY (cat_id) REFERENCES Category(id))";
        this.jdbcTemplate.update(createTableQuery);
        System.out.println("Product table is created or already exists.");
    }

    @Override
    public Product create(Product product) {
        String query = "Insert into Product(id,title,description,price,cat_id) values (?,?,?,?,?)";

        int affectedRows = jdbcTemplate.update(query,
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getCatId());

        System.out.print(affectedRows + " rows affected");
        return product;
    }

    @Override
    public Product update(Product product, int productId) {
        String query = "update Product set title = ? , description = ?, price = ? where id = ?";
        int affectedRows = jdbcTemplate.update(query,product.getTitle(),product.getDescription(),product.getPrice(),productId);

        System.out.print(affectedRows + " rows updated");
        product.setId(productId);
        return product;
    }

    @Override
    public void delete(int productId) {

    }

    @Override
    public List<Product> getAll() {
        String query = "Select * from Product";
        //1.Automatic maps Rowmapper.
//        List<Product> productList = jdbcTemplate.queryForList(query,Product.class);

        //2. Custom rowmapper thorugh anonymous class.
//        List<Product> productList = jdbcTemplate.query(query, new RowMapper<Product>() {
//            @Override
//            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Product product = new Product();
//                product.setId(rs.getInt("id"));
//                product.setTitle(rs.getString("title"));
//                product.setPrice(rs.getInt("price"));
//                product.setDescription(rs.getString("description"));
//                return product;
//            }
//        });

        //3. Custom rowmapper thorugh lambda expression.
        List<Product> productList = jdbcTemplate.query(query,(rs,rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setTitle(rs.getString("title"));
            product.setPrice(rs.getInt("price"));
            product.setDescription(rs.getString("description"));
            return product;
        });
        return productList;
    }

    @Override
    public Product get(int productId) {
        String query = "Select * from Product where id = ?";
//        Product pdt = jdbcTemplate.queryForObject(query,new Object[]{productId},(rs,rowNum) -> {
//            Product product = new Product();
//            product.setId(rs.getInt("id"));
//            product.setTitle(rs.getString("title"));
//            product.setPrice(rs.getInt("price"));
//            product.setDescription(rs.getString("description"));
//            return product;
//        });

        Product pdt = jdbcTemplate.queryForObject(query, new Object[]{productId}, new ProductMapper());
        return pdt;
    }

    @Override
    public List<Product> search(String keyword) {
        return List.of();
    }

    @Override
    public List<Product> getAllByCategory(int catId) {
        return List.of();
    }

    @Override
    public List<ProductWithCategory> getAllWithCategory() {
        String query = "Select p.id,p.title as pTitle,p.description as pDescription,p.price,c.title as cTitle,c.description as cDescription from Product as p INNER JOIN Category as c ON p.cat_id =c.id";
        List<ProductWithCategory> pwcList = jdbcTemplate.query(query,(rs,rowNum) -> {
            ProductWithCategory pwc = new ProductWithCategory();
            pwc.setId(rs.getInt("id"));
            pwc.setpTitle(rs.getString("pTitle"));
            pwc.setPrice(rs.getInt("price"));
            pwc.setpDescription(rs.getString("pDescription"));
            pwc.setcTitle(rs.getString("cTitle"));
            pwc.setcDescription(rs.getString("cDescription"));
            return pwc;
        });
        return pwcList;
    }
}
