package com.dsa.graphs.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AdjacencyList {
    private Map<String, List<String>> adjacencyList;

    public AdjacencyList() {
        this.adjacencyList = new HashMap<>();
    }

    // Add bidirectional edge
    public void addEdge(String fromUser, String toUser) {
        adjacencyList.get(fromUser).add(toUser);
        adjacencyList.get(toUser).add(fromUser);
    }

    public void addNode(String user) {
        adjacencyList.put(user, new ArrayList<String>());
    }

    public int getSize() {
        return adjacencyList.size();
    }
}
