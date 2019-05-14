# URL-Shortener
URL Shortener project

This is Java Spring Boot project. It uses ofc, Spring Boot, H2 Database and Spring Security.


It has 4 main functionalities: 

    1) Storing account if there isn't one with provided accountId and generating password.
    2) Register URL pages and generate custom shor URL-s. Further, to store them in H2 database.
    3) Redirecing from provided short URL to mapped original URL which are fetched from databse.
    4) Providing statistic report in JSON format for specific URL and user. Further, providing the number of times user has redirected to them.
    
    
For testing purposes refer to http://localhost:8080/help  
H2 database is available via http://localhost:8080/console (no password needed).
