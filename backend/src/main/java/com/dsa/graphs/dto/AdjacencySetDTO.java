package com.dsa.graphs.dto;

import com.dsa.graphs.models.AdjacencySet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AdjacencySetDTO {
    private AdjacencySet adjacencySet;
    private long timeTaken;
}
