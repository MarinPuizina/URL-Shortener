package com.urlshortener.domain.response;

import org.springframework.stereotype.Component;

/**
 * Used for mapping JSON from AccountController POST request's Response
 *
 * @author  Marin Puizina
 */
@Component
public class AccountResponse {

    private String password;
    private String description;
    private Boolean success;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    @Override
    public String toString() {
        return "AccountResponse{" +
                "password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", success=" + success +
                '}';
    }

}
