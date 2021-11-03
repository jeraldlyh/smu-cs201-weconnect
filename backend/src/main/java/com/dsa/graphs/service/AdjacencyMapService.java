package com.dsa.graphs.service;

import com.dsa.graphs.dto.AdjacencyMapDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencyMap;

public interface AdjacencyMapService {
    AdjacencyMapDTO createAdjacencyMap();
    AdjacencyMap getAdjacencyMap(boolean create);
    void deleteAdjacencyMap();
    FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser);
}
