package com.travel.packages.test;

import com.travel.activities.Activity;
import com.travel.activities.ConcreteActivity;
import com.travel.packages.ConcreteTravelPackage;
import com.travel.passengers.Passenger;
import com.travel.passengers.StandardPassenger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConcreteTravelPackageTest {

    private ConcreteTravelPackage travelPackage;

    @Before
    public void setUp() {
        // Initialize a ConcreteTravelPackage instance for testing
        travelPackage = new ConcreteTravelPackage("TestPackage", 10);
    }

    @Test
    public void testEnrollPassengerSuccess() {
        Passenger john = new StandardPassenger("John", 1, 500.0);
        ConcreteActivity hiking = new ConcreteActivity("Hiking", "Explore scenic trails", 50.0, 3);

        travelPackage.addActivity(hiking, "Mountain Region");

        travelPackage.enrollPassenger(john, hiking);

        // Ensure John is enrolled in Hiking
        assertTrue(hiking.isEnrolled(john));
        // Ensure the package has John in the passenger list
        assertTrue(travelPackage.getPassengers().contains(john));
        // Ensure the available spaces for Hiking are updated
        assertEquals(2, travelPackage.getAvailableSpacesForActivity("Hiking"));
    }
    
//    @Test
//    public void testEnrollPassengerPackageFull() {
//        // Set up a package with a capacity of 1 passenger
//        travelPackage = new ConcreteTravelPackage("TestPackage", 1);
//
//        Passenger john = new StandardPassenger("John", 1, 500.0);
//        ConcreteActivity hiking = new ConcreteActivity("Hiking", "Explore scenic trails", 50.0, 3);
//
//        travelPackage.addActivity(hiking, "Mountain Region");
//
//        travelPackage.enrollPassenger(john, hiking);
//
//        // Try to enroll another passenger when the package is full
//        Passenger alice = new StandardPassenger("Alice", 2, 700.0);
//        travelPackage.enrollPassenger(alice, hiking);
//
//        // Ensure Alice is not enrolled, and the package is still full
//        assertFalse(hiking.isEnrolled(alice));
//        assertEquals(0, travelPackage.getAvailableSpacesForActivity("Hiking"));
//    }
    
//    @Test
//    public void testEnrollPassengerActivityFull() {
//        Passenger john = new StandardPassenger("John", 1, 500.0);
//        ConcreteActivity hiking = new ConcreteActivity("Hiking", "Explore scenic trails", 50.0, 1);
//
//        travelPackage.addActivity(hiking, "Mountain Region");
//
//        travelPackage.enrollPassenger(john, hiking);
//
//        // Try to enroll another passenger when the activity is full
//        Passenger alice = new StandardPassenger("Alice", 2, 700.0);
//        travelPackage.enrollPassenger(alice, hiking);
//
//        // Ensure Alice is not enrolled, and the activity is still full
//        assertFalse(hiking.isEnrolled(alice));
//        assertEquals(0, travelPackage.getAvailableSpacesForActivity("Hiking"));
//    }
}
