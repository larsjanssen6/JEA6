package controller.Authentication.JWT;

import java.time.ZoneId;
import Jwt.Util.KeyGenerator;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import domain.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.ws.rs.core.UriInfo;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("/jwt")
public class JwtController {
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

        //only login if not already logged in...
        if(req.getUserPrincipal() == null){
            try {
                req.login(userLogin.getEmail(), userLogin.getPassword());

                String token = issueToken(userLogin.getEmail());

                return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
            } catch (ServletException e) {
                return Response.status(UNAUTHORIZED).build();
            }
        } else{
            req.getServletContext().log("Skip logged because already logged in: "+userLogin.getEmail());
        }

        return Response.status(UNAUTHORIZED).build();
    }

    private String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwtToken;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
