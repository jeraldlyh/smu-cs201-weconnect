package com.dsa.graphs.service;

import java.util.Set;

import com.dsa.graphs.models.User;

public interface AdjacencyListService {
    void createAdjacencyList();
    void createNodes(Set<User> nodes);
    void createEdges(Set<User> nodes);
    void addEdge(String fromUser, String toUser);
    void addNode(String user);
}
