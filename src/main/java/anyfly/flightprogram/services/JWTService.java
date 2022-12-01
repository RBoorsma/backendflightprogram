package anyfly.flightprogram.services;

import anyfly.flightprogram.Exceptions.UnfillableJWSException;
import anyfly.flightprogram.objects.DTO.Login.UserDTO;
import anyfly.flightprogram.objects.JwsData;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

/**
 * This {@code Service Layer} is responsible for the JWS tokens. For more information look in the following documentation.
 *<a href="https://github.com/jwtk/jjwt">JsonWebToken Documentation</a>
 */
@Service
public class JWTService implements IJWTService {

    Calendar calendar;
    /**
     * {@code SecretKey} is responsible for creating & verifying the JSON Web token.
     * @implNote Front end can decrypt the data without any key, however if they change data. This key can verify its intergrity.
     * @see #generateJWS(UserDTO) 
     */
    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final Claims claims = Jwts.claims();
    private static final String ISSUER = "AnyFlight";
    /**
     *
     * @implNote At this moment HS256 will be used. This key is a symmetric key.
     * It is recommended to upgrade to RS256 later, this will result into getting 2 keys for encryption: Private / Public
     * However, for the current state of the project, this is not a requirement.
     * @see #secretKey
     * @see <a href="https://github.com/jwtk/jjwt#jws-key-create-secret">Library Documentation</a>
     * @param user The Entity User.
     * @return An JWS Token in String Format signed with an HS256 Algorithm.
     */

    public UserDTO generateJWS(@NotNull UserDTO user) {
        calendar = Calendar.getInstance();
        claims.setIssuer(ISSUER);
        claims.setSubject(String.valueOf(user.getId()));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH + 7));
        Date date = calendar.getTime();
        claims.setExpiration(date);
        user.setJwsString(Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .signWith(secretKey)
                .compact());
        return user;
    }
    public JwsData verifyJWS(String token)
    {
        io.jsonwebtoken.Jws<Claims> claims;
        claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);


        return fillJWS(claims);
    }

    private JwsData fillJWS(io.jsonwebtoken.Jws<Claims> claims) throws UnfillableJWSException
    {
        JwsData jwsData = new JwsData();
        jwsData.setExp(claims.getBody().getExpiration());
        jwsData.setId(claims.getBody().getId());
        jwsData.setFirstName(claims.getBody().get("firstName", String.class));
        jwsData.setLastName(claims.getBody().get("lastName", String.class));
        jwsData.setIss(claims.getBody().getIssuer());
        jwsData.setEmail(claims.getBody().getSubject());
        return jwsData;
    }
}
