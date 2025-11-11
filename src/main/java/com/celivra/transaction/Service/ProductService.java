package com.celivra.transaction.Service;

import com.celivra.transaction.Mapper.ProductMapper;
import com.celivra.transaction.Pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;
    public Boolean  addProduct(Product product, Integer userId) {
        product.setUserId(userId);
        product.setStatus("已发布");
        System.out.println(product.toString());
        return productMapper.addProduct(product);
    }
    public Boolean updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }
    public Product getProductById(Integer id) {
        return productMapper.getProductById(id);
    }
    public List<Product> getProductsByUserId(Integer id) {
        return productMapper.getProductsByUserId(id);
    }
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }
}
