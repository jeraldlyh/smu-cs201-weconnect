package com.dsa.graphs.service;

import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.AdjacencyListDTO;

public interface AdjacencyListService {
    AdjacencyListDTO createAdjacencyList();
    // void createNodes(Set<User> nodes);
    // void createEdges(Set<User> nodes);
    // void addEdge(String fromUser, String toUser);
    // void addNode(String user);
    AdjacencyList getAdjacencyList();
}
