package com.miguel.demo.controller;

import com.miguel.demo.exceptions.ActionNotPossibleException;
import com.miguel.demo.exceptions.ScooterNotFoundException;
import com.miguel.demo.model.CreateScooterRequest;
import com.miguel.demo.model.Scooter;
import com.miguel.demo.model.Status;
import com.miguel.demo.model.UpdateScooterRequest;
import com.miguel.demo.service.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("api/v1/")
public class ScooterController {

    @Autowired
    private ScooterService scooterService;

    @RequestMapping(value = "scooters", method = POST)
    public String createScooter(@RequestBody CreateScooterRequest createScooterRequest) {
        scooterService.createScooter(createScooterRequest.getVehicleId(), createScooterRequest.getUser());
        return createScooterRequest.getVehicleId();
    }

    @RequestMapping(value = "scooters", method = GET)
    public List<Scooter> getScooters() {
        return scooterService.getScooters();
    }

    @RequestMapping(value = "scooters", method = PUT)
    public String changeScooter(@RequestBody UpdateScooterRequest updateScooterRequest) {
        try {
            scooterService.updateScooter(updateScooterRequest.getAction(),
                    updateScooterRequest.getVehicleId(),
                    updateScooterRequest.getUser(),
                    updateScooterRequest.getComment());
        } catch (ActionNotPossibleException actionNotPossibleException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Action could not be executed on scooter");
        } catch (ScooterNotFoundException scooterNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Scooter not found");
        }

        return updateScooterRequest.getVehicleId();
    }
}
