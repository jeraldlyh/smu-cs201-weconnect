package com.dsa.graphs.controller;

import com.dsa.graphs.dto.AddFriendDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.service.AdjacencyMatrixService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdjacencyMatrixController {
    AdjacencyMatrixService adjacencyMatrixService;

    @Autowired
    public AdjacencyMatrixController(AdjacencyMatrixService adjacencyMatrixService) {
        this.adjacencyMatrixService = adjacencyMatrixService;
    }

    @GetMapping("/adjacency-matrix")
    public String getAdjacencyMatrix() {
        adjacencyMatrixService.getAdjacencyMatrix();
        return "";
    }

    @PostMapping("/adjacency-matrix")
    public FriendSuggestionDTO addFriend(@RequestBody AddFriendDTO addFriendDTO) {
        return adjacencyMatrixService.getFriendSuggestionsByBfs(addFriendDTO.getFromUser(), addFriendDTO.getToUser());
    }
}
