package com.dsa.graphs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.dsa.graphs.models.User;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {
    private Set<User> nodes = new HashSet<>();
    private Gson gson;
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyListServiceImpl.class);

    public NodeServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void generateNodes() {
        try {
            File resource = new ClassPathResource("data.json").getFile();
            BufferedReader reader = new BufferedReader(new FileReader(resource));
            User[] users = gson.fromJson(reader, User[].class);

            for (User user : users) {
                // Friends in json are combined with a comma
                String[] friends = user.getFriends().split(", ");

                nodes.add(user); // add() is O(1) time complexity

                // Add dummy nodes for user's friends
                for (String friendID : friends) {
                    nodes.add(new User(friendID));
                }
            }
        } catch (IOException e) {
            LOGGER.warn("------ File not found");
        } catch (Exception e) {
            LOGGER.warn("------ Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public Set<User> getNodes() {
        return nodes;
    }
}