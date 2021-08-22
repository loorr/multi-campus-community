package com.example.rest;

import com.example.model.proto.UserProto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProtoTest {
    @Test
    void send() {
        UserProto.User.Builder userBuilder = UserProto.User.newBuilder();
        userBuilder.setName("zjianfa");
        userBuilder.setMobile("121");
        userBuilder.setSex("1");
        userBuilder.build();
        System.out.println(userBuilder.toString());
    }
}
