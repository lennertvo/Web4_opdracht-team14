package ui.controller;



import domain.db.GroupDBInMemory;
import domain.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {

    private GroupDBInMemory groupDBInMemory;

    public GroupServlet(){
        super();
        groupDBInMemory = new GroupDBInMemory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Group> groups = groupDBInMemory.getGroups();
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
}