package com.example.demo.domain.model.health;

public class HealthDTO {
    private String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public HealthDTO(String status) {
        super();
        this.status = status;
    }
}
