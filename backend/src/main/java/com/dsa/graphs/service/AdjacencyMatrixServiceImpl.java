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
            int userIndex = adjacencyMatrix.getIndexByUserId(userId);

            for (int i = 0; i < matrix.length; i++) {
                // Skip the validation for edge (i.e. relationship) that connect the vertex (i.e. user) to itself
                if (i == userIndex) continue;

                String adjacentUserId = adjacencyMatrix.getUserIdByIndex(i);        // getUserIdByIndex() is O(n) time complexity

                // Check if an edge is found
                if (matrix[userIndex][i] == 1) {
                    // Check if target vertex (i.e. user) has been found
                    if (adjacentUserId.equals(toUser)) {
                        return new FriendSuggestionDTO(nodeService.getListOfNodes(adjacentUserId), degreeOfRelationship);
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

}
