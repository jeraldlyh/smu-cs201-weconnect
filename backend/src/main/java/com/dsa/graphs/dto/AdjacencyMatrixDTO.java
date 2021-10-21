package com.dsa.graphs.dto;

import com.dsa.graphs.models.AdjacencyMatrix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AdjacencyMatrixDTO {
    private AdjacencyMatrix adjacencyMatrix;
    private long timeTaken;
}
