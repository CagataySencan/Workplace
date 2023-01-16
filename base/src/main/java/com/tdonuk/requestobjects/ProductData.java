package com.tdonuk.requestobjects;

public class ProductData {
    private Long serverId;
    private String name;
    private String description;
    private Long oturbir;

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOturbir() {
        return oturbir;
    }

    public void setOturbir(Long oturbir) {
        this.oturbir = oturbir;
    }
}
