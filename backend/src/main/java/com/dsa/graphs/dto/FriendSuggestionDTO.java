package com.dsa.graphs.dto;

import java.util.List;

import com.dsa.graphs.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FriendSuggestionDTO {
    private List<User> friendSuggestions;
    private int degreeOfRelationship;
    private long timeTaken;

    public FriendSuggestionDTO(List<User> friendSuggestions, int degreeOfRelationship) {
        this.friendSuggestions = friendSuggestions;
        this.degreeOfRelationship = degreeOfRelationship;
    }
}
