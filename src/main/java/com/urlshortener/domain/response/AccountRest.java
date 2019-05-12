package com.urlshortener.domain.response;

public class AccountRest {

    private Boolean success;
    private String description;
    private String password;


    public AccountRest() {
    }

    public AccountRest(Boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public AccountRest(Boolean success, String description, String password) {
        this.success = success;
        this.description = description;
        this.password = password;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "AccountRest{" +
                "success=" + success +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
