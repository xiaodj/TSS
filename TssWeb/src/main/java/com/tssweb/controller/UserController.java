package com.tssweb.controller;

import com.tssweb.dto.BaseDto;
import com.tssweb.dto.LoginDto;
import com.tssweb.dto.UserSetDto;
import com.tssweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xiaodj on 2018/11/28.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 注册接口
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/register", method = RequestMethod.POST)
    public @ResponseBody BaseDto register(@RequestBody Map<String, String> param){
        return iUserService.register(param);
    }

    /**
     * 登录接口
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/login", method = RequestMethod.POST)
    public @ResponseBody LoginDto login(@RequestBody Map<String, String> param){
        return iUserService.login(param);
    }

    /**
     * 获取用户设置信息
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/set", method = RequestMethod.GET)
    public @ResponseBody
    UserSetDto GetSetInfo(@PathVariable("uid") Integer uid){
        return iUserService.getSetInfo(uid);
    }

    /**
     * 修改用户设置信息
     * @author 邓江
     */
    @RequestMapping(value = "/v1/user/{uid}/set", method = RequestMethod.PUT)
    public @ResponseBody BaseDto PutSetInfo(@PathVariable("uid") Integer uid, @RequestBody Map<String, String> param){
        return iUserService.putSetInfo(uid, param);
    }
}
