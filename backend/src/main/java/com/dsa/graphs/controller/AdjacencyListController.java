package com.dsa.graphs.controller;

import com.dsa.graphs.models.AddFriendDTO;
import com.dsa.graphs.models.AdjacencyListDTO;
import com.dsa.graphs.models.User;
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
    @GetMapping("/test")
    public String getAdjacencyList() {
        return adjacencyListService.getAdjacencyList().toString();
    }

    @GetMapping("/adjacency-list/create")
    public AdjacencyListDTO createAdjacencyList() {
        return adjacencyListService.createAdjacencyList();
    }

    @PostMapping("/adjacency-list")
    public User addFriend(@RequestBody AddFriendDTO addFriendDTO) {
        return adjacencyListService.getUserByBfs(addFriendDTO.getFromUser(), addFriendDTO.getToUser());
    }

}
