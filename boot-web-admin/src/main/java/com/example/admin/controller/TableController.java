package com.example.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.admin.bean.User;
import com.example.admin.bean.UserPlus;
import com.example.admin.exception.UserTooManyException;
import com.example.admin.service.UserPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TableController {

    @Autowired
    public UserPlusService userPlusService;
    /**
     *
     * @param a  不带请求参数或者参数类型不对返回400； bad request  一般都是浏览器参数传递错误
     * @return
     */
    @GetMapping("/basic_table.html")
    public String basicTable(@RequestParam("a") int a){
        int b = a/0;//springboot默认会将静态资源和模板引擎路径下的error目录里面的4xx.html和5xx.html页面作为错误页面
        return "table/basic_table";
    }

    @GetMapping("/editable_table.html")
    public String editableTable(){
        return "table/editable_table";
    }

    @GetMapping("/dynamic_table.html")
    public String pricingTable(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,Model model){
        /*List<User> list = new ArrayList<User>();
        list.add(new User("zhangsan","123455"));
        list.add(new User("lisi","32423"));
        list.add(new User("wanger","23142"));
        list.add(new User("jiege","42342"));
        model.addAttribute("users",list);

        if(list.size()>3){
            throw new UserTooManyException();
        }*/
//        List<UserPlus> list = userPlusService.list();
//        model.addAttribute("users",list);
        Page<UserPlus> page = new Page<>(pageNum,2);
        Page<UserPlus> page1 = userPlusService.page(page, null);
        page1.getPages();
        model.addAttribute("page",page1);
        return "table/dynamic_table";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id,
                             @RequestParam("pageNum") Integer pageNum,
                             RedirectAttributes ra){
        userPlusService.removeById(id);
        ra.addAttribute("pageNum",pageNum);
        return "redirect:/dynamic_table.html";
    }

    @GetMapping("/responsive_table.html")
    public String responsiveTable(){
        return "table/responsive_table";
    }


}
