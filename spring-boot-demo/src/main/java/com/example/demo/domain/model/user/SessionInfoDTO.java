package com.example.demo.domain.model.user;

public class SessionInfoDTO {
    private String email;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public SessionInfoDTO(String email) {
        super();
        this.email = email;
    }
}
