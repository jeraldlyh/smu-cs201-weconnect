package com.dsa.graphs.service;

import com.dsa.graphs.dto.StatusDTO;
import com.dsa.graphs.models.AdjacencyMap;
import com.dsa.graphs.models.AdjacencyMatrix;
import com.dsa.graphs.models.AdjacencySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private AdjacencyMapService adjacencyMapService;
    private AdjacencyMatrixService adjacencyMatrixService;
    private AdjacencySetService adjacencySetService;

    @Autowired
    public StatusServiceImpl(AdjacencyMapService adjacencyMapService, AdjacencyMatrixService adjacencyMatrixService, AdjacencySetService adjacencySetService) {
        this.adjacencyMapService = adjacencyMapService;
        this.adjacencyMatrixService = adjacencyMatrixService;
        this.adjacencySetService = adjacencySetService;
    }

    @Override
    public StatusDTO getStatus() {
        AdjacencyMap adjacencyMap = adjacencyMapService.getAdjacencyMap(false);
        AdjacencyMatrix adjacencyMatrix = adjacencyMatrixService.getAdjacencyMatrix(false);
        AdjacencySet adjacencySet = adjacencySetService.getAdjacencySet(false);
        boolean adjacencyMapStatus = true;
        boolean adjacencyMatrixStatus = true;
        boolean adjacencySetStatus = true;

        if (adjacencyMap == null || adjacencyMap.getSize() == 0) {
            adjacencyMapStatus = false;
        }

        if (adjacencyMatrix == null || adjacencyMatrix.getSize() == 0) {
            adjacencyMatrixStatus = false;
        }

        if (adjacencySet == null || adjacencySet.getSize() == 0) {
            adjacencySetStatus = false;
        }

        return new StatusDTO(adjacencyMapStatus, adjacencyMatrixStatus, adjacencySetStatus);
    }
}
