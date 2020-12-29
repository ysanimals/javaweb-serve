package com.nit.ssm.utils;


import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.nit.ssm.dto.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil {

    /**
     * 加密秘钥
     */
    private static String secretKey;

    @Value(value = "${application.jwt.secret}")
    public void setSecretKey(String secret) {
        JWTUtil.secretKey = secret;
    }

    private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    /**
     * 生成签名
     *
     * @param seed
     * @return 加密的Token
     */
    public static String createSign(String seed, Integer expire) {
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, expire);
        Date expiresDate = nowTime.getTime();

        return JWT.create()
                .withClaim("seed", seed)
                .withIssuedAt(iatDate)           // sign time
                .withExpiresAt(expiresDate)      // expire time
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * 校验Token是否正确
     *
     * @param token
     * @return 是否正确
     */
    public static TokenDTO verifyToken(String token) {
        //根据传来的Token获取Seed
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        TokenDTO tokenDTO = new TokenDTO();
        try {
            Map<String, Claim> claims = verifier.verify(token).getClaims();
            Claim seed_claim = claims.get("seed");
            String jsonSign = seed_claim.asString();
            JSONObject jsonObject = JSONObject.parseObject(jsonSign);
            tokenDTO.setUserId(jsonObject.getInteger("userId"));
            tokenDTO.setUserName(jsonObject.getString("userName"));
        } catch (Exception e) {
            logger.error("Token验证======>" + tokenDTO.toString());
            logger.error(e.toString());
            tokenDTO = null;
        }
        return tokenDTO;
    }

    /**
     * 无需secret解密，获得Token中的Seed信息
     *
     * @param token
     * @return Token中包含的种子值，通常是用户ID
     */
    public static String getSeed(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        Map<String, Claim> claims = verifier.verify(token).getClaims();
        Claim seed_claim = claims.get("seed");
        return seed_claim.asString();
    }

    public static void main(String[] args) {
        secretKey = "ADB8E3D5838A0AE8E274014928CE2CEE";
        TokenDTO tokenDTO = new TokenDTO(10, "测试账号",1);
        String token = createSign(JSONObject.toJSONString(tokenDTO), 2400000);
        System.out.println(token);
    }
}
