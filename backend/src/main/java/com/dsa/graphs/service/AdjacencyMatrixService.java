package com.dsa.graphs.service;

import com.dsa.graphs.dto.AdjacencyMatrixDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyMatrix;

public interface AdjacencyMatrixService {
    AdjacencyMatrixDTO createAdjacencyMatrix();
    AdjacencyMatrix getAdjacencyMatrix();
    FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser);
}
