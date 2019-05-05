package controller.Question;

import Jwt.Filter.JWTTokenNeeded;
import domain.Question.Question;
import domain.Question.QuestionDTO;
import repos.Question.IQuestionRepo;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/question")
public class QuestionController {
    @EJB
    IQuestionRepo questionRepo;

    public QuestionController(){ }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenNeeded
    public List<Question> get(@PathParam("id") long id)
    {
        return questionRepo.getAll(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @JWTTokenNeeded
    public Response save(QuestionDTO questionDTO, @Context HttpServletRequest req) {
        Question question = new Question(questionDTO);
        questionRepo.save(question);
        return Response.ok().entity(question).build();
    }
}
