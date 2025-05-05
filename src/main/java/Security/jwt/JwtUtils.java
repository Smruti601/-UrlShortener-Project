package Security.jwt;

import Services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.stream.Collectors;

public class JwtUtils {

   @Value("${jwt.secret}")
    private String jwtSecret;

   @Value("${jwt.expiration}")
   private int jwtExpirationMs;

    //Authorization -> Bearer <TOKEN>
    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
    public String generateToken(UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        String roles = userDetails.getAuthorities().stream().map(
                authority-> authority.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(username)
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime()+ 172800000)))
                .signWith()
                .compact()
    }
    private Key key()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode())
    }
}
