package com.dsa.graphs.models;

import java.util.Set;

public interface Graph {
    void createVertices(Set<User> nodes);
    void createEdges(Set<User> nodes);
    void addVertex(String userId);
    void addVertex(String userId, int index);
    void addEdge(String fromUser, String toUser);
    void delete();
    int getSize();
}
