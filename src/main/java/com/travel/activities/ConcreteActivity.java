package com.travel.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.travel.passengers.Passenger;

//Concrete implementation of Activity
public class ConcreteActivity implements Activity {
	private String name;
	private String description;
	private double cost;
	private int capacity;
	private String destination;
	private List<Passenger> enrolledPassengers = new ArrayList<>();

	public ConcreteActivity(String name, String description, double cost, int capacity) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.capacity = capacity;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public double getCost() {
		return cost;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public String getDestination() {
		return destination; // Assuming 'destination' is a field in your ConcreteActivity class
	}

	@Override
	public boolean isEnrolled(Passenger passenger) {
		return enrolledPassengers.contains(passenger);
	}

	@Override
	public void enroll(Passenger passenger) {
		if (!isEnrolled(passenger)) {
			enrolledPassengers.add(passenger);
		}
	}

	@Override
	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public void updateCapacity(int delta) {
		// Assuming you want to increment/decrement the capacity by a certain amount
		// (delta)
		this.capacity += delta;
	}
	
//	@Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        ConcreteActivity activity = (ConcreteActivity) obj;
//        return Objects.equals(name, activity.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
   

}
