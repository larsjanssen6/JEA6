package controller.Authentication.Facebook;

import Authentication.UserDTO;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.restfb.types.User;

@WebServlet(name = "CallBackServlet", urlPatterns = {"/callback"})
public class CallBackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("access_token"), Version.VERSION_2_6);
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "name, email"));

        request.setAttribute("first_name", user.getName());
        request.setAttribute("email", user.getEmail());

        RequestDispatcher view = request.getRequestDispatcher("/facebook/secured.jsp");
        view.forward(request, response);
    }
}