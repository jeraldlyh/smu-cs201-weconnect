package com.dsa.graphs.service;

import com.dsa.graphs.dto.AdjacencyMatrixDTO;
import com.dsa.graphs.models.AdjacencyMatrix;

public interface AdjacencyMatrixService {
    AdjacencyMatrixDTO createAdjacencyMatrix();
    AdjacencyMatrix getAdjacencyMatrix();
}
