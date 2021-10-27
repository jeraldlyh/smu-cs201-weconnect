package com.dsa.graphs.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.dsa.graphs.dto.AdjacencySetDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencySet;
import com.dsa.graphs.models.User;
import com.dsa.graphs.util.Time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AdjacencySetImpl implements AdjacencySetService {
    private static final Logger LOGGER = LogManager.getLogger(AdjacencySetImpl.class);

    private AdjacencySet adjacencySet;
    private NodeService nodeService;

    public AdjacencySetImpl(NodeService nodeService, AdjacencySet adjacencyListSet) {
        this.nodeService = nodeService;
        this.adjacencySet = adjacencyListSet;
    }

    @Override
    public AdjacencySetDTO createAdjacencySet() {
        // Check if adjacencyList has been previous created
        if (adjacencySet != null && adjacencySet.getSize() != 0) {
            return new AdjacencySetDTO(adjacencySet, 0);
        }

        LOGGER.info("------ STARTING TO CREATE ADJACENCY SET");
        Set<User> nodes = nodeService.getNodes();
        LocalDateTime start = LocalDateTime.now();

        adjacencySet.createVertices(nodes);        // O(|V|) time complexity
        adjacencySet.createEdges(nodes);           // O(|V|) time complexity

        LocalDateTime end = LocalDateTime.now();
        long timeTaken = ChronoUnit.MILLIS.between(start, end);

        LOGGER.info("------ SUCCESSFULLY CREATED ADJACENCY SET");
        AdjacencySetDTO adjacencySetDTO = new AdjacencySetDTO(adjacencySet, timeTaken);
        return adjacencySetDTO;
    }

    @Override
    public AdjacencySet getAdjacencySet(boolean create) {
        if (create && (adjacencySet == null || adjacencySet.getSize() == 0)) {
            createAdjacencySet();
        }
        return adjacencySet;
    }

    /**
     * Modified BFS is required as the entire graph might possibly be disconnected as 
     * there might be a case where not all the users are related to each other through
     * some way or another. Instead of traversing from the starting node only, we'll traverse 
     * every single unvisited node.
     * 
     * @param fromUser User in the graph
     * @param toUser Specified user that the caller wishes to add
     * @param visted List that contains visited vertices
     * @param originalUser User that is specifying the request
     * @return List of suggested friends
     */
    public FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser, List<String> visited, String originalUser) {
        Queue<String> queue = new LinkedList<String>();     // Create a queue to store vertices that has an edge connected to current vertex and have yet to visit
        int degreeOfRelationship = 0;                       // Create an integer to check how deep are the connections from the user to another specified user

        visited.add(fromUser);
        queue.add(fromUser);

        LOGGER.info("------ STARTING BFS SEARCH FOR ADJACENCY SET");

        while(queue.size() != 0) {
            // Dequeue the vertex from the queue
            String userId = queue.poll();
            Set<String> adjacentVertices = adjacencySet.getNeighbours(userId);

            if (adjacentVertices != null && adjacentVertices.size() != 0) {                 // Check if current vertex contains any edges

                // This loop is O(|V|) time complexity where the worst case is that the Set contains edges to all other users
                for (String neighbour: adjacentVertices) {
                    // Found the vertex that we're looking for in the graph
                    if (toUser.equals(neighbour)) {
                        // Corner case where the node does not exist as a vertex in the graph
                        // but it's located in user's friend list
                        User targetUser = nodeService.getNode(toUser);

                        if (targetUser == null) {
                            LOGGER.info("------ USER DOES NOT EXIST IN GRAPH");
                            return null;
                        }

                        // Establish an relationship between the users within BFS so that 
                        // we are able to obtain the updated adjacent nodes
                        LOGGER.info("------ FRIENDSHIP FORMED: " + originalUser + " | " + toUser);
                        adjacencySet.addEdge(originalUser, toUser);

                        // Removes the set difference between user's existing friend and target user's friend
                        // removeAll() is O(n * m) where ArrayList contains() method is O(n)
                        Set<String> fromUserAdjacentVertices = adjacencySet.getNeighbours(originalUser);
                        adjacentVertices.removeAll(fromUserAdjacentVertices);
                        
                        LOGGER.info("------ SUCCESSFULLY FOUND USER: " + targetUser);
                        System.out.println(adjacencySet);
                        return new FriendSuggestionDTO(nodeService.getListOfNodes(new ArrayList<>(adjacentVertices)), degreeOfRelationship);
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
        LOGGER.info("------ NO USER FOUND AT THE END OF BFS FOR ADJACENCY SET");
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
        createAdjacencySet();

        LocalDateTime start = LocalDateTime.now();
        List<String> visited = new ArrayList<String>();        // Create a set to mark vertices that are visited
        Map<String, Set<String>> data = adjacencySet.getAdjacencySet();
        FriendSuggestionDTO result = null;

        for (String user: data.keySet()) {
            if (!visited.contains(user)) {
                result = getFriendSuggestionsByBfs(user, toUser, visited, fromUser);

                if (result != null) {       // Exit once user has been found
                    break;
                }
            }
        }

        if (result == null) {
            result = new FriendSuggestionDTO(null, 0);
        }
        result.setTimeTaken(Time.calculateTimeTaken(start, LocalDateTime.now()));
        return result;
    }

    @Override
    public void deleteAdjacencySet() {
        if (adjacencySet != null) {
            LOGGER.info("------ DELETING ADJACENCY SET");
            adjacencySet.delete();
            LOGGER.info("------ SUCCESSFULLY DELETED ADJACENCY SET");
        }
    }
}
