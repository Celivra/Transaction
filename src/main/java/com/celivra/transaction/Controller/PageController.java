package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    @GetMapping("/")
    public String IndexPage(){
        return "index";
    }
    @GetMapping("/Login")
    public String LoginPage(){
        return "login";
    }
    @GetMapping("/Register")
    public String RegisterPage(){
        return "register";
    }
    @GetMapping("/Account")
    public String AccountPage(){
        return "account";
    }

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public String ProductPage(@PathVariable Integer id, Model model){
        Product currentProduct =  productService.getProductById(id);

        model.addAttribute("product", currentProduct);

        return "product";
    }
}
