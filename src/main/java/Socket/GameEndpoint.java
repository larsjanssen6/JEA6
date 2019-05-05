package Socket;

import repos.User.IUserRepo;

import javax.ejb.EJB;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(
        value="/game/socket/{userId}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class )
public class GameEndpoint {

    private Session session;

    @EJB
    IUserRepo userRepo;

    private static Set<GameEndpoint> gameEndpoints
            = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("userId") Long userId) throws IOException, EncodeException {
        this.session = session;

        gameEndpoints.add(this);
        users.put(session.getId(), Long.toString(userId));

        Message message = new Message();
        message.setFrom(Long.toString(userId));
        message.setFirstName(userRepo.findById(userId).getFirstName());
        message.setContent("connected");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        message.setFirstName(userRepo.findById(Long.parseLong(users.get(session.getId()))).getFirstName());
        broadcast(message);
    }


    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        gameEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("disconnected");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message)
            throws IOException, EncodeException {

        for(GameEndpoint endpoint: gameEndpoints) {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
