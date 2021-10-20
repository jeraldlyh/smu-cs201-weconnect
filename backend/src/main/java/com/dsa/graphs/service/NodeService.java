package com.dsa.graphs.service;

import java.util.Map;

import com.dsa.graphs.models.User;

public interface NodeService {
    void generateNodes();
    Map<User, Object> getNodes();
    User getNode();
}
