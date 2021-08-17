package com.example.core.common;

public class PreStartConfig {
    public static void preWork(){
        // 避免邮件附近过长截断
        System.setProperty("mail.mime.splitlongparameters", "false");
    }
}
