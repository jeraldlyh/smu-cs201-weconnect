package com.dsa.graphs.controller;

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
    public String getAdjacencyList() {
        adjacencyListService.createAdjacencyList();
        return "";
    }

}
