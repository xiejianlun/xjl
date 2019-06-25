package com.xjl.controller.user;

import com.xjl.entity.user.User;
import com.xjl.service.user.UserService;
import com.xjl.vo.common.PageBean;
import com.xjl.vo.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by lenovo on 2018/11/29.
 */
@RequestMapping("/user")
@RestController("LoginController")
@Api(value="LoginController",tags="登录接口")
public class LoginController {

    @Autowired
    private UserService userService;



    @ApiOperation(value = "查询用户", notes = "查询用户")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Result<PageBean<User>> list(){
        PageBean<User> list = userService.selectUserList();
        return Result.success(list);
    }

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Result listActivity(@RequestParam(value = "file") MultipartFile file){
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://temp-file//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-file/" + fileName;

        return Result.success();
    }


}
