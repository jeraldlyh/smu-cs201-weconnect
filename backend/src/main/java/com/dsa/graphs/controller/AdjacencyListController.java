package com.dsa.graphs.controller;

import com.dsa.graphs.dto.AddFriendDTO;
import com.dsa.graphs.dto.AdjacencyListDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.service.AdjacencyListService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdjacencyListController {
    private AdjacencyListService adjacencyListService;

    public AdjacencyListController(AdjacencyListService adjacencyListService) {
        this.adjacencyListService = adjacencyListService;
    }

    // Unsure why it returns size of array if AdjacencyList type is returned as a response
    @GetMapping("/adjacency-list")
    public String getAdjacencyList() {
        return adjacencyListService.getAdjacencyList().toString();
    }

    @PostMapping("/adjacency-list")
    public FriendSuggestionDTO addFriend(@RequestBody AddFriendDTO addFriendDTO) {
        return adjacencyListService.getFriendSuggestionsByBfs(addFriendDTO.getFromUser(), addFriendDTO.getToUser());
    }
}
