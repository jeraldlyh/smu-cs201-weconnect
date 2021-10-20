package com.dsa.graphs.models;

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
