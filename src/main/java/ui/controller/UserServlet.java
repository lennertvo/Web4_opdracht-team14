package ui.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.db.UserDBInMemory;
import domain.model.Group;
import domain.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = userDBInMemory.getUsers();
        response.setContentType("application/json");
        response.getWriter().write(toJSON(users));
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command != null && command.equals("searchUser")) {
            String firstName = request.getParameter("firstName");
            ArrayList<User> users = userDBInMemory.findUser(firstName);
            response.setContentType("application/json");
            response.getWriter().write(toJSON(users));
        }
        else {
            ArrayList<User> users = userDBInMemory.getUsers();
            response.setContentType("application/json");
            response.getWriter().write(toJSON(users));
        }
    }

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            ArrayList<User> users = userDBInMemory.getUsers();
            response.setContentType("application/json");
            response.getWriter().write(toJSON(users));
        }
        switch (command) {
            case "searchUser":
                String firstName = request.getParameter("firstName");
                ArrayList<User> users = userDBInMemory.findUser(firstName);
                response.setContentType("application/json");
                response.getWriter().write(toJSON(users));
                break;
        }
    }*/

    /*String command = request.getParameter("command");
        switch (command){
            case("filterByMaxNumberOfPlayers"):
                String m = request.getParameter("max");
                int max = Integer.parseInt(m);
                ArrayList<Group> filteredGroups = groupDBInMemory.getGroupsWithMaxUsers(max);
                response.setContentType("application/json");
                response.getWriter().write(toJson(filteredGroups));
                break;
            case("searchGroup"):
                String searchedGroup = request.getParameter("searchedGroup");
                ArrayList<Group> group = groupDBInMemory.searchGroup(searchedGroup);
                response.setContentType("application/json");
                response.getWriter().write(toJson(group));
                break;
            case ("all"):
                ArrayList<Group> groups = groupDBInMemory.getGroups();
                response.setContentType("application/json");
                response.getWriter().write(toJson(groups));
                break;
        }*/

    /*private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "";
        String action = "home";

        if (request.getParameter("command") != null) action = request.getParameter("command");

        switch (action) {
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "formulier":
                destination = formulier(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }*/


    private String toJSON(ArrayList<User> users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(users);
    }
}
