package com.dsa.graphs.service;

import java.util.List;

import com.dsa.graphs.dto.AdjacencyMatrixDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyMatrix;

public interface AdjacencyMatrixService {
    AdjacencyMatrixDTO createAdjacencyMatrix();
    AdjacencyMatrix getAdjacencyMatrix(boolean create);
    void deleteAdjacencyMatrix();
    FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser);
    List<Integer> getAdjacentVerticesByIndex(int[][] matrix, int index);
    List<String> getAdjacentVerticesId(int[][] matrix, int index);
}
