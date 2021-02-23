package ui.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        Group group = groupDBInMemory.getGroup();
        response.setContentType("application/json");
        response.getWriter().write(toJson(group));
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }

    private String toJson(Group groups) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(groups);
    }
}