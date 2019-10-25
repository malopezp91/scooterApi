package com.miguel.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "SCOOTER")
public class Scooter {
    @Id
    private String vehicleId;
    private Status status;
    @OneToMany(mappedBy="scooter")
    private List<Event> eventList;
    public Scooter(){}

    public Scooter(String vehicleId, Status status) {
        this.vehicleId = vehicleId;
        this.status = status;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
