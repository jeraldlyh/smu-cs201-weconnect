package com.dsa.graphs.models;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
}
