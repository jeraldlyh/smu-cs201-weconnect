package com.dsa.graphs.models;

import java.util.Set;

public interface Graph {
    void createNodes(Set<User> nodes);
    void createEdges(Set<User> nodes);
    void addNode(String userId);
    void addNode(String userId, int index);
    void addEdge(String fromUser, String toUser);
}
