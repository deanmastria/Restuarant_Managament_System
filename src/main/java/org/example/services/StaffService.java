package org.example.services;

import org.example.models.Role;
import org.example.models.Staff;
import org.example.StaffManager;

import java.util.List;

public class StaffService {
    private final StaffManager staffManager;

    public StaffService() {
        staffManager = new StaffManager();
    }

    public void addStaff(Staff staff, Role role) {
        staffManager.addStaff(staff, role);
    }

    public void deleteStaff(String staffID, Role role) {
        staffManager.deleteStaff(staffID, role);
    }

    public void editStaff(String staffID, String newName, int newHoursWorked, Role role) {
        staffManager.editStaff(staffID, newName, newHoursWorked, role);
    }

    public List<Staff> getStaffList() {
        return staffManager.getStaffList();
    }
}