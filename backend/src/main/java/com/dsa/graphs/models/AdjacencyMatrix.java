package com.dsa.graphs.models;

import java.util.Map;
import java.util.Set;

public class AdjacencyMatrix implements Graph {
    private Map<String, Integer> vertices;
    private int[][] matrix;
    
    // private Map<String, Map<String, Integer>> matrix;

    public AdjacencyMatrix(int size) {
        matrix = new int[size][size];
    }

    /**
     * This method assigns an index to each vertex 
     * to be able to map a 2D matrix
     * @param nodes Collection of nodes that contain user data
     */
    @Override
    public void createNodes(Set<User> nodes) {
        int index = 0;
        for (User user: nodes) {
            addNode(user.getUser_id(), index++);
        }
    }

    @Override
    public void createEdges(Set<User> nodes) {
        for (User user: nodes) {
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
    public void addNode(String userId, int index) {
        vertices.put(userId, index);                // put() method is O(1) time complexity
    }

    // Overriden method from Graph interface but is  not applicable to this class
    @Override
    public void addNode(String userId) {}

    @Override
    public void addEdge(String fromUser, String toUser) {
        // Use wrapper int class to store null values in the event where user does not exist
        Integer indexOfFromUser = vertices.get(fromUser);
        Integer indexOfToUser = vertices.get(toUser);

        if (indexOfFromUser != null && indexOfToUser != null) {
            matrix[indexOfFromUser][indexOfToUser] = 1;
        }
    }
}
