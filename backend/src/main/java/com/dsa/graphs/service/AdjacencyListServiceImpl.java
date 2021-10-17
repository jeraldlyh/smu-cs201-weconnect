package com.dsa.graphs.service;

import java.util.Set;

import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.User;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyListServiceImpl implements AdjacencyListService {
    private Gson gson;
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyListServiceImpl.class);

    private AdjacencyList adjacencyList = new AdjacencyList();
    private NodeService nodeService;

    public AdjacencyListServiceImpl(Gson gson, AdjacencyList adjacencyList, NodeService nodeService) {
        this.gson = gson;
        this.adjacencyList = adjacencyList;
        this.nodeService = nodeService;
    }

    @Override
    public void createAdjacencyList() {
        Set<User> nodes = nodeService.getNodes();
        System.out.println(nodes.size());
    }
}
