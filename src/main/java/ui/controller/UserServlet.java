package ui.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.db.FriendShipMessagesInMemory;
import domain.db.UserDBInMemory;
import domain.model.FriendShip;
import domain.model.Status;
import domain.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private UserDBInMemory userDBInMemory;

    private FriendShipMessagesInMemory friendShipMessagesInMemory;


    public UserServlet() {
        super();
        userDBInMemory = new UserDBInMemory();
        friendShipMessagesInMemory = new FriendShipMessagesInMemory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        System.out.println(command);
        switch (command) {
            case ("changeStatus"):
                String status = request.getParameter("status");
                System.out.println(status);
                User u = (User) request.getSession().getAttribute("user");
                System.out.println(u);
                if (status.startsWith("T")) {
                    Status s = Status.TAKING_A_CLASS;
                    u.setStatus(s);
                    userDBInMemory.setStatus(u, s);

                } else {
                    Status s = Status.valueOf(status.toUpperCase().trim());
                    u.setStatus(s);
                    userDBInMemory.setStatus(u, s);


                }


                request.setAttribute("status", u.getStatus());


                break;
            case ("addFriend"):
                String id = request.getParameter("userid");
                User friend = userDBInMemory.findUser(id);
                System.out.println("het geraakt hier");
                User a = (User) request.getSession().getAttribute("user");
                if (!userDBInMemory.isAlreadyFriend(a, friend)) {
                    a.addFriend(friend);
                    FriendShip f = new FriendShip(a.getFirstName() + friend.getFirstName(), a, friend);
                    friendShipMessagesInMemory.addFriendShip(f);
                } else {
                    System.out.println("Is already a friend");
                }
                break;
            case ("addMessage"):
                String mes = request.getParameter("message");
                System.out.println(mes);
                String user2id = request.getParameter("user2id");
                System.out.println(user2id);
                User user2 = userDBInMemory.findUser(user2id);
                System.out.println(user2);
                User user1 = (User) request.getSession().getAttribute("user");
                String message = user1.getFirstName() + ": " + mes;
                //System.out.println(message);
                System.out.println("dit dan wel");
                FriendShip friendShip = friendShipMessagesInMemory.getFriendShip(user1.getFirstName()+user2.getFirstName(), user2.getFirstName() + user1.getFirstName());
                System.out.println("jaja dit werkt");
                friendShip.addMessage(message);
                //friendShipMessagesInMemory.addMessage(user1.getFirstName()+user2.getFirstName(), user2.getFirstName() + user1.getFirstName(), message);
                System.out.println(friendShipMessagesInMemory.getMessagesBetween2Users(user1.getFirstName() + user2.getFirstName(), user2.getFirstName() + user1.getFirstName()));
                System.out.println("jajajjajaja");
                break;
            default:
                String userid = request.getParameter("userid");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String phonenumber = request.getParameter("phonenumber");
                String dateofbirth = request.getParameter("dateofbirth");
                System.out.println(dateofbirth);
                System.out.println("blabla");
                System.out.println(phonenumber);
                LocalDate date = LocalDate.parse(dateofbirth);
                User user = new User(userid, firstname, lastname, password, email, phonenumber, date);
                userDBInMemory.addUser(user);

        }

    }

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = userDBInMemory.getUsers();
        response.setContentType("application/json");
        response.getWriter().write(toJSON(users));
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Deelopdracht 1a individueel - Ruben Bottu r0789520 - gebruiker zoeken op voornaam
        String command = request.getParameter("command");


        switch (command) {
            case ("searchUser"):
                String firstName = request.getParameter("firstName");
                ArrayList<User> users = userDBInMemory.findUsers(firstName);
                response.setContentType("application/json");
                response.getWriter().write(toJSON(users));
                break;
            case ("getStatus"):
                User u = (User) request.getSession().getAttribute("user");
                Status status = u.getStatus();
                String c = status.getDescription();
                response.setContentType("application/json");
                response.getWriter().write(toJson(c));
                break;
            case ("getFriends"):
                User a = (User) request.getSession().getAttribute("user");
                ArrayList<User> friends = a.getFriends();

                response.setContentType("application/json");
                response.getWriter().write(toJSON(friends));
                break;


            case ("getPotentialFriends"):
                User b = (User) request.getSession().getAttribute("user");
                ArrayList<User> f = userDBInMemory.getPotentialFriends(b.getUserid());
                response.setContentType("application/json");
                System.out.println(f);
                response.getWriter().write(toJSON(f));
                break;
            case ("getMessagesBetweenFriends"):
                System.out.println("zorooo");
                User user1 = (User) request.getSession().getAttribute("user");
                String id = request.getParameter("user2id");
                System.out.println(id);
                System.out.println("zzzzzzzzzzzzz");
                User user2 = userDBInMemory.findUser(id);
                ArrayList<String> messages = friendShipMessagesInMemory.getMessagesBetween2Users(user1.getFirstName() + user2.getFirstName(), user2.getFirstName()+user1.getFirstName());
                System.out.println(messages);
                System.out.println(toJson(messages));
                response.setContentType("application/json");
                response.getWriter().write(toJson(messages));
                break;
            default:
                ArrayList<User> us = userDBInMemory.getUsers();
                response.setContentType("application/json");
                response.getWriter().write(toJSON(us));


        }
    }

    private String toJSON(ArrayList<User> users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(users);
    }

    private String toJson(Status status) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(status);
    }


    private String toJson(String s) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(s);
    }

    private String toJson(ArrayList<String> strings) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(strings);
    }


}
