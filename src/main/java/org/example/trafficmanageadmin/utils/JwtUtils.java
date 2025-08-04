package org.example.trafficmanageadmin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.trafficmanageadmin.entity.User;
import org.springframework.cglib.core.internal.Function;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 定义密钥，用于签名和验证令牌，应妥善保管
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // 定义令牌过期时间，单位为毫秒，这里设置为1小时
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 从令牌中获取用户名
    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // 获取令牌过期时间
    public static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // 通用方法，从令牌中获取指定声明
    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 解析令牌，获取所有声明
    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    // 检查令牌是否过期
    private static boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 生成令牌
    public static String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // 内部方法，实际生成令牌
    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // 验证令牌是否有效
    public static boolean validateToken(String token, User userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) &&!isTokenExpired(token));
    }


}
