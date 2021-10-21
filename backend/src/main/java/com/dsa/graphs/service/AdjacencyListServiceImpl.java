package com.dsa.graphs.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.dsa.graphs.dto.AdjacencyListDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyListServiceImpl implements AdjacencyListService {
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyListServiceImpl.class);

    private AdjacencyList adjacencyList;
    private NodeService nodeService;

    public AdjacencyListServiceImpl(NodeService nodeService, AdjacencyList adjacencyList) {
        this.nodeService = nodeService;
        this.adjacencyList = adjacencyList;
    }

    @Override
    public AdjacencyListDTO createAdjacencyList() {
        // Check if adjacencyList has been previous created
        if (adjacencyList.getSize() != 0) {
            return new AdjacencyListDTO(adjacencyList, 0);
        }

        LOGGER.info("------ STARTING TO CREATE ADJACENCY LIST");
        Set<User> nodes = nodeService.getNodes();
        LocalDateTime start = LocalDateTime.now();

        adjacencyList.createVertices(nodes);        // O(|V|) time complexity
        adjacencyList.createEdges(nodes);           // O(|V|) time complexity

        LocalDateTime end = LocalDateTime.now();
        long timeTaken = ChronoUnit.MILLIS.between(start, end);

        LOGGER.info("------ SUCCESSFULLY CREATED ADJACENCY LIST");
        AdjacencyListDTO adjacencyListDTO = new AdjacencyListDTO(adjacencyList, timeTaken);
        return adjacencyListDTO;
    }

    @Override
    public AdjacencyList getAdjacencyList() {
        if (adjacencyList.getSize() == 0) {
            createAdjacencyList();
        }
        return adjacencyList;
    }

    /**
     * Modified BFS is required as the entire graph might possibly be disconnected as 
     * there might be a case where not all the users are related to each other through
     * some way or another. Instead of traversing from the starting node only, we'll traverse 
     * every single unvisited node.
     * 
     * @param fromUser User specifying the request
     * @param toUser Specified user that the caller wishes to add
     * @param visted List that contains visited vertices
     * @return List of suggested friends
     */
    public FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser, List<String> visited) {
        Queue<String> queue = new LinkedList<String>();     // Create a queue to store vertices that has an edge connected to current vertex and have yet to visit
        int degreeOfRelationship = 0;                          // Create an integer to check how deep are the connections from the user to another specified user

        visited.add(fromUser);
        queue.add(fromUser);

        LOGGER.info("------ STARTING BFS SEARCH FOR ADJACENCY LIST");

        while(queue.size() != 0) {
            // Dequeue the vertex from the queue
            String vertex = queue.poll();
            List<String> adjacentVertices = adjacencyList.getNeighbours(vertex);

            if (adjacentVertices != null && adjacentVertices.size() != 0) {                 // Check if current vertex contains any edges

                // This loop is O(|V|) time complexity where the worst case is that the LinkedList contains edges to all other users
                for (String neighbour: adjacentVertices) {
                    // Found the vertex that we're looking for in the graph
                    if (toUser.equals(neighbour)) {
                        List<String> adjacentVerticesId = getAdjacentVerticesId(toUser);

                        LOGGER.info("------ SUCCESSFULLY FOUND USER: " + nodeService.getNode(toUser));
                        return new FriendSuggestionDTO(nodeService.getListOfNodes(adjacentVerticesId), degreeOfRelationship);
                    }

                    // Check if neighbour has been previously visited to prevent an infinite recursion
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);             // Ensure that neighbour will not be visited from its subsequent neighbours
                        queue.add(neighbour);               // Add it into the queue to traverse later
                    }
                }
            }
            degreeOfRelationship++;
        }
        LOGGER.info("------ NO USER FOUND AT THE END OF BFS FOR ADJACENCY LIST");
        return null;
    }

    /**
     * This method calls its overloaded method where it runs BFS on every single vertex since
     * the graph might be disconnected.
     * 
     * Steps:
     * 1. Adds an edge between the vertices
     * 2. Runs BFS on each vertex that has yet to be visited
     * 3. Stops running BFS on susbequent vertices if the specified user has been found
     * 
     * @param fromUser String that represents the caller userId
     * @param toUser String that represents the target userId
     * @return FriendSuggestionDTO response model
     */
    @Override
    public FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser) {
        createAdjacencyList();
        adjacencyList.addEdge(fromUser, toUser);

        List<String> visited = new ArrayList<String>();        // Create a set to mark vertices that are visited
        Map<String, LinkedList<String>> data = adjacencyList.getAdjacencyList();
        FriendSuggestionDTO result = null;

        for (String user: data.keySet()) {
            if (!visited.contains(user)) {
                result = getFriendSuggestionsByBfs(fromUser, toUser, visited);

                if (result != null) {       // Exit once user has been found
                    break;
                }
            }
        }
        return result;
    }

    // This method is O(|V|) time complexity where it converts a LinkedList to ArrayList
    @Override
    public List<String> getAdjacentVerticesId(String userId) {
        return new ArrayList<>(adjacencyList.getNeighbours(userId));
    }
}
