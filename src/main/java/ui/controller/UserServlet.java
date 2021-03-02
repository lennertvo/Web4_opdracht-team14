package ui.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.db.UserDBInMemory;
import domain.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String userid = request.getParameter("userid");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber");
        String dateofbirth = request.getParameter("dateofbirth");
        LocalDate date = LocalDate.parse(dateofbirth);
        User user = new User(userid, firstname, lastname, password, email, phonenumber, date);
        userDBInMemory.addUser(user);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = userDBInMemory.getUsers();

        response.setContentType("application/json");
        response.getWriter().write(toJSON(users));


    }


    private String toJSON(ArrayList<User> users) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(users);

    }
}
