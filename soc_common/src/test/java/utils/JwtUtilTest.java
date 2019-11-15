package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class JwtUtilTest {

    @Test
    public void build(){
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"1234")
                .claim("role","admin");
        System.out.println(jwtBuilder.compact());

    }

    @Test
    public void parse(){
        Claims body = Jwts.parser().setSigningKey("1234")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiYWRtaW4ifQ.O-yT8bMTPzGTuFHb9R4QnhCe4dOwBh9wO-N6IpmXFpI")
                .getBody();
        System.out.println(body.get("role"));
    }
}