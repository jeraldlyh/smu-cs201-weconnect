package com.dsa.graphs.service;

import java.util.List;

import com.dsa.graphs.dto.AdjacencyListDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyList;

public interface AdjacencyListService {
    AdjacencyListDTO createAdjacencyList();
    AdjacencyList getAdjacencyList();
    FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser);
    List<String> getAdjacentVerticesId(String userId);
}
