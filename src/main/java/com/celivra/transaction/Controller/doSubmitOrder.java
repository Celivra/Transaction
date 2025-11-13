package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.TransRecord;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.TranRecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class doSubmitOrder {
    @Autowired
    TranRecordService tranRecordService;

    @PostMapping("/doSubmitOrder")
    public String SubmitOrder(@RequestParam Integer productId,
                              @RequestParam String address,
                              @RequestParam String description,
                              HttpSession session,
                              RedirectAttributes fModel) {
        User user = (User) session.getAttribute("user");

        // 创建交易记录对象
        TransRecord record = new TransRecord();
        record.setUserId(user.getId());
        record.setProductId(productId);
        record.setPurchaseTime(LocalDateTime.now());
        record.setStatus("待发货");
        record.setReceiveAddress(address);
        record.setDescription(description);

        tranRecordService.addTransRecord(record);

        fModel.addFlashAttribute("status", "订单提交成功！");
        return "redirect:/TransRecord"; // 提交后跳转到交易记录页面
    }

}
