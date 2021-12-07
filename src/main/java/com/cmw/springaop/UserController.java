package com.cmw.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/print",method = RequestMethod.POST)
    @ResponseBody
    public User printUser(@RequestParam(value = "id") int id,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "note") String note){
        User user =new User();
        user.setId(id);
        user.setUsername(username);
        user.setNote(note);
        userService.printUser(user);

        return user;
    }

    @RequestMapping(value = "/print2",method = RequestMethod.POST)
    @ResponseBody
    @CustomerLog
    public User printUser2(@RequestParam(value = "id") int id,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "note") String note){
        User user =new User();
        user.setId(id);
        user.setUsername(username);
        user.setNote(note);
        userService.printUser(user);

        return user;
    }
}
