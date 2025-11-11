package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/doPostProduct")
    public String addProduct(Product product, HttpSession session, RedirectAttributes fModel) {
        User user = (User) session.getAttribute("user");

        if(productService.addProduct(product,user.getId())){
            fModel.addFlashAttribute("status", "success");
        }else{
            fModel.addFlashAttribute("status", "fatal");
        }
        return "redirect:/PostProduct";
    }
}
