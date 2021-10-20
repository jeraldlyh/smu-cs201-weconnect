package com.dsa.graphs.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AdjacencyList {
    private Map<String, LinkedList<String>> adjacencyList = new HashMap<>();

    public void createEdges(Set<User> nodes) {
        for (User user : nodes) {
            String friends = user.getFriends();

            if (friends != null) {      // Checks if user has friends
                String[] listOfFriends = user.getFriends().split(",");

                for (String friendID : listOfFriends) {
                    addEdge(user.getUser_id(), friendID);
                }
            }
        }
    }

    public void createNodes(Set<User> nodes) {
        for (User user : nodes) {
            String friends = user.getFriends();

            if (friends != null) {
                String[] listOfFriends = user.getFriends().split(",");

                for (String friendID : listOfFriends) {
                    addNode(friendID);
                }
            }
            addNode(user.getUser_id());
        }
    }

    public void addEdge(String fromUser, String toUser) {
        adjacencyList.get(fromUser).add(toUser);
        adjacencyList.get(toUser).add(fromUser);
    }

    public void addNode(String userID) {
        adjacencyList.put(userID, new LinkedList<String>());
    }

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
        StringBuilder sb = new StringBuilder();     // More optimized way compared to String

        for (Map.Entry<String, LinkedList<String>> user: adjacencyList.entrySet()) {
            sb.append(user.getKey() + " ==> ");     // append() is O(1)
            sb.append(user.getValue() + "\n");
        }
        return sb.toString();
    }
}
