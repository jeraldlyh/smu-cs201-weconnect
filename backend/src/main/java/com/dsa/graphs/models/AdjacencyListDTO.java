package com.dsa.graphs.models;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AdjacencyListDTO {
    private Map<String, List<String>> adjacencyList;
    private long timeTaken;
}
