package com.dsa.graphs.service;

import java.util.List;
import java.util.Set;

import com.dsa.graphs.models.User;

public interface NodeService {
    void generateNodes();
    Set<User> getNodes();
    List<User> getRandomNodes();
    User getNode(String userId);
    List<User> getListOfNodes(String userId);
    List<User> getListOfNodes(List<String> indexes);
}
