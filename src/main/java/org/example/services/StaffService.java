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

    /**
     * Adds a new staff member if the user has the appropriate role.
     *
     * @param staff          The staff member to add.
     * @param currentUserRole The role of the user performing the action.
     */
    public void addStaff(Staff staff, Role currentUserRole) {
        if (currentUserRole == Role.MANAGER) {
            staffManager.addStaff(staff, currentUserRole);
        } else {
            System.out.println("Insufficient permissions. Only managers can add staff.");
        }
    }

    /**
     * Deletes a staff member if the user has the appropriate role.
     *
     * @param staffID        The ID of the staff member to delete.
     * @param currentUserRole The role of the user performing the action.
     */
    public void deleteStaff(String staffID, Role currentUserRole) {
        if (currentUserRole == Role.MANAGER) {
            if (staffID != null && !staffID.isEmpty()) {
                staffManager.deleteStaff(staffID, currentUserRole);
            } else {
                System.out.println("Invalid staff ID. Deletion failed.");
            }
        } else {
            System.out.println("Insufficient permissions. Only managers can delete staff.");
        }
    }

    /**
     * Edits a staff member's details if the user has the appropriate role.
     *
     * @param staffID        The ID of the staff member to edit.
     * @param newName        The new name of the staff member.
     * @param newHoursWorked The new hours worked by the staff member.
     * @param currentUserRole The role of the user performing the action.
     */
    public void editStaff(String staffID, String newName, int newHoursWorked, Role currentUserRole) {
        if (currentUserRole == Role.MANAGER) {
            if (staffID != null && !staffID.isEmpty() && newHoursWorked >= 0) {
                staffManager.editStaff(staffID, newName, newHoursWorked, currentUserRole);
            } else {
                System.out.println("Invalid input. Editing failed.");
            }
        } else {
            System.out.println("Insufficient permissions. Only managers can edit staff.");
        }
    }

    /**
     * Retrieves the list of all staff members.
     *
     * @return A list of all staff members.
     */
    public List<Staff> getStaffList() {
        return staffManager.getStaffList();
    }
}