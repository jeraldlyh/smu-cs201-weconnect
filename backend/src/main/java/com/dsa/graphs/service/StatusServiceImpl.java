package com.dsa.graphs.service;

import com.dsa.graphs.dto.StatusDTO;
import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.AdjacencyMatrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    private AdjacencyListService adjacencyListService;
    private AdjacencyMatrixService adjacencyMatrixService;

    @Autowired
    public StatusServiceImpl(AdjacencyListService adjacencyListService, AdjacencyMatrixService adjacencyMatrixService) {
        this.adjacencyListService = adjacencyListService;
        this.adjacencyMatrixService = adjacencyMatrixService;
    }

    @Override
    public StatusDTO getStatus() {
        AdjacencyList adjacencyList = adjacencyListService.getAdjacencyList();
        AdjacencyMatrix adjacencyMatrix = adjacencyMatrixService.getAdjacencyMatrix();
        boolean adjacencyListStatus = true;
        boolean adjacencyMatrixStatus = true;

        if (adjacencyList == null || adjacencyList.getSize() == 0) {
            adjacencyListStatus = false;
        }

        if (adjacencyMatrix == null || adjacencyMatrix.getSize() == 0) {
            adjacencyMatrixStatus = false;
        }

        return new StatusDTO(adjacencyListStatus, adjacencyMatrixStatus);
    }
}
