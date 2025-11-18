package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.TransRecord;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.ProductService;
import com.celivra.transaction.Service.TranRecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransRecordController {
    @Autowired
    TranRecordService tranRecordService;
    @Autowired
    ProductService productService;


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
    @PostMapping("/shipNow")
    @ResponseBody
    public ResponseEntity<String> shipNow(@RequestParam Integer recordId) {
        TransRecord record = tranRecordService.getTransRecordById(recordId);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("记录不存在");
        }

        record.setStatus("已发货");
        tranRecordService.updateTranRecord(record);
        return ResponseEntity.ok("ok");
    }
}
