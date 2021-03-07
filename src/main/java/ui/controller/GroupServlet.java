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
        String groupName = request.getParameter("groupName");
        groupDBInMemory.addGroup(new Group(groupName));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
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
        }

    }

    private String toJson(ArrayList<Group> groups) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(groups);
    }
}