package controller.Authentication.JWT;

import java.time.ZoneId;
import Authentication.UserDTO;
import Jwt.Util.KeyGenerator;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import domain.User.User;
import domain.User.UserLogin;
import interceptor.SimpleInterceptor;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import repos.User.IUserRepo;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.*;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@Path("/jwt")
@Interceptors(SimpleInterceptor.class)
public class JwtController {
    @EJB
    private IUserRepo userRepo;

    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Response login(UserLogin userLogin,
                          @Context HttpServletRequest req) {

        if (userRepo.login(userLogin.getEmail(), DigestUtils.sha512Hex(userLogin.getPassword()))) {

            final Long finalUserId = userRepo.find(userLogin.getEmail()).getId();
            final String finalToken = issueToken(userLogin.getEmail());

            Object response = new Object() {
                public final Long userId = finalUserId;
                public final String token = finalToken;
            };

            return Response
                    .status(Response.Status.OK)
                    .entity(response)
                    .build();
        }

        return Response.status(UNAUTHORIZED).build();
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Response register(UserDTO userDTO, @Context HttpServletRequest req) {
        User newUser = new User(userDTO);
        userRepo.save(newUser);

        final Long finalUserId = userRepo.find(newUser.getEmail()).getId();
        final String finalToken = issueToken(newUser.getEmail());

        Object response = new Object() {
            public final Long userId = finalUserId;
            public final String token = finalToken;
        };

        return Response
                .status(Response.Status.OK)
                .entity(response)
                .build();
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(toDate(LocalDateTime.now().minusMinutes(1L)))
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
