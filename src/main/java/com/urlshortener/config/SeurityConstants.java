package com.urlshortener.config;

public class SeurityConstants {

    // URLs
    public static final String ACCOUNT_URL = "/account";
    public static final String CONSOLE_URL = "/console";
    public static final String CONSOLE_DASH_URL = "/console/**";
    public static final String REGISTER_URL = "/register";
    public static final String STATISTIC_ACCOUNTID_URL = "/statistic/**";

    // ROLES
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";

    // inMemoryAuthentication
    public static final String FIRST_USER = "marin";
    public static final String FIRST_USER_PASSWORD = "$2a$04$ZfcBPMInqCj5yHiFKS8PGuUpB./3lzvm3fraAbVZ3eHDRYwl7nGnG";
    public static final String SECOND_USER = "admin";
    public static final String SECOND_USER_PASSWORD = "$2a$04$d/2S8acBEEB06rhShMU6nuetrygLbuzTO9CZ/M1n5GvmiZgKA8Yd6";

}
