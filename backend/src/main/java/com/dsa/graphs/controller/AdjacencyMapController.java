package com.dsa.graphs.controller;

import com.dsa.graphs.dto.AddFriendDTO;
import com.dsa.graphs.dto.AdjacencyMapDTO;
import com.dsa.graphs.dto.FriendSuggestionDTO;
import com.dsa.graphs.service.AdjacencyMapService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "https://cs201-we-connect.vercel.app/" })
@RestController
public class AdjacencyMapController {
    private AdjacencyMapService adjacencyMapService;

    public AdjacencyMapController(AdjacencyMapService adjacencyMapService) {
        this.adjacencyMapService = adjacencyMapService;
    }

    // Unsure why it returns size of array if AdjacencyMap type is returned as a response
    @GetMapping("/adjacency-map")
    public AdjacencyMapDTO getAdjacencyMap() {
        return adjacencyMapService.createAdjacencyMap();
    }

    @PostMapping("/adjacency-map")
    public FriendSuggestionDTO addFriend(@RequestBody AddFriendDTO addFriendDTO) {
        return adjacencyMapService.getFriendSuggestionsByBfs(addFriendDTO.getFromUser(), addFriendDTO.getToUser());
    }

    @DeleteMapping("/adjacency-map")
    public void deleteAdjacencyMap() {
        adjacencyMapService.deleteAdjacencyMap();
    }
}
