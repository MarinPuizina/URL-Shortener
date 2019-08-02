# URL-Shortener
URL Shortener project

This is Java Spring Boot Maven project. It uses ofc, Spring Boot, H2 Database and Spring Security.


It has 4 main functionalities: 

    1) Storing account if there isn't one with provided accountId and generating password.
    2) Register URL pages and generate custom shor URL-s. Further, to store them in H2 database.
    3) Redirecing from provided short URL to mapped original URL which are fetched from databse.
    4) Providing statistic report in JSON format for specific URL and user. Further, providing the number of times user has redirected to them.
    
    
For testing purposes refer to http://localhost:8080/help  
H2 database is available via http://localhost:8080/console (no password needed).


Furthermore, I've decided to use H2 databse because it's embedded. I've specified in application.properties file that I was to use in memory database. Therefore, this application will work out of the box.  
It has JPA for persisting data. I've made custom @Entity classes. Therefore, Spring Boot will automatically pick them up and create tables in our database.  
JPA also provided ability to use already provided CRUD operations. With ability to customize query manipulating method name without even writing sql query. Ofc, it also provides ability to write custom queries.  
I've decided to implement basic Spring Security with some minor configuration e.g. added in memory users, and added logic for specific URL authorization.
  
I've provided methods for password encryption using BCrypt or simply to encode it using Base64. They are stored in encrypted/encoded format in H2 database. If I add custom security configuration which allows authorization of users based on stored password. I will remove in memory users, then appllication will be able to use users created via REST API.
