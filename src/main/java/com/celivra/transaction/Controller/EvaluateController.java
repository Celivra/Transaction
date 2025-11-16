package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.Evaluate;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.EvaluateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;
    @PostMapping("/submitEvaluate")
    public String submit(@RequestParam Integer productId,
                         @RequestParam String content,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        Evaluate evaluate = new Evaluate(user.getId(), productId,user.getUsername(), content);
        evaluateService.addEvaluate(evaluate);
        return "redirect:/product/"+productId.toString();
    }
}
