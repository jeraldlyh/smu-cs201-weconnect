package com.dsa.graphs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.dsa.graphs.models.AdjacencyList;
import com.dsa.graphs.models.User;
import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class AdjacencyListServiceImpl implements AdjacencyListService {
    private AdjacencyList adjacencyList = new AdjacencyList();
    private Gson gson;
    private static final Logger LOGGER = LogManager.getLogger(AdjacencyListServiceImpl.class);

    public AdjacencyListServiceImpl(AdjacencyList adjacencyList, Gson gson) {
        this.adjacencyList = adjacencyList;
        this.gson = gson;
    }

    @Override
    public void createAdjacencyList() {
        try {
            File resource = new ClassPathResource("data.json").getFile();
            BufferedReader reader = new BufferedReader(new FileReader(resource));
            User[] data = gson.fromJson(reader, User[].class);
            
        } catch (IOException e) {
            LOGGER.warn("------ File not found");
        } catch (Exception e) {
            LOGGER.warn("------ Unexpected error: " + e.getMessage());
        }
    }
}
