package com.dsa.graphs.service;

import java.util.HashSet;
import java.util.Set;

import com.dsa.graphs.models.User;

import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {
    private Set<User> nodes = new HashSet<>();

}
