package ui.controller;

import domain.db.UserDBInMemory;
import domain.model.Status;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
    private UserDBInMemory userDBInMemory;


    public LogInServlet() {
        super();
        userDBInMemory = new UserDBInMemory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String command = "index";

        if(request.getParameter("command") != null){
            command = request.getParameter("command");
        }
        switch(command) {
            case "logIn":
                destination = logIn(request);
                break;
            case "logOut":
                destination = logOut(request);
                break;
            default:
                destination = index(request);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String logOut(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        user.setStatus(Status.OFFLINE);
        request.getSession().invalidate();
        return "logIn.jsp";
    }

    private String logIn(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User found = userDBInMemory.findUser(userId);
        if(found.isCorrectPassword(password)){
            createSession(found, request);
            request.setAttribute("status", found.getStatus());

        }
        found.setStatus(Status.ONLINE);

        return "logIn.jsp";
    }

    private void createSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userid", user.getUserid());
    }

    private String index(HttpServletRequest request) {
        return "logIn.jsp";
    }


}
