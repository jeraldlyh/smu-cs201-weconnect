package com.dsa.graphs.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FriendSuggestionDTO {
    private List<User> friendSuggestions;
    private int degreeOfRelationship;
}
