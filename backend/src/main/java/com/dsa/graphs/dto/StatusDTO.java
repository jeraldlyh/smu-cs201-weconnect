package com.dsa.graphs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatusDTO {
    private boolean adjacencyMapStatus;
    private boolean adjacencyMatrixStatus;
    private boolean adjacencySetStatus;
}
