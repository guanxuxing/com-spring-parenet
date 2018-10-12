package com.spring.bot.secret;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Administrator on 2018-09-25.
 */
public class DesSecret {

    /***
     * 非对称加解密（RSA）
     * 生成秘钥对
     * @throws Exception
     */
    public void enstr() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String  publinStr = Base64.encodeBase64String(publicKey.getEncoded());
        String privateStr = Base64.encodeBase64String(privateKey.getEncoded());
        System.out.println("-------publinStr  " + publinStr);
        System.out.println("-------privateStr " + privateStr);
    }

    public static void main(String[] args) throws Exception{
        DesSecret desSecret = new DesSecret();
        desSecret.enstr();
    }
}
