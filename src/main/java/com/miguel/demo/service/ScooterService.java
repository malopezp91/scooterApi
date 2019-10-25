package com.miguel.demo.service;

import com.miguel.demo.exceptions.ActionNotPossibleException;
import com.miguel.demo.exceptions.ScooterNotFoundException;
import com.miguel.demo.model.Action;
import com.miguel.demo.model.Event;
import com.miguel.demo.model.Scooter;
import com.miguel.demo.model.Status;
import com.miguel.demo.repository.ScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ScooterService {
    @Autowired
    private ScooterRepository scooterRepository;

    @Autowired
    private GrinClient grinClient;

    public Scooter createScooter(String vehicleId, String user) {
        Scooter newScooter = new Scooter(vehicleId, Status.CREATED);
        List<Event> events = new ArrayList<>();
        Event newEvent = new Event(Action.CREATION, user, "");
        newEvent.setScooter(newScooter);
        events.add(newEvent);
        newScooter.setEventList(events);
        System.out.println(newScooter.getEventList().get(0).getId());
        return scooterRepository.save(newScooter);
    }

    public List<Scooter> getScooters() {
        return StreamSupport.stream(scooterRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Scooter updateScooter(Action action, String vehicleId, String user, String comment) {
        Optional<Scooter> scooter = scooterRepository.findById(vehicleId);
        if (!scooter.isPresent()) {
            throw new ScooterNotFoundException("Scooter was not found");
        }
        Status newStatus = getNewStatus(scooter.get(), action);
        if (newStatus.equals(Status.UNDEFINED_STATUS)) {
            throw new ActionNotPossibleException("Given action is not possible");
        }
        Scooter updatedScooter = doActionOnScooter(scooter.get(), action, newStatus, user, comment);
        notifyManager()
        scooterRepository.save(updatedScooter);
        return updatedScooter;
    }

    private Status getNewStatus(Scooter currentScooter, Action action) {
        switch (currentScooter.getStatus()) {
            case CREATED:
                return action.equals(Action.SETUP) ? Status.MAINTENANCE : Status.UNDEFINED_STATUS;
            case MAINTENANCE:
                return action.equals(Action.MOVING) ? Status.DISTRIBUTION : Status.UNDEFINED_STATUS;
            case DISTRIBUTION:
                return action.equals(Action.PLACED) ? Status.ON_STREET : Status.UNDEFINED_STATUS;
            case ON_STREET:
                if (action.equals(Action.MOVING)) {
                    return Status.DISTRIBUTION;
                }
                if (action.equals(Action.NEEDS_REPAIR)) {
                    return Status.MAINTENANCE;
                }
            default:
                return Status.UNDEFINED_STATUS;
        }
    }

    private Scooter doActionOnScooter(Scooter scooter, Action action, Status newStatus, String user, String comment) {
        scooter.setStatus(newStatus);
        scooter.getEventList().add(new Event(action, user, comment));
        return scooter;
    }
}
