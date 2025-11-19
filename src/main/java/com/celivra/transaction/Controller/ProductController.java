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

    @GetMapping("/seller-product/{id}")
    public String SellerProductPage(@PathVariable Integer id, Model model,  HttpSession session){
        Product currentProduct =  productService.getProductById(id);
        User checkUser = userService.getUserById(currentProduct.getUserId());
        User user = (User) session.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }

        if(!checkUser.getId().equals(user.getId())){
            return "redirect:/";
        }

        List<Evaluate> evaluateList = evaluateService.getEvaluatesByProductId(id);

        model.addAttribute("product", currentProduct);
        model.addAttribute("evaluates", evaluateList);

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
    @PostMapping("/updateProduct")
    public String updateProduct(Product product, HttpSession session, Model model) {
        System.out.println(product);
        Product currentProduct = productService.getProductById(product.getId());
        currentProduct.setName(product.getName());
        currentProduct.setImage(product.getImage());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setCategory(product.getCategory());
        currentProduct.setCondition(product.getCondition());
        currentProduct.setDescription(product.getDescription());

        User checkUser = userService.getUserById(currentProduct.getUserId());
        User user = (User) session.getAttribute("user");
        if(!checkUser.getId().equals(user.getId())){
            return "redirect:/seller-product/"+product.getId();
        }

        // 调用 Service 执行更新
        boolean ok = productService.updateProduct(currentProduct);

        if (ok) {
            return "redirect:/seller-product/" + product.getId();
        } else {
            model.addAttribute("error", "更新失败，请稍后再试");
            model.addAttribute("product", product);
            return "redirect:/seller-product/" + product.getId();
        }
    }
}
