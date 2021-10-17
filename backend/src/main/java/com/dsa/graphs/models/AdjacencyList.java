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
    private Map<User, List<User>> adjacencyList;

    public AdjacencyList() {
        this.adjacencyList = new HashMap<>();
    }

    // Add bidirectional edge
    public void addEdge(User fromUser, User toUser) {
        adjacencyList.get(fromUser).add(toUser);
        adjacencyList.get(toUser).add(fromUser);
    }

    public void addNode(User user) {
        adjacencyList.put(user, new ArrayList<User>());
    }
}
