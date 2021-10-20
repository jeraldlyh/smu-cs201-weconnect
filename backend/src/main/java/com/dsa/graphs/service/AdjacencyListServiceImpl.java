package com.dsa.graphs.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.AdjacencyListDTO;
import com.dsa.graphs.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyListServiceImpl implements AdjacencyListService {
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyListServiceImpl.class);

    private AdjacencyList adjacencyList;
    private NodeService nodeService;

    public AdjacencyListServiceImpl(NodeService nodeService, AdjacencyList adjacencyList) {
        this.nodeService = nodeService;
        this.adjacencyList = adjacencyList;
    }

    @Override
    public AdjacencyListDTO createAdjacencyList() {
        LOGGER.info("------ STARTING TO CREATE ADJACENCY LIST");
        Set<User> nodes = nodeService.getNodes();
        LocalDateTime start = LocalDateTime.now();

        adjacencyList.createNodes(nodes);
        adjacencyList.createEdges(nodes);

        LocalDateTime end = LocalDateTime.now();
        long timeTaken = ChronoUnit.MILLIS.between(start, end);

        LOGGER.info("------ SUCCESSFULLY CREATED ADJACENCY LIST");
        AdjacencyListDTO adjacencyListDTO = new AdjacencyListDTO(adjacencyList, timeTaken);
        return adjacencyListDTO;
    }

    @Override
    public AdjacencyList getAdjacencyList() {
        if (adjacencyList.getSize() == 0) {
            createAdjacencyList();
        }
        // System.out.print(adjacencyList.getSize());
        return adjacencyList;
    }
}
