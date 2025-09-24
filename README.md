Define application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/database_name
spring.datasource.username='name'
spring.datasource.password='password'
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
