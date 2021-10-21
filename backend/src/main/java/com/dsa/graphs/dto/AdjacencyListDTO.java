package com.dsa.graphs.dto;

import com.dsa.graphs.models.AdjacencyList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AdjacencyListDTO {
    private AdjacencyList adjacencyList;
    private long timeTaken;
}
