package com.arti.inventory.mission.backend.model;

/**
 * Represents the category of an employee.
 */
public enum EmployeeCategory {
    
    /**
     * Represents category A.
     * Value: "PR-CR"
     */
    A("PR-CR"),

    /**
     * Represents category B.
     * Value: "DG"
     */
    B("DG"),

    /**
     * Represents category C.
     * Value: "MEMBRE-CR"
     */
    C("MEMBRE-CR"),

    /**
     * Represents category D.
     * Value: "DIRECTEUR"
     */
    D("DIRECTEUR"),

    /**
     * Represents category E.
     * Value: "SOUS-DIRECTEUR"
     */
    E("SOUS-DIRECTEUR"),

    /**
     * Represents category F.
     * Value: "CHEF DE SERVICE"
     */
    F("CHEF DE SERVICE"),

    /**
     * Represents category G.
     * Value: "CADRE"
     */
    G("CADRE"),

    /**
     * Represents category H.
     * Value: "EMPLOYEE"
     */
    H("EMPLOYEE");

    private final String customValue;

    /**
     * Constructs a new EmployeeCategory with the specified custom value.
     * 
     * @param customValue the custom value associated with the category
     */
    EmployeeCategory(String customValue) {
        this.customValue = customValue;
    }

    /**
     * Returns the custom value associated with the category.
     * 
     * @return the custom value
     */
    @Override
    public String toString() {
        return customValue;
    }
}
