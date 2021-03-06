package kr.mj.gollaba.security;

import io.jsonwebtoken.*;
import kr.mj.gollaba.exception.GollabaErrorCode;
import kr.mj.gollaba.exception.GollabaException;
import kr.mj.gollaba.security.service.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long accessExpirationTime;
    private final long refreshExpirationTime;
    private final PrincipalDetailsService principalDetailsService;

    public JwtTokenProvider(
            @Value("${security.jwt.secret-key}") String secretKey,
            @Value("${security.jwt.access-expiration-time}") long accessExpirationTime,
            @Value("${security.jwt.refresh-expiration-time}") long refreshExpirationTime,
            PrincipalDetailsService principalDetailsService
    ) {
        this.secretKey = secretKey;
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
        this.principalDetailsService = principalDetailsService;
    }

    public String createToken(String email) {
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + accessExpirationTime);

        return Jwts.builder()
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getPayloadByKey(String token, String key) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .get(key, String.class);
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException();
        }
    }

    /*
        iss : ??? ???????????? ???????????? ????????????.
        iat : ??? ???????????? ????????? ????????? ????????????.
        exp : ??? ???????????? ????????? ????????? ????????????.
        sub : ????????? ???????????????.
        aud : ????????? ???????????????.
        nbf : ????????? ???????????? ????????? ??? ????????? ???????????????.
        ??? ????????? ????????? ?????? ????????? ???????????? ????????????.
        jti : ????????? ?????? ??????????????????.
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsByToken(token);
        return null;
    }

    private Claims getClaimsByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new GollabaException(GollabaErrorCode.INVALID_JWT_TOKEN);
        }
    }
}
