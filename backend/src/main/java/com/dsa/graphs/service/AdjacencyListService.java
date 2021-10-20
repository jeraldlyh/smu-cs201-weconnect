package com.dsa.graphs.service;

import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.AdjacencyListDTO;
import com.dsa.graphs.models.FriendSuggestionDTO;

public interface AdjacencyListService {
    AdjacencyListDTO createAdjacencyList();
    AdjacencyList getAdjacencyList();
    FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser);
}
