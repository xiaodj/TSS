package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Controller
public class UserController {

    /**
     * 登录接口
     */
    @RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
    public @ResponseBody
    BaseDto login(@RequestBody Map<String, String> param){
        return iUser.login(param);
    }
}
