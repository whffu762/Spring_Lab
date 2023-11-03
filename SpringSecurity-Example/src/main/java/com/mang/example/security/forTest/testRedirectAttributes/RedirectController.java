package com.mang.example.security.forTest.testRedirectAttributes;

import com.mang.example.security.app.user.model.UserVO;
import com.mang.example.security.app.user.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController {


/*
 URL 의 변화를 확인하면 됨
 WhiteLabel 페이지 나오는게 맞음
 */

    @GetMapping("/testRedirect")
    public String test(RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("page", 10);
        redirectAttributes.addAttribute("name", "kim");
        redirectAttributes.addAttribute("pw", "1234");
        return "redirect:/test/{page}";
    }

    @GetMapping("/testRedirect2")
    public String test2(RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("page", 10);
        return "redirect:/test";
    }

}
