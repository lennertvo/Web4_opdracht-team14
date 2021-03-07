package ui.controller;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ServerEndpoint("/comment")
public class BlogServer {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
}
