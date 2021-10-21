package com.dsa.graphs.models;

import java.util.Objects;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String user_id;
    private String name;
    private int review_count;
    private String yelping_since;
    private String useful;
    private int funny;
    private int cool;
    private String friends;
    private int fans;
    private float average_stars;

    public User(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

    // Compare users based on their user ID
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User)o;
        return user.getUser_id().equals(this.user_id);
    }

    @Override
    public String toString() {
        return name;
    }
}
