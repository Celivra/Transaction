package com.celivra.transaction.Controller;

import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password,
                          RedirectAttributes fModel, HttpSession session){
        User InputUser = userService.getUserByUsername(username);
        if(InputUser == null){
            fModel.addFlashAttribute("login_error", "Username Is Not Exist");
            return "redirect:/Login";
        }
        if(InputUser.getPassword().equals(password)){
            session.setAttribute("user", InputUser);
            return "redirect:/";
        }
        fModel.addFlashAttribute("login_error", "Password Is Not Equal");
        return "redirect:/Login";
    }

    @PostMapping("/doReg")
    public String doRegister(@RequestBody User user, RedirectAttributes fModel){

        int statusCode = userService.addUser(user);
        if(statusCode == 1){
            fModel.addFlashAttribute("register_success", "Registration Successful");
        }else if(statusCode == 0){
            fModel.addFlashAttribute("register_error", "Service Error");
        }else if(statusCode == -1){
            fModel.addFlashAttribute("register_error", "Username Is Already Exist");
        }
        return "redirect:/Register";

    }

}
