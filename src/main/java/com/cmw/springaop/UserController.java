package com.cmw.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @autor:
 * @create:
 * @description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService=null;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(int id,String username,String note){
        User user =new User();
        user.setId(id);
        user.setUsername(username);
        user.setNote(note);
        userService.printUser(user);

        return user;
    }
}
