package com.dsa.graphs.dto;

import lombok.Getter;

@Getter
public class AddFriendDTO {
    String fromUser;
    String toUser;

    @Override
    public String toString() {
        return String.format("FROM: %s | TO: %s", fromUser, toUser);
    }
}
