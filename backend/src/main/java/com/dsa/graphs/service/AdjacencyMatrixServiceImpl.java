package com.dsa.graphs.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import com.dsa.graphs.dto.AdjacencyMatrixDTO;
import com.dsa.graphs.models.AdjacencyMatrix;
import com.dsa.graphs.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyMatrixServiceImpl implements AdjacencyMatrixService {
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyMatrixService.class);

    private AdjacencyMatrix adjacencyMatrix;
    private NodeService nodeService;

    @Autowired
    public AdjacencyMatrixServiceImpl(NodeService nodeService) {
        this.nodeService = nodeService;
        // this.adjacencyMatrix = adjacencyMatrix;
    }

    @Override
    public AdjacencyMatrixDTO createAdjacencyMatrix() {
        // Check if adjacencyList has been previous created
        if (adjacencyMatrix != null && adjacencyMatrix.getSize() != 0) {
            return new AdjacencyMatrixDTO(adjacencyMatrix, 0);
        }

        
        LOGGER.info("------ STARTING TO CREATE ADJACENCY MATRIX");
        Set<User> nodes = nodeService.getNodes();

        adjacencyMatrix = new AdjacencyMatrix(nodes.size());
        LocalDateTime start = LocalDateTime.now();

        adjacencyMatrix.createNodes(nodes);
        adjacencyMatrix.createEdges(nodes);

        LocalDateTime end = LocalDateTime.now();
        long timeTaken = ChronoUnit.MILLIS.between(start, end);

        LOGGER.info("------ SUCCESSFULLY CREATED ADJACENCY MATRIX");
        AdjacencyMatrixDTO adjacencyMatrixDTO = new AdjacencyMatrixDTO(adjacencyMatrix, timeTaken);
        return adjacencyMatrixDTO;
    }

    @Override
    public AdjacencyMatrix getAdjacencyMatrix() {
        if (adjacencyMatrix == null || adjacencyMatrix.getSize() == 0) {
            createAdjacencyMatrix();
        }
        System.out.println(adjacencyMatrix);
        return adjacencyMatrix;
    }
}
