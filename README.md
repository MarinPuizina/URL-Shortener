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
I've decided to implement basic Spring Security with some minor configuration e.g. added in memory users, and added logic for specific URL authorization. I've done it that way mostly, because I haven't been working with customizing Spring Security which can be difficult task for someone that has never done it before. (Basically, at this time I have only one in memory user which can be used in this application)  
