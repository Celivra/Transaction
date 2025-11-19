package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.TransRecord;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.ProductService;
import com.celivra.transaction.Service.TranRecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    TranRecordService tranRecordService;

    @GetMapping("/")
    public String IndexPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("productList", allProducts);
        if(user != null){
            model.addAttribute("user", user);
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

        model.addAttribute("product_list",productList);
        model.addAttribute("user", user);

        return "account";
    }

    @GetMapping("/PostProduct")
    public String PostProductPage(HttpSession session, Model model){
        return "post-product";
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
    @GetMapping("/TransRecord")
    public String TransRecordPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<TransRecord> buyList = tranRecordService.getTransRecordsByBuyer(user.getId());
        List<TransRecord> sellList = tranRecordService.getTransRecordsBySeller(user.getId());

        // 在TransRecordPage方法里
        List<Map<String,Object>> buyRecords= new ArrayList<>();
        List<Map<String,Object>> sellRecords= new ArrayList<>();

        for(TransRecord tr : buyList){
            Product product = productService.getProductById(tr.getProductId());
            Map<String,Object> map = new HashMap<>();
            map.put("record", tr);
            map.put("productName", product.getName());
            buyRecords.add(map);
        }
        for(TransRecord tr : sellList){
            Product product = productService.getProductById(tr.getProductId());
            Map<String,Object> map = new HashMap<>();
            map.put("record", tr);
            map.put("productName", product.getName());
            sellRecords.add(map);
        }

        model.addAttribute("buy_records", buyRecords);
        model.addAttribute("sell_records", sellRecords);
        model.addAttribute("user", user);
        return "t-record";
    }

}
