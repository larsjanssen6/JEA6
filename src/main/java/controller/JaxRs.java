package controller;

import Authentication.Group;
import Authentication.UserBean;
import Authentication.UserDTO;
import Response.JsonResponse;
import domain.User;
import domain.UserLogin;

import javax.ws.rs.core.Response;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/jxrs")
public class JaxRs {

    @EJB
    private UserBean userBean;

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Response register(UserDTO newUser, @Context HttpServletRequest req) {

        JsonResponse json = new JsonResponse();
        json.setData(newUser); //just return the date we received

        //do some validation (in reality you would do some more validation...)
        //by the way: i did not choose to use bean validation (JSR 303)
        if (newUser.getPassword1().length() == 0 || !newUser.getPassword1().equals(newUser.getPassword2())) {
            json.setErrorMsg("Both passwords have to be the same - typo?");
            json.setStatus("FAILED");
            return Response.ok().entity(json).build();
        }

        User user = new User(newUser);

        List<Group> groups = new ArrayList<Group>();
        groups.add(Group.ADMINISTRATOR);
        groups.add(Group.USER);
        groups.add(Group.DEFAULT);
        user.setGroups(groups);

        //this could cause a runtime exception, i.e. in case the user already exists
        //such exceptions will be caught by our ExceptionMapper, i.e. javax.transaction.RollbackException
        userBean.save(user); // this would use the clients transaction which is committed after save() has finished
        req.getServletContext().log("successfully registered new user: '" + newUser.getEmail() + "':'" + newUser.getPassword1() + "'");

        req.getServletContext().log("execute login now: '" + newUser.getEmail() + "':'" + newUser.getPassword1() + "'");
        try {
            req.login(newUser.getEmail(), newUser.getPassword1());
            json.setStatus("SUCCESS");
        } catch (ServletException e) {
            e.printStackTrace();
            json.setErrorMsg("User Account created, but login failed. Please try again later.");
            json.setStatus("FAILED"); //maybe some other status? you can choose...
        }

        return Response.ok().entity(json).build();
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Response login(UserLogin userLogin,
                          @Context HttpServletRequest req) {

        JsonResponse json = new JsonResponse();

        //only login if not already logged in...
        if(req.getUserPrincipal() == null){
            try {
                req.login(userLogin.getEmail(), userLogin.getPassword());
                req.getServletContext().log("Authentication Demo: successfully logged in " + userLogin.getEmail());
            } catch (ServletException e) {
                e.printStackTrace();
                json.setStatus("FAILED");
                json.setErrorMsg("Authentication failed");
                return Response.ok().entity(json).build();
            }
        }else{
            req.getServletContext().log("Skip logged because already logged in: "+userLogin.getEmail());
        }

        //read the user data from db and return to caller
        json.setStatus("SUCCESS");

        User user = userBean.find(userLogin.getEmail());
        req.getServletContext().log("Authentication Demo: successfully retrieved User Profile from DB for " + userLogin.getEmail());
        json.setData(user);

        //we don't want to send the hashed password out in the json response
        userBean.detach(user);
        user.setPassword(null);
        user.setGroups(null);
        return Response.ok().entity(json).build();
    }
}
