package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;
import com.tssweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@Autowired
    private IUserService iUserService;

    /**
     * 登录接口
     * @author 邓江
     */
    @RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
    public @ResponseBody LoginDto login(@RequestBody Map<String, String> param){
        return iUserService.login(param);
    }

    /**
     * 用户设置
     * @author 邓江
     */
    @RequestMapping(value = "/api/user/set", method = RequestMethod.POST)
    public @ResponseBody BaseDto set(@RequestBody Map<String, String> param){
        return iUserService.set(param);
    }
}
