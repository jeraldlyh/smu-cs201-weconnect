package com.dsa.graphs.dto;

import java.util.List;

import com.dsa.graphs.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FriendSuggestionDTO {
    private List<User> friendSuggestions;
    private int degreeOfRelationship;
}
