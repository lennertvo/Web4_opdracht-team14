package ui.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.db.UserDBInMemory;
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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private UserDBInMemory userDBInMemory;


    public UserServlet() {
        super();
        userDBInMemory = new UserDBInMemory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        System.out.println(command);
        switch (command){
            case ("changeStatus"):
                String status = request.getParameter("status");
                System.out.println(status);
                User u = (User) request.getSession().getAttribute("user");
                System.out.println(u);

                Status s = Status.valueOf(status.toUpperCase().trim());


                u.setStatus(s);
                request.setAttribute("status", u.getStatus());



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
        if (command != null && command.equals("searchUser")) {
            String firstName = request.getParameter("firstName");
            ArrayList<User> users = userDBInMemory.findUsers(firstName);
            response.setContentType("application/json");
            response.getWriter().write(toJSON(users));
        }
        else if (command != null && command.equals("getStatus")){
            User u = (User) request.getSession().getAttribute("user");
            Status status = u.getStatus();
            response.setContentType("application/json");
            response.getWriter().write(toJson(status));
        }
        else {
            ArrayList<User> users = userDBInMemory.getUsers();
            response.setContentType("application/json");
            response.getWriter().write(toJSON(users));
        }
    }

    private String toJSON(ArrayList<User> users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(users);
    }

    private String toJson(Status status) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(status);
    }
}
