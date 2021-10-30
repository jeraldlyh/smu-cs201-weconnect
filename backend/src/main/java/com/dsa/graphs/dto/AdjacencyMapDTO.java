package com.dsa.graphs.dto;

import com.dsa.graphs.models.AdjacencyMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AdjacencyMapDTO {
    private AdjacencyMap adjacencyMap;
    private long timeTaken;
}
