package com.dsa.graphs.service;

import java.util.Set;

import com.dsa.graphs.models.User;

public interface NodeService {
    void generateNodes();
    Set<User> getNodes();
    User getNode(String userId);
}
