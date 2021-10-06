package com.fushaolei.server.controller;

import com.alibaba.fastjson.JSON;
import com.fushaolei.server.bean.BaseResponse;
import com.fushaolei.server.bean.User;
import com.fushaolei.server.constant.HttpConstant;
import com.fushaolei.server.dao.UserDao;
import com.fushaolei.server.helper.BaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDao mUserDao;

    @GetMapping("/{id}")
    public String getUser(@PathVariable(name = "id") int id) {
        BaseResponse<User> baseResponse = new BaseResponse<>();
        User mUser = mUserDao.getUserById(id);
        if (mUser != null) {
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setMsg(HttpConstant.BASE_SUCCESS);
            baseResponse.setData(mUser);
        }
        return JSON.toJSONString(baseResponse);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PutMapping("/")
    public String updateUser(@RequestBody User user) {
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        int id = mUserDao.updateUser(user.getId(), user.getName(), user.getSign());
        if (id != 0) {
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setMsg(HttpConstant.UPDATE_SUCCESS);
            baseResponse.setData(id);
        }
        return JSON.toJSONString(baseResponse);
    }

    /**
     * 登录和注册用这个方法
     * 如果可以从数据库中取的，则是登录业务
     * 如果不可以，则是注册业务
     * 注册业务用户名不能重复
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public String loginUser(@RequestBody User user) {
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        User loginUser = mUserDao.getUser(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            baseResponse.setCode(HttpConstant.SUCCESS_CODE);
            baseResponse.setMsg(HttpConstant.LOGIN_SUCCESS);
            baseResponse.setData(loginUser.getId());
        } else if (loginUser == null) {
            User registerUser = mUserDao.getUserByUsername(user.getUsername());
            if (registerUser == null) {
                mUserDao.inserUser(user);
                baseResponse.setCode(HttpConstant.SUCCESS_CODE);
                baseResponse.setMsg(HttpConstant.REGISTER_SUCCESS);
                baseResponse.setData(user.getId());
            } else {
                baseResponse.setMsg(HttpConstant.LOGIN_FAIL);
            }
        }
        return JSON.toJSONString(baseResponse);
    }

    /**
     * 上传图片
     * @param id
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload/{id}")
    public String uploadImage(@PathVariable(name = "id") int id, @RequestParam("image") MultipartFile multipartFile) {
        BaseResponse<Integer> baseReturn = new BaseResponse<>();
        if (multipartFile == null || multipartFile.isEmpty()) {
            return JSON.toJSONString(baseReturn);
        }
        try {
            String fileName = BaseHelper.getFileName(multipartFile.getOriginalFilename());
            multipartFile.transferTo(new File("F:\\img\\" + fileName));
            mUserDao.updateUserAvator(id, fileName);
            baseReturn.setCode(200);
            baseReturn.setMsg(HttpConstant.BASE_SUCCESS);
            System.out.println("上传成功！图片的地址是：" + HttpConstant.HOST + HttpConstant.FILE + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(baseReturn);
    }
}
