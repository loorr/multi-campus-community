package com.example.core.service.impl;

import com.example.api.req.SignInReq;
import com.example.api.common.ChatErrorCode;
import com.example.common.CryptoTool;
import com.example.common.IdGenerator;
import com.example.api.common.ChatException;
import com.example.core.common.impl.CacheService;
import com.example.core.service.UserService;
import com.example.core.common.MailService;
import com.example.core.common.impl.RedisUserServiceImpl;
import com.example.dao.UserMapper;
import com.example.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjianfa
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUserServiceImpl redisUserService;

    @Resource
    CacheService cacheService;

    @Autowired
    private MailService mailService;

    @Resource
    private UserMapper userMapper;

    @Override
    public void sendCode(SignInReq req) {
        String code = IdGenerator.getCode();
        log.debug("sign info the email {}, the code {}", req.getEmail(), code);
        try {
            redisUserService.setAuthCode(req.getEmail(), code);
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
        String code = redisUserService.getAuthCode(req.getEmail());
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
            String cryptoPassword = CryptoTool.encrypt(req.getPassword());
            user.setPassword(cryptoPassword);
            user.setEmail(req.getEmail());
            user.setNickname(req.getNickname());
            addUser(user);

        }catch (DuplicateKeyException e){
            throw new ChatException(ChatErrorCode.AUTH_ERROR);
        }
        return uid;
    }

    public void addUser(User user){
        try {
            userMapper.insertOne(user);
        }catch (DuplicateKeyException e){
            throw e;
        }
        redisUserService.setUserByUid(user);
    }


    @Override
    public User findUserByUid(Long uid) {
        User user = redisUserService.getUserByUid(uid);
        if (user != null){
            return user;
        }
        return userMapper.getUserByEmailOrUid(null, uid);
    }

    @Override
    public User findUserByEmail(String email) {
        User user =  userMapper.getUserByEmailOrUid(email, null);
        if (user == null || user.getUid() == null){
            throw new ChatException(ChatErrorCode.AUTH_ERROR);
        }
        redisUserService.setUserByUid(user);
        return user;
    }
}
