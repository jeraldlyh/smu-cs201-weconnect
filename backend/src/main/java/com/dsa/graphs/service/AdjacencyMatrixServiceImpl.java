package com.dsa.graphs.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.dsa.graphs.dto.AdjacencyMatrixDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyMatrix;
import com.dsa.graphs.models.User;

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
        // Check if adjacencyList has been previous created
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
        long timeTaken = ChronoUnit.MILLIS.between(start, end);

        LOGGER.info("------ SUCCESSFULLY CREATED ADJACENCY MATRIX");
        AdjacencyMatrixDTO adjacencyMatrixDTO = new AdjacencyMatrixDTO(adjacencyMatrix, timeTaken);
        System.out.println(adjacencyMatrix);
        return adjacencyMatrixDTO;
    }

    @Override
    public AdjacencyMatrix getAdjacencyMatrix() {
        if (adjacencyMatrix == null || adjacencyMatrix.getSize() == 0) {
            createAdjacencyMatrix();
        }
        return adjacencyMatrix;
    }

    /**
     * This method is O(n^2) time complexity because of getListOfNodes() method which incurs O(n^2) time complexity.
     * It first searches through each row of the matrix and add adjacent vertex that has an edge into the queue
     * to traverse subsequently. It then adds the traversed node into a visited array to ensure that it does not 
     * get traversed again.
     */
    @Override
    public FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser) {
        createAdjacencyMatrix();

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

                String adjacentUserId = adjacencyMatrix.getUserIdByIndex(i);        // getUserIdByIndex() is O(n) time complexity

                // Check if an edge is found
                if (matrix[userIndex][i] == 1) {
                    // Check if target vertex (i.e. user) has been found
                    if (adjacentUserId.equals(toUser)) {

                        // TODO: Does this method cause the parent method to be O(n^3)?

                        // getAdjacentVerticesId is O(n) time complexity
                        List<String> adjacentVerticesId = getAdjacentVerticesId(matrix, i);

                        // getListOfNodes() is O(n^2) time complexity
                        return new FriendSuggestionDTO(nodeService.getListOfNodes(adjacentVerticesId), degreeOfRelationship);
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
        LOGGER.info("------ NO USER FOUND AT THE END OF BFS FOR ADJACENCY MATRIX");
        return null;
    }

    /**
     * This method is O(n) time complexity where it has to traverse through the 
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
     * This method is O(n) time complexity where the worst case is that adjacentVerticesIndex 
     * contains n elements where the current vertex (i.e. user) is connected to all other vertices
     * 
     * @param matrix 2D array containing the edges of the vertices
     * @param index Integer that represents the index of the vertex in the matrix
     * @return List of String that represent adjacent vertices (i.e. userId)
     */
    public List<String> getAdjacentVerticesId(int[][] matrix, int index) {
        // O(n) time complexity, refer to above implementation
        List<Integer> adjacentVerticesIndex = getAdjacentVerticesByIndex(matrix, index);
        List<String> adjacentVerticesId = new ArrayList<>();

        // O(n) time complexity for this loop as well
        for (int i: adjacentVerticesIndex) {
            adjacentVerticesId.add(adjacencyMatrix.getUserIdByIndex(i));
        }
        return adjacentVerticesId;
    }
}
