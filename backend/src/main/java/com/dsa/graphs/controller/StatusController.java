package com.dsa.graphs.controller;

import com.dsa.graphs.dto.StatusDTO;
import com.dsa.graphs.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "https://cs201-we-connect.vercel.app/" })
@RestController
public class StatusController {
    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status")
    public StatusDTO getStatus() {
        return statusService.getStatus();
    }
}
