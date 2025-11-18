package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Evaluate;
import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.EvaluateService;
import com.celivra.transaction.Service.ProductService;
import com.celivra.transaction.Service.UserService;
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
    @Autowired
    EvaluateService evaluateService;
    @Autowired
    UserService userService;

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
            model.addAttribute("logged", true);
        }

        return "index"; // 返回你要显示结果的页面
    }
    @GetMapping("/TakeOffProduct")
    public String takeOffProduct(@RequestParam Integer productId) {
        Product product = productService.getProductById(productId);
        System.out.println(product);
        product.setStatus("已下架");
        System.out.println(product);
        productService.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/seller-product/{id}")
    public String SellerProductPage(@PathVariable Integer id, Model model,  HttpSession session){
        Product currentProduct =  productService.getProductById(id);
        List<Evaluate> evaluateList = evaluateService.getEvaluatesByProductId(id);

        model.addAttribute("product", currentProduct);
        model.addAttribute("evaluates", evaluateList);

        User user = (User) session.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }
        return "seller-product";
    }
    @GetMapping("/product/{id}")
    public String ProductPage(@PathVariable Integer id, Model model, HttpSession session){
        Product currentProduct =  productService.getProductById(id);
        User seller = userService.getUserById(currentProduct.getUserId());
        List<Evaluate> evaluateList = evaluateService.getEvaluatesByProductId(id);

        model.addAttribute("product", currentProduct);
        model.addAttribute("seller", seller);
        model.addAttribute("evaluates", evaluateList);

        User user = (User) session.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }
        return "product";
    }
}
