package com.example.core.service.impl;

import com.example.api.req.SignInReq;
import com.example.api.common.ChatErrorCode;
import com.example.common.IdGenerator;
import com.example.api.common.ChatException;
import com.example.core.service.UserService;
import com.example.core.common.MailService;
import com.example.core.common.RedisService;
import com.example.dao.UserMapper;
import com.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjianfa
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void sendCode(SignInReq req) {
        String code = IdGenerator.getCode();
        try {
            redisService.setAuthCode(req.getEmail(), code);
            mailService.sendMail(req.getEmail(), "Sign Code", code);
        }catch (Exception e){
            throw new ChatException(ChatErrorCode.CODE_SEND_FILED);
        }
    }

    @Override
    public List<User> getAllUser(int size) {
        return userMapper.getAllUser(size);
    }

    @Override
    public Long signUser(SignInReq req) {
        String code = redisService.getAuthCode(req.getEmail());
        if (code == null){
            throw new ChatException(ChatErrorCode.CODE_EXPRIED);
        }
        if (!code.equals(req.getCode())){
            throw new ChatException(ChatErrorCode.ERROR_CODE);
        }
        Long uid;
        try {
            User user = new User();
            uid = IdGenerator.getUid();
            user.setUid(uid);
            user.setPassword(req.getPassword());
            user.setEmail(req.getEmail());
            user.setNickname(req.getNickname());
            userMapper.insertOne(user);
        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.AUTH_ERROR);
        }
        return uid;
    }

    @Override
    public User findUserByUid(Long uid) {
        return userMapper.getUserByEmailOrUid(null, uid);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.getUserByEmailOrUid(email, null);
    }

    @Override
    public void testAsync() {

    }


}
