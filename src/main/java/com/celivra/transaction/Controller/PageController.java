package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Evaluate;
import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.TransRecord;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.EvaluateService;
import com.celivra.transaction.Service.ProductService;
import com.celivra.transaction.Service.TranRecordService;
import com.celivra.transaction.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    TranRecordService tranRecordService;
    @Autowired
    EvaluateService evaluateService;

    @GetMapping("/")
    public String IndexPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("all_product", allProducts);
        if(user != null){
            model.addAttribute("user", user);
            model.addAttribute("logged", true);
        }

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
    public String AccountPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<Product> productList = productService.getProductsByUserId(user.getId());
        List<TransRecord> transRecordList = tranRecordService.getTransRecordsByUserId(user.getId());

        model.addAttribute("product_list",productList);
        model.addAttribute("trans_list", transRecordList);
        model.addAttribute("user", user);

        return "account";
    }

    @GetMapping("/PostProduct")
    public String PostProductPage(HttpSession session, Model model){
        return "post-product";
    }

    @GetMapping("/product/{id}")
    public String ProductPage(@PathVariable Integer id, Model model){
        Product currentProduct =  productService.getProductById(id);
        User seller = userService.getUserById(currentProduct.getUserId());
        List<Evaluate> evaluateList = evaluateService.getEvaluatesByProductId(id);

        model.addAttribute("product", currentProduct);
        model.addAttribute("seller", seller);
        model.addAttribute("evaluates", evaluateList);
        return "product";
    }

    @GetMapping("/TransRecord")
    public String TransRecordPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<TransRecord> transRecordList = tranRecordService.getTransRecordsByUserId(user.getId());

        // 在TransRecordPage方法里
        List<Map<String,Object>> records = new ArrayList<>();
        for(TransRecord tr : transRecordList){
            Product product = productService.getProductById(tr.getProductId());
            Map<String,Object> map = new HashMap<>();
            map.put("record", tr);
            map.put("productName", product.getName());
            records.add(map);
        }
        model.addAttribute("records", records);


        model.addAttribute("user", user);
        return "t-record";
    }
    @GetMapping("/Order")
    public String OrderPage(@RequestParam Integer userId, @RequestParam Integer productId, HttpSession session, Model model, RedirectAttributes fModel) {
        User user = (User) session.getAttribute("user");
        if(userId.equals(user.getId())){
            fModel.addFlashAttribute("orderStatus", "不可购买自己的产品");
            String backUrl = "redirect:/product/"+productId.toString();
            return backUrl;
        }
        Product product = productService.getProductById(productId);

        model.addAttribute("user", user);
        model.addAttribute("product", product);
        return "order";
    }

}
