package ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Group;
import domain.model.Message;
import domain.model.User;
import domain.db.GroupDBInMemory;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/ChatServer")
public class ChatServer extends HttpServlet {
    private GroupDBInMemory groupDBInMemory;

    public ChatServer(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "getUser":
                User user = (User) request.getSession().getAttribute("user");
                String username = user.getFirstName()+" "+user.getLastName();
                response.getWriter().write(username);
                break;
            case "getGroupMessages":
                ArrayList<String> messages = (ArrayList<String>) request.getSession().getAttribute("messages");
                response.setContentType("application/json");
                response.getWriter().write(toJSON(messages));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "putGroupMessages":
                String name = request.getParameter("name");
                Group requestedGroup = groupDBInMemory.getGroupByName(name);
                ArrayList<String> messages = requestedGroup.getMessages();
                request.getSession().setAttribute("group",requestedGroup);
                request.getSession().setAttribute("messages",messages);
                break;
        }
    }

    private String toJSON(ArrayList<String> messages) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(messages);
    }

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session){
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        for(Session s: sessions) {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
