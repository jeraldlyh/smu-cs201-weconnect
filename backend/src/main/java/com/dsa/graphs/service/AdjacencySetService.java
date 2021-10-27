package com.dsa.graphs.service;

import com.dsa.graphs.dto.AdjacencySetDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.models.AdjacencySet;

public interface AdjacencySetService {
    AdjacencySetDTO createAdjacencySet();
    AdjacencySet getAdjacencySet(boolean create);
    void deleteAdjacencySet();
    FriendSuggestionDTO getFriendSuggestionsByBfs(String fromUser, String toUser);
}
