package net.odtel.tv.controller;


import net.odtel.tv.service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataServiceController {

    @Autowired
    private TvService tvService;

    @GetMapping(value = "/all", produces = "text/plain;charset=UTF-8")
    public String getAll() {
        return tvService.getAllTVProgrammes();
    }

}
