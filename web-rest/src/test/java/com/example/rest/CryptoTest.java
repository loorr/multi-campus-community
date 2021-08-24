package com.example.rest;

import com.example.common.CryptoTool;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CryptoTest {


    @Test
    void test(){
        String a = CryptoTool.encrypt("12121212");
        System.out.println(a);
        System.out.println(CryptoTool.decrypt(a));
    }
}
