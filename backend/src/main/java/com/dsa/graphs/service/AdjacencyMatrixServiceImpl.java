package com.dsa.graphs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.dsa.graphs.dto.AdjacencyMatrixDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyMatrix;
import com.dsa.graphs.models.User;
import com.dsa.graphs.util.Time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyMatrixServiceImpl implements AdjacencyMatrixService {
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyMatrixService.class);

    private AdjacencyMatrix adjacencyMatrix;
    private NodeService nodeService;

    @Autowired
    public AdjacencyMatrixServiceImpl(NodeService nodeService) {
        this.nodeService = nodeService;
        // this.adjacencyMatrix = adjacencyMatrix;
    }

    @Override
    public AdjacencyMatrixDTO createAdjacencyMatrix() {
        // Check if adjacencyMatrix has been previous created
        if (adjacencyMatrix != null && adjacencyMatrix.getSize() != 0) {
            return new AdjacencyMatrixDTO(adjacencyMatrix, 0);
        }

        
        LOGGER.info("------ STARTING TO CREATE ADJACENCY MATRIX");
        Set<User> nodes = nodeService.getNodes();

        adjacencyMatrix = new AdjacencyMatrix(nodes.size());
        LocalDateTime start = LocalDateTime.now();

        adjacencyMatrix.createVertices(nodes);
        adjacencyMatrix.createEdges(nodes);

        LocalDateTime end = LocalDateTime.now();

        LOGGER.info("------ SUCCESSFULLY CREATED ADJACENCY MATRIX");
        AdjacencyMatrixDTO adjacencyMatrixDTO = new AdjacencyMatrixDTO(
            adjacencyMatrix, 
            Time.calculateTimeTaken(start, end)
        );
        return adjacencyMatrixDTO;
    }

    @Override
    public AdjacencyMatrix getAdjacencyMatrix(boolean create) {
        if (create && (adjacencyMatrix == null || adjacencyMatrix.getSize() == 0)) {
            createAdjacencyMatrix();
        }
        return adjacencyMatrix;
    }

    /**
     * This method is O(|V|^2) time complexity because of getListOfNodes() method which incurs O(|V|^2) time complexity.
     * Steps:
     * 1. It adds an edge between the vertices
     * 2. Searches through each row of the matrix and add adjacent vertex that has an edge into the queue to traverse subsequently.
     * 3. It then adds the traversed node into a visited array to ensure that it does not get traversed again.
     * 4. When a result is found, return the set difference between the current user friends and the target user friends to prevent duplicates
     * 
     * @param fromUser String representing the userId of the caller
     * @param toUser String representing the userId of specified user
     * @return FriendSuggestionDTO response model
     */
    @Override
    public FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser) {
        createAdjacencyMatrix();

        LocalDateTime start = LocalDateTime.now();

        Queue<String> queue = new LinkedList<>();
        List<String> visited = new ArrayList<>();
        int degreeOfRelationship = 0;
        int[][] matrix = adjacencyMatrix.getAdjacencyMatrix();

        queue.add(fromUser);
        visited.add(fromUser);

        LOGGER.info("------ STARTING BFS SEARCH FOR ADJACENCY MATRIX");

        while (queue.size() != 0) {
            // Remove vertex from queue
            String userId = queue.poll();
            int userIndex = adjacencyMatrix.getIndexByUserId(userId);               // O(1) time complexity

            for (int i = 0; i < matrix.length; i++) {
                // Skip the validation for edge (i.e. relationship) that connect the vertex (i.e. user) to itself
                if (i == userIndex) continue;

                String adjacentUserId = adjacencyMatrix.getUserIdByIndex(i);        // getUserIdByIndex() is O(|V|) time complexity

                // Check if an edge is found
                if (matrix[userIndex][i] == 1) {
                    // Check if target vertex (i.e. user) has been found
                    if (adjacentUserId.equals(toUser)) {
                        // getAdjacentVerticesId() is O(|V|) time complexity
                        List<String> adjacentVerticesId = getAdjacentVerticesId(matrix, i);
                        List<String> fromUserAdjacentVerticesId = getAdjacentVerticesId(matrix, adjacencyMatrix.getIndexByUserId(fromUser));
                        
                        // Removes the set difference between user's existing friend and target user's friend
                        // removeAll() is O(n * m) where ArrayList contains() method is O(n)
                        adjacentVerticesId.removeAll(fromUserAdjacentVerticesId);

                        LOGGER.info("------ SUCCESSFULLY FOUND USER: " + adjacentUserId);

                        // Establish an relationship between the users after BFS
                        LOGGER.info("------ FRIENDSHIP FORMED: " + fromUser + " | " + toUser);
                        adjacencyMatrix.addEdge(fromUser, toUser);

                        // getListOfNodes() is O(|V|) time complexity
                        return new FriendSuggestionDTO(
                            nodeService.getListOfNodes(adjacentVerticesId), 
                            degreeOfRelationship, 
                            Time.calculateTimeTaken(start, LocalDateTime.now())
                        );
                    }

                    // Check if the vertex has been visited previously to prevent infinite loop
                    if (!visited.contains(adjacentUserId)) {
                        visited.add(adjacentUserId);
                        queue.add(adjacentUserId);
                    }
                }
            }
            degreeOfRelationship++;
        }
        // Establish an relationship between the users after BFS
        LOGGER.info("------ FRIENDSHIP FORMED: " + fromUser + " | " + toUser);
        adjacencyMatrix.addEdge(fromUser, toUser);

        LOGGER.info("------ NO USER FOUND AT THE END OF BFS FOR ADJACENCY MATRIX");
        return new FriendSuggestionDTO(null, 0, Time.calculateTimeTaken(start, LocalDateTime.now()));
    }

    /**
     * This method is O(|V|) time complexity where it has to traverse through the 
     * entire row to find edges
     * @param matrix 2D array containing the edges of the vertices
     * @param index Integer that represents the index of the vertex in the matrix
     * @return List of indexes that represent adjacent vertices that are connected to the specified vertex (i.e. user)
     */
    @Override
    public List<Integer> getAdjacentVerticesByIndex(int[][] matrix, int index) {
        List<Integer> adjacentVertices = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (i == index) continue;

            // Check if there's an edge
            if (matrix[index][i] == 1) {
                adjacentVertices.add(i);
            }
        }

        return adjacentVertices;
    }

    /**
     * This method is O(|V|) time complexity where the worst case is that adjacentVerticesIndex 
     * contains all the vertices where the current vertex (i.e. user) is connected to them
     * 
     * @param matrix 2D array containing the edges of the vertices
     * @param index Integer that represents the index of the vertex in the matrix
     * @return List of String that represent adjacent vertices (i.e. userId)
     */
    public List<String> getAdjacentVerticesId(int[][] matrix, int index) {
        // O(|V|) time complexity, refer to above implementation
        List<Integer> adjacentVerticesIndex = getAdjacentVerticesByIndex(matrix, index);
        List<String> adjacentVerticesId = new ArrayList<>();

        // O(|V|) time complexity for this loop as well
        for (int i: adjacentVerticesIndex) {
            adjacentVerticesId.add(adjacencyMatrix.getUserIdByIndex(i));
        }
        return adjacentVerticesId;
    }

    @Override
    public void deleteAdjacencyMatrix() {
        if (adjacencyMatrix != null) {
            LOGGER.info("------ DELETING ADJACENCY MATRIX");
            adjacencyMatrix.delete();
            LOGGER.info("------ SUCCESSFULLY DELETED ADJACENCY MATRIX");
        }
    }
}
