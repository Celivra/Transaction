package com.celivra.transaction.Controller;

import com.celivra.transaction.DTO.ChatSummary;
import com.celivra.transaction.Pojo.Message;
import com.celivra.transaction.Pojo.Product;
import com.celivra.transaction.Pojo.User;
import com.celivra.transaction.Service.MessageService;
import com.celivra.transaction.Service.ProductService;
import com.celivra.transaction.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @PostMapping("/chat")
    public String chatPage(
            @RequestParam Integer otherUserId,
            @RequestParam Integer productId,
            HttpSession session,
            Model model
    ){
        Product p = productService.getProductById(productId);
        User me = (User) session.getAttribute("user");
        User other = userService.getUserById(otherUserId);

        model.addAttribute("me", me);
        model.addAttribute("other", other);
        model.addAttribute("product", p);

        User user = (User) session.getAttribute("user");
        if(user != null){
            model.addAttribute("user", user);
        }

        return "chat";
    }
    @GetMapping("/chat/list")
    public String chatListPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user == null) return "redirect:/Login";

        List<ChatSummary> list = messageService.getChatSummaryList(user.getId());
        model.addAttribute("chatList", list);

        return "chat_list"; // 对应 chat_list.html
    }

    @ResponseBody
    @GetMapping("/chat/history")
    public List<Message> getHistory(Integer me, Integer other, Integer productId){
        return messageService.getChatHistory(me, other, productId);
    }

    @ResponseBody
    @PostMapping("/chat/send")
    public boolean sendMessage(@RequestBody Message msg){
        msg.setSendTime(LocalDateTime.now());
        return messageService.sendMessage(msg);
    }
}
