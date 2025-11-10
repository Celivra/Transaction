package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password,
                          RedirectAttributes fModel, HttpSession httpSession, HttpSession session){
        User InputUser = userService.getUserByUsername(username);
        if(InputUser == null){
            fModel.addFlashAttribute("loginError", "Username Is Not Exist");
            return "Login";
        }
        if(InputUser.getPassword().equals(password)){
            session.setAttribute("user", InputUser);
            return  "/";
        }
        fModel.addFlashAttribute("loginError", "Username Is Not Exist");
        return "Login";
    }

}
