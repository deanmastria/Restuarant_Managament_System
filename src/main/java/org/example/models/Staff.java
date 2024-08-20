package org.example.models;

import java.util.Objects;

public class Staff {
    private final String staffID;
    private String name;
    private final Role role;
    private int hoursWorked;

    // Constructor with validation
    public Staff(String staffID, String name, Role role, int hoursWorked) {
        if (staffID == null || staffID.isEmpty()) {
            throw new IllegalArgumentException("Staff ID must not be null or empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role must not be null.");
        }
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked must be non-negative.");
        }
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.hoursWorked = hoursWorked;
    }

    // Getters
    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    // Setters
    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked must be non-negative.");
        }
        this.hoursWorked = hoursWorked;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }
        this.name = name;
    }

    // Override toString for better logging and debugging
    @Override
    public String toString() {
        return "Staff{" +
                "staffID='" + staffID + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", hoursWorked=" + hoursWorked +
                '}';
    }

    // Override equals and hashCode for proper comparison in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staffID.equals(staff.staffID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffID);
    }
}

