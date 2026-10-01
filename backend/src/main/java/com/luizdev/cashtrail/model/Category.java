package com.luizdev.cashtrail.model;

public class Category {
    private Long id; // Long (wrapper) so a new category can have a null id before being saved
    private String name;

    public Category() {} // Empty constructor (Jackson) that converts JSON into objects.
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
