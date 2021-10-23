package com.dsa.graphs.controller;

import java.util.List;

import com.dsa.graphs.models.User;
import com.dsa.graphs.service.NodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "https://cs201-we-connect.vercel.app/" })
@RestController
public class NodeController {
    private NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/random")
    public List<User> getRandomUsers() {
        return nodeService.getRandomNodes();
    }
}
