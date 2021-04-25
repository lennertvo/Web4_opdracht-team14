package ui.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.db.GroupDBInMemory;
import domain.model.Group;
import domain.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {

    private GroupDBInMemory groupDBInMemory;

    public GroupServlet() {
        super();
        groupDBInMemory = new GroupDBInMemory();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command) {
            case "putGroupMessages":
                String name = request.getParameter("name");
                System.out.println(name);
                Group requestedGroup = groupDBInMemory.getGroupByName(name);
                ArrayList<String> messages = requestedGroup.getMessages();
                request.getSession().setAttribute("group", requestedGroup);
                request.getSession().setAttribute("messages", messages);
                break;
            case "add":
                String groupName = request.getParameter("groupName");
                Group group = new Group(groupName);
                groupDBInMemory.addGroup(group);
                group.addUser((String) request.getSession().getAttribute("userid"));
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command) {
            case ("filterByMaxNumberOfPlayers"):
                String m = request.getParameter("max");
                int max = Integer.parseInt(m);
                ArrayList<Group> filteredGroups = groupDBInMemory.getGroupsWithMaxUsers(max);
                response.setContentType("application/json");
                response.getWriter().write(toJson(filteredGroups));
                break;
            case ("searchGroup"):
                String searchedGroup = request.getParameter("searchedGroup");
                ArrayList<Group> group = groupDBInMemory.searchGroup(searchedGroup);
                response.setContentType("application/json");
                response.getWriter().write(toJson(group));
                break;
            case ("all"):
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                ArrayList<Group> groups = groupDBInMemory.getGroupsOfUser(user);
                response.setContentType("application/json");
                response.getWriter().write(toJson(groups));
                break;
            case ("sortColumn"):
                String columnName = request.getParameter("columnName");
                String sortDirection = request.getParameter("sortDirection");
                ArrayList<Group> sortedGroups = groupDBInMemory.sortedGroups(columnName, sortDirection);
                response.setContentType("application/json");
                response.getWriter().write(toJson(sortedGroups));
                break;
            case "getGroupMessages":
                ArrayList<String> messages = (ArrayList<String>) request.getSession().getAttribute("messages");
                response.setContentType("application/json");
                response.getWriter().write(toJSON(messages));
                break;
        }

    }

    private String toJSON(ArrayList<String> messages) throws JsonProcessingException  {
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(messages);
    }

    private String toJson(ArrayList<Group> groups) throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper();
        return o.writeValueAsString(groups);
    }
}