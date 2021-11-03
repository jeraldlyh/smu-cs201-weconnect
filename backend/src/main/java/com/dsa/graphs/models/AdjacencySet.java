package com.dsa.graphs.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AdjacencySet implements Graph {
    private Map<String, Set<String>> adjacencySet = new HashMap<>();

    /**
     * This method is O(|V|) time complexity where it instantiates
     * each vertex with an empty set.
     * 
     * @param nodes Collection of nodes that contain user data
     */
    @Override
    public void createVertices(Set<User> nodes) {
        for (User user : nodes) {
            addVertex(user.getUser_id().strip());
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

    public void updateSet(String fromUser, String toUser) {
        Set<String> userFriends = adjacencySet.get(fromUser);
        if (userFriends != null) {
            userFriends.add(toUser);
        }
    }

    /**
     * This method is O(1) as add() incurs O(1) time complexity
     */
    @Override
    public void addEdge(String fromUser, String toUser) {
        updateSet(fromUser, toUser);
        updateSet(toUser, fromUser);
    }

    @Override
    public void addVertex(String userId) {
        adjacencySet.put(userId, new HashSet<String>());        // put() method is O(1) time complexity
    }

    // Overriden method from Graph interface but is not applicable to this class
    @Override
    public void addVertex(String userId, int index) { }

    // This method is O(E) time complexity where it converts a Set to ArrayList
    public HashSet<String> getNeighbours(String user) {
        Set<String> neighbours = adjacencySet.get(user);
        if (neighbours == null) {
            return null;
        }
        return new HashSet<>(adjacencySet.get(user));    // get() method is O(1) time complexity
    }

    @Override
    public int getSize() {
        return adjacencySet.size();
    }

    @Override
    public void delete() {
        adjacencySet.clear();
    }

    public Map<String, Set<String>> getAdjacencySet() {
        return adjacencySet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // More optimized way compared to String

        for (Map.Entry<String, Set<String>> user : adjacencySet.entrySet()) {
            sb.append(user.getKey() + " ==> "); // append() is O(1)
            sb.append(user.getValue() + "\n");
        }
        return sb.toString();
    }
}
