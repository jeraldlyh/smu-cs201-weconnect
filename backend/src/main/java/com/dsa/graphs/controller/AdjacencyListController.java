package com.dsa.graphs.controller;

import java.util.List;
import java.util.Map;

import com.dsa.graphs.models.AdjacencyListDTO;
import com.dsa.graphs.service.AdjacencyListService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdjacencyListController {
    private AdjacencyListService adjacencyListService;

    public AdjacencyListController(AdjacencyListService adjacencyListService) {
        this.adjacencyListService = adjacencyListService;
    }

    @GetMapping("/test")
    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyListService.getAdjacencyList();
    }

    @GetMapping("/adjacency-list/create")
    public AdjacencyListDTO createAdjacencyList() {
        return adjacencyListService.createAdjacencyList();
    }

}
