package com.dsa.graphs.service;

import com.dsa.graphs.dto.StatusDTO;
import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.AdjacencyMatrix;
import com.dsa.graphs.models.AdjacencySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private AdjacencyListService adjacencyListService;
    private AdjacencyMatrixService adjacencyMatrixService;
    private AdjacencySetService adjacencySetService;

    @Autowired
    public StatusServiceImpl(AdjacencyListService adjacencyListService, AdjacencyMatrixService adjacencyMatrixService, AdjacencySetService adjacencySetService) {
        this.adjacencyListService = adjacencyListService;
        this.adjacencyMatrixService = adjacencyMatrixService;
        this.adjacencySetService = adjacencySetService;
    }

    @Override
    public StatusDTO getStatus() {
        AdjacencyList adjacencyList = adjacencyListService.getAdjacencyList(false);
        AdjacencyMatrix adjacencyMatrix = adjacencyMatrixService.getAdjacencyMatrix(false);
        AdjacencySet adjacencySet = adjacencySetService.getAdjacencySet(false);
        boolean adjacencyListStatus = true;
        boolean adjacencyMatrixStatus = true;
        boolean adjacencySetStatus = true;

        if (adjacencyList == null || adjacencyList.getSize() == 0) {
            adjacencyListStatus = false;
        }

        if (adjacencyMatrix == null || adjacencyMatrix.getSize() == 0) {
            adjacencyMatrixStatus = false;
        }

        if (adjacencySet == null || adjacencySet.getSize() == 0) {
            adjacencySetStatus = false;
        }

        return new StatusDTO(adjacencyListStatus, adjacencyMatrixStatus, adjacencySetStatus);
    }
}
