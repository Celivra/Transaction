package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/search")
    public String searchProducts(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "min_price", required = false) Double minPrice,
            @RequestParam(value = "max_price", required = false) Double maxPrice,
            @RequestParam(value = "condition", required = false) String condition,
            Model model,
            HttpSession session
    ) {

        // 调用你的 Service 做搜索
        List<Product> results = productService.searchProducts(keyword, minPrice, maxPrice, condition);
        User user = (User) session.getAttribute("user");

        // 回传给前端页面

        model.addAttribute("productList", results);
        if(user != null) {
            model.addAttribute("user", user);
        }

        return "index"; // 返回你要显示结果的页面
    }
}
