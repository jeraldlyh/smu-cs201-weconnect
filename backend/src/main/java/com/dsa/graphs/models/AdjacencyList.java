package com.dsa.graphs.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AdjacencyList implements Graph {
    private Map<String, LinkedList<String>> adjacencyList = new HashMap<>();

    @Override
    public void createNodes(Set<User> nodes) {
        // for (User user : nodes) {
        //     String friends = user.getFriends();

        //     if (friends != null) {
        //         String[] listOfFriends = user.getFriends().split(",");

        //         for (String friendID : listOfFriends) {
        //             if (!friendID.isEmpty()) { // Prevent adding empty vertices
        //                 addNode(friendID.strip());
        //             }
        //         }
        //     }
        //     addNode(user.getUser_id().strip());
        // }
        for (User user : nodes) {
            addNode(user.getUser_id().strip());
        }
    }

    @Override
    public void createEdges(Set<User> nodes) {
        for (User user : nodes) {
            String friends = user.getFriends();

            if (friends != null) { // Checks if user has friends
                String[] listOfFriends = user.getFriends().split(",");

                for (String friendId : listOfFriends) {
                    // Prevent adding empty edges
                    if (!friendId.isEmpty()) {
                        addEdge(user.getUser_id().strip(), friendId.strip());
                    }
                }
            }
        }
    }

    @Override
    public void addEdge(String fromUser, String toUser) {
        LinkedList<String> fromUserFriends = adjacencyList.get(fromUser);
        if (fromUserFriends != null) {
            fromUserFriends.add(toUser);
        }

        LinkedList<String> toUserFriends = adjacencyList.get(toUser);
        if (toUserFriends != null) {
            toUserFriends.add(fromUser);
        }
    }

    @Override
    public void addNode(String userId) {
        adjacencyList.put(userId, new LinkedList<String>());        // put() method is O(1) time complexity
    }

    // Overriden method from Graph interface but is  not applicable to this class
    @Override
    public void addNode(String userId, int index) { }

    public LinkedList<String> getNeighbours(String user) {
        return adjacencyList.get(user);
    }

    public int getSize() {
        return adjacencyList.size();
    }

    public Map<String, LinkedList<String>> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // More optimized way compared to String

        for (Map.Entry<String, LinkedList<String>> user : adjacencyList.entrySet()) {
            sb.append(user.getKey() + " ==> "); // append() is O(1)
            sb.append(user.getValue() + "\n");
        }
        return sb.toString();
    }
}
