package net.odtel.tv.controller;

import net.odtel.tv.model.UserAuth;
import net.odtel.tv.service.TokenResponse;
import net.odtel.tv.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataServiceController {
    
    @Autowired
    private TvService tvService;
    
    @GetMapping(value = "/all", produces = "text/plain;charset=UTF-8")
    public String getAll() {
        return tvService.getAllTVProgrammes();
    }
    
    @PostMapping(path = "/auth")
    public ResponseEntity<TokenResponse> createAuth(@RequestBody UserAuth userAuth) {
        return new ResponseEntity<>(tvService.getToken(userAuth), HttpStatus.CREATED);
    }
}
