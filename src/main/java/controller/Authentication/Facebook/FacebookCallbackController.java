package controller.Authentication.Facebook;

import Jwt.Util.KeyGenerator;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.restfb.types.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import repos.User.IUserRepo;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Path("/callback")
public class FacebookCallbackController {
    @EJB
    private IUserRepo userRepo;

    @Inject
    private KeyGenerator keyGenerator;

    @Context
    private UriInfo uriInfo;

    public FacebookCallbackController() { }

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Response login(@Context HttpServletRequest request) {

        FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("access_token"), Version.VERSION_2_6);
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "name, email"));

        String[] splited = user.getName().split("\\s+");

        if(userRepo.find(user.getEmail()) == null) {
            domain.User usr = new domain.User();
            usr.setFirstName(splited[0]);
            usr.setLastName(splited[1]);
            usr.setEmail(user.getEmail());
            usr.setRegisteredOn(new Date());
            usr.setPassword(DigestUtils.sha512Hex("abcdefghijklmnop"));
            userRepo.save(usr);
        }

        return Response
                .status(Response.Status.OK)
                .entity(issueToken(user.getEmail()))
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