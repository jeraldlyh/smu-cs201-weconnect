package com.dsa.graphs.controller;

import com.dsa.graphs.dto.AddFriendDTO;
import com.dsa.graphs.dto.AdjacencySetDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.service.AdjacencySetService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "https://cs201-we-connect.vercel.app/" })
@RestController
public class AdjacencySetController {
    private AdjacencySetService adjacencySetService;

    public AdjacencySetController(AdjacencySetService adjacencySetService) {
        this.adjacencySetService = adjacencySetService;
    }

    @GetMapping("/adjacency-set")
    public AdjacencySetDTO getAdjacencySet() {
        return adjacencySetService.createAdjacencySet();
    }

    @PostMapping("/adjacency-set")
    public FriendSuggestionDTO addFriend(@RequestBody AddFriendDTO addFriendDTO) {
        return adjacencySetService.getFriendSuggestionsByBfs(addFriendDTO.getFromUser(), addFriendDTO.getToUser());
    }

    @DeleteMapping("/adjacency-set")
    public void deleteAdjacencySet() {
        adjacencySetService.deleteAdjacencySet();
    }
}
