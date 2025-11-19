package com.celivra.transaction.RestController;

import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.ProductService;
import com.celivra.transaction.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;


    @PostMapping("/TakeOffProduct")
    @ResponseBody
    public ResponseEntity<String> takeOffProduct(@RequestParam Integer productId, HttpSession session) {
        Product product = productService.getProductById(productId);
        User user = (User) session.getAttribute("user");
        User checkUser = userService.getUserById(user.getId());
        if(!checkUser.getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("user_error");
        }

        if(productService.takeOffProduct(product)){
            return  ResponseEntity.status(HttpStatus.OK).body("success");
        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}
