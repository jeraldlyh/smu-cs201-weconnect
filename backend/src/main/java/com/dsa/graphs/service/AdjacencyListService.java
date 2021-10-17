package com.dsa.graphs.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dsa.graphs.models.AdjacencyListDTO;
import com.dsa.graphs.models.User;

public interface AdjacencyListService {
    AdjacencyListDTO createAdjacencyList();
    void createNodes(Set<User> nodes);
    void createEdges(Set<User> nodes);
    void addEdge(String fromUser, String toUser);
    void addNode(String user);
    Map<String, List<String>> getAdjacencyList();
}
