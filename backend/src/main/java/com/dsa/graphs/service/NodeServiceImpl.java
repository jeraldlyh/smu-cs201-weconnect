package com.dsa.graphs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyListServiceImpl.class);

    private Set<User> nodes = new HashSet<>();
    private Gson gson;

    public NodeServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void generateNodes() {
        try {
            LOGGER.info("------ STARTING TO GENERATE NODES");
            File resource = new ClassPathResource("data.json").getFile();
            BufferedReader reader = new BufferedReader(new FileReader(resource));
            User[] users = gson.fromJson(reader, User[].class);

            for (User user : users) {
                // Friends in json are combined with a comma
                // String[] friends = user.getFriends().split(", ");

                nodes.add(user); // add() is O(1) time complexity

                // Add dummy nodes for user's friends
                // for (String friendId : friends) {
                //     System.out.println("Contains " + friendId + " | " + nodes.contains(new User(friendId.strip())));
                //     if (!friendId.isEmpty() && !nodes.contains(new User(friendId.strip()))) { // Prevent adding empty nodes
                //         nodes.add(new User(friendId.strip()));
                //     }
                // }
            }
            // System.out.println(nodes);
            LOGGER.info("------ SUCCESSFULLY GENERATED NODES");
        } catch (IOException e) {
            LOGGER.warn("------ FILE NOT FOUND");
        } catch (Exception e) {
            LOGGER.warn("------ UNEXPECTED ERROR: " + e.getMessage());
        }
    }

    @Override
    public Set<User> getNodes() {
        return nodes;
    }

    /**
     * This method costs O(n) time complexity since the worst case is where the user
     * exist at the last element of the set
     * 
     * @param userId String that represents user ID
     * @return User object that is being represented as a node
     */
    @Override
    public User getNode(String userId) {
        Iterator<User> users = nodes.iterator();

        while (users.hasNext()) {
            User currentUser = users.next();
            if (currentUser.getUser_id().equals(userId)) {
                return currentUser;
            }
        }
        return null;
    }

    /**
     * This method costs O(n) time complexity since the worst case is where
     * an user is friends with all other users (i.e. one vertex contains n edges)
     * 
     * @param userId String that represents user ID
     * @return List of user objects that is being represented as nodes
     */
    @Override
    public List<User> getListOfNodes(String userId) {
        User targetUser = getNode(userId);
        String targetUserFriendIds = targetUser.getFriends();

        if (targetUserFriendIds == null || targetUserFriendIds.isEmpty()) {
            return null;
        }

        String[] targetUserFriendIdsList = targetUserFriendIds.split(",");
        List<User> targetUserFriends = new ArrayList<>();

        for (String friendId : targetUserFriendIdsList) {
            targetUserFriends.add(getNode(friendId.strip()));
        }
        // System.out.println(targetUserFriends);
        return targetUserFriends;
    }

    /**
     * This method is O(n^2) time complexity where the worst case is the input list 
     * contains all edges (i.e. n) and getNode() method is O(n) time complexity
     * 
     * @param userIds List of userIds
     * @return List of users objects that is represented as nodes
     */
    @Override
    public List<User> getListOfNodes(List<String> userIds) {
        List<User> targetUserFriends = new ArrayList<>();

        for (String userId: userIds) {
            targetUserFriends.add(getNode(userId));
        }

        return targetUserFriends;
    }
}
