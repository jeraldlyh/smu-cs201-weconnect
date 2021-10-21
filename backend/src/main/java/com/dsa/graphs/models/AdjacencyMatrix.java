package com.dsa.graphs.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AdjacencyMatrix implements Graph {
    private Map<String, Integer> vertices = new HashMap<>();
    private int[][] matrix;

    public AdjacencyMatrix(int size) {
        matrix = new int[size][size];
    }

    /**
     * This method is O(|V|) time complexity where it assigns 
     * an index to each vertex to be able to map a 2D matrix.
     * 
     * @param nodes Collection of nodes that contain user data
     */
    @Override
    public void createVertices(Set<User> nodes) {
        int index = 0;
        for (User user: nodes) {
            addVertex(user.getUser_id(), index++);
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
    public void addVertex(String userId, int index) {
        vertices.put(userId, index);                // put() method is O(1) time complexity
    }

    // Overriden method from Graph interface but is not applicable to this class
    @Override
    public void addVertex(String userId) {}

    @Override
    public void addEdge(String fromUser, String toUser) {
        // Use wrapper int class to store null values in the event where vertex (i.e. user) does not exist

        Integer indexOfFromUser = vertices.get(fromUser);
        Integer indexOfToUser = vertices.get(toUser);

        if (indexOfFromUser != null && indexOfToUser != null) {
            // O(1) time complexity
            matrix[indexOfFromUser][indexOfToUser] = 1;
            matrix[indexOfToUser][indexOfFromUser] = 1;
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    public int[][] getAdjacencyMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();      // More optimized way compared to String

        for (Map.Entry<String, Integer> entry: vertices.entrySet()) {
            String userId = entry.getKey();
            Integer index = entry.getValue();

            sb.append(userId);
            
            // Row of relationship that belongs to the user
            for (int i = 0; i < matrix.length; i++) {
                if (index == i) continue;                       // Skip the edge if the vertex (i.e. user) is connected to itself
                if (matrix[index][i] == 1) {                    // Append to output if an edge (i.e. relationship) is found
                    sb.append(" => " + getUserIdByIndex(i));    // TODO: O(n^2) here?
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * This method is O(|V|) time complexity where it searches through the list of vertices to obtain the index
     * 
     * @param index Integer that represents a vertex (i.e. user)
     * @return String that represents userId
     */
    public String getUserIdByIndex(int index) {
        for (Map.Entry<String, Integer> entry: vertices.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * This method is O(1) time complexity
     * 
     * @param userId String that represent the userId
     * @return Integer indicating the index that was allocated to this vertex
     */
    public int getIndexByUserId(String userId) {
        return vertices.get(userId);
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
        }
    }
}
