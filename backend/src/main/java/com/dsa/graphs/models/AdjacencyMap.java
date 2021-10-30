package com.dsa.graphs.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AdjacencyMap implements Graph {
    private Map<String, LinkedList<String>> adjacencyMap = new HashMap<>();

    /**
     * This method is O(|V|) time complexity where it instantiates
     * each vertex with an empty linked list.
     * 
     * @param nodes Collection of nodes that contain user data
     */
    @Override
    public void createVertices(Set<User> nodes) {
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

    public void updateLinkedList(String fromUser, String toUser) {
        LinkedList<String> userFriends = adjacencyMap.get(fromUser);
        if (userFriends != null) {
            // Store a boolean to check if user already exists in the linkedList
            boolean isExist = false;
            Iterator<String> userFriendsIter = userFriends.iterator();

            // This loop is O(|V|) time complexity as the worst case is where
            // the linkedList has to look through all the vertices (i.e. all users) stored inside
            while (userFriendsIter.hasNext()) {
                if (userFriendsIter.next().equals(toUser)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) userFriends.add(toUser);            // add() is O(1) time complexity
        }
    }

    /**
     * This method is O(|V|) as it has to search if there's a duplicate node
     */
    @Override
    public void addEdge(String fromUser, String toUser) {
        updateLinkedList(fromUser, toUser);
        updateLinkedList(toUser, fromUser);
    }

    @Override
    public void addVertex(String userId) {
        adjacencyMap.put(userId, new LinkedList<String>());        // put() method is O(1) time complexity
    }

    // Overriden method from Graph interface but is not applicable to this class
    @Override
    public void addVertex(String userId, int index) { }

    public List<String> getNeighbours(String user) {
        return adjacencyMap.get(user);     // get() method is O(1) time complexity
    }

    @Override
    public int getSize() {
        return adjacencyMap.size();
    }

    @Override
    public void delete() {
        adjacencyMap.clear();
    }

    public Map<String, LinkedList<String>> getAdjacencyMap() {
        return adjacencyMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // More optimized way compared to String

        for (Map.Entry<String, LinkedList<String>> user : adjacencyMap.entrySet()) {
            sb.append(user.getKey() + " ==> "); // append() is O(1)
            sb.append(user.getValue() + "\n");
        }
        return sb.toString();
    }
}
