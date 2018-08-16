package org.wlgzs.website.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zjg
 * @date 2018/8/15 9:21
 * @Description jwt工具类
 */
@Slf4j
public class JwtUtil {

    /**
     * 传输信息，必须是json格式
     */
    private String msg;
    /**
     * 密钥
     */
    private String secret;
    /**
     * 所验证的jwt
     */
    @Setter private String token;

    public JwtUtil(String msg,  String token) {
        this.msg = msg;
        this.token = token;
        secret = readSecret();
    }

    /**
     * 生成jwt
     */
    public String creatJwt () {
        //先对信息进行aes加密
        msg = new AesUtil(msg).encrypt();
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("wlgzs")
                    .withClaim("user", msg)
                    .sign(Algorithm.HMAC256(secret));
        } catch (Exception e) {
            log.info("jwt 生成问题");
        }
        log.info("加密后：" + token);
        return token;
    }

    /**
     * 解密jwt并验证是否正确
     */
    public boolean freeJwt () {
        DecodedJWT decodedJWT = null;
        try {
            //使用hmac256加密算法
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("wlgzs")
                    .build();
            decodedJWT = verifier.verify(token);
            log.info("签名人：" + decodedJWT.getIssuer() + " 加密方式：" + decodedJWT.getAlgorithm() + " 携带信息：" + decodedJWT.getClaim("user").asString());
        } catch (Exception e) {
            log.info("jwt解密出现错误，jwt或私钥或签证人不正确");
            return false;
        }
        //获得token的头部，载荷和签名，只对比头部和载荷
        String [] headPayload = token.split("\\.");
        //获得jwt解密后头部
        String header = decodedJWT.getHeader();
        //获得jwt解密后载荷
        String payload = decodedJWT.getPayload();

        return (header.equals(headPayload[0]) && payload.equals(headPayload[1]));
    }

    /**
     * 读取密钥
     * @return 密钥信息
     */
    private String readSecret() {
        Properties properties = new Properties();
        //加载resource目录下的配置文件
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("jwtsecret.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.info("读取密钥文件错误");
        }
        return properties.getProperty("jwtsecret");
    }
}
