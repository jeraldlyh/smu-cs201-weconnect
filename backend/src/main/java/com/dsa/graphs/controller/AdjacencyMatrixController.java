package com.dsa.graphs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdjacencyMatrixController {
    

    @GetMapping("/adjacency-matrix")
    public String getAdjacencyMatrix() {
        return "";
    }
}
