# nisum-inteview

# REST application to register Users

### Requirements
- Java 1.8

### Technologies
- H2
- Spring Data JPA
- Spring Boot
- ModelMapper
- java-jwt
- Lombok
- JUnit

### Instalation

- Clone the project from https://github.com/gabrieloest/nisum-interview
 ````
 git clone https://github.com/gabrieloest/nisum-interview.git 
 ````
- Open the command line in the project directory
- Run the following commands:
````
./gradlew clean
````
````
./gradlew test
````
````
./gradlew bootRun
````


# REST API

The REST API to the app is described below.

## Get list of Users

### Request

`GET /users/`

    curl -i -H 'Accept: application/json' http://localhost:8080/users/

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json

    [
        {
            "userId": "ed03e1f0-9012-426a-b18e-b11e67d83fe0",
            "name": "Juan Rodriguez",
            "email": "juan@rodriguez.org",
            "password": "$2a$10$MCgWzfk0QZipgcCrLNHS0ecGgL.BqJrWn72nvwefsZdQIbnkMTV2.",
            "created": "2020-08-28T19:47:55.932",
            "modified": "2020-08-28T19:47:55.932",
            "lastLogin": "2020-08-28T19:47:55.87",
            "token": null,
            "phones": [
                {
                    "phoneId": "bef98e39-191e-46f8-bd35-4c539dd20d7d",
                    "number": "099 999 999",
                    "cityCode": "1",
                    "countryCode": "+098"
                },
                {
                    "phoneId": "dd5940cc-ea29-4179-a2b8-d87a43e265d7",
                    "number": "099 999 998",
                    "cityCode": "1",
                    "countryCode": "+098"
                }
            ],
            "active": true
        },
        {
            "userId": "ff7dd825-69f8-4216-b576-ae021d0669b1",
            "name": "Gabriel Oest",
            "email": "gabriel@oest.com",
            "password": "$2a$10$fYuM5aTyJZ2jGsYUtOy.ne3uBbILS6sm1DedD3Q1cMVAvz7ZXPZu2",
            "created": "2020-08-28T19:47:56.06",
            "modified": "2020-08-28T19:47:56.06",
            "lastLogin": "2020-08-28T19:47:56.059",
            "token": null,
            "phones": [
                {
                    "phoneId": "56dd8bb6-60da-4abb-9fde-8d9cee51b840",
                    "number": "099 999 997",
                    "cityCode": "1",
                    "countryCode": "+098"
                },
                {
                    "phoneId": "41a56d2e-0d9a-4277-a1cd-d21b1762c098",
                    "number": "099 999 996",
                    "cityCode": "1",
                    "countryCode": "+098"
                }
            ],
            "active": true
        }
    ]
    
## Get an User

### Request

`GET /users/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/users/ed03e1f0-9012-426a-b18e-b11e67d83fe0

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    {
        "userId": "ed03e1f0-9012-426a-b18e-b11e67d83fe0",
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "$2a$10$MCgWzfk0QZipgcCrLNHS0ecGgL.BqJrWn72nvwefsZdQIbnkMTV2.",
        "created": "2020-08-28T19:47:55.932",
        "modified": "2020-08-28T19:47:55.932",
        "lastLogin": "2020-08-28T19:47:55.87",
        "token": null,
        "phones": [
            {
                "phoneId": "bef98e39-191e-46f8-bd35-4c539dd20d7d",
                "number": "099 999 999",
                "cityCode": "1",
                "countryCode": "+098"
            },
            {
                "phoneId": "dd5940cc-ea29-4179-a2b8-d87a43e265d7",
                "number": "099 999 998",
                "cityCode": "1",
                "countryCode": "+098"
            }
        ],
        "active": true
    }

### Response Error

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 404 Not Found
    Connection: close
    
    {
        "message": "Could not find user 8de147c7-492f-4b6d-ba24-5245c2657c60"
    }

## Create a new User

### Request

`POST /users/`

    curl -i -H 'Accept: application/json' http://localhost:8080/users/
    
    {
        "name": "Jane Doe",
        "email": "jane@doe.com",
        "password": "Password1234",
        "phones": [
            {
                "number": "098 999 984",
                "cityCode": "2",
                "countryCode": "*+098"
            }
        ]
    }

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /users/7ee89e79-e649-4042-983c-e5d3ce79e306
    Content-Length: 36

    {
        "id": "7ee89e79-e649-4042-983c-e5d3ce79e306",
        "name": "Jane Doe",
        "email": "jane@doe.com",
        "password": "$2a$10$jRWtSjfVjQ7wDfnbXFsfmeNvwqCDcMXMCbWbdbtRrfgAI8OhXcd9q",
        "created": "2020-08-28T19:48:23.022",
        "modified": "2020-08-28T19:48:23.022",
        "lastLogin": "2020-08-28T19:48:22.901",
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYW5lQGRvZS5jb20iLCJleHAiOjE1OTk1MTg5MDN9.stM-EuHcvReIZwqfYraElIY6itjmZ_AmFRCQul-wxPnbo6ZqaIgATj4UaqIlZELHMwAK26EtaZtt0GGZHjH0Dg",
        "phones": [
            {
                "number": "098 999 984",
                "cityCode": "2",
                "countryCode": "*+098"
            }
        ],
        "active": false
    }
    
### Response Error

    HTTP/1.1 400 Bad Request
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 400 Bad Request
    Connection: close
    
    {
        "message": "The email must have the following format 'aaaaaaa@domain.cl'"
    }
    
## Update an existing User

### Request

`PUT /users/{id}`

    curl -i -H 'Accept: application/json' http://localhost:8080/users/8de147c7-492f-4b6d-ba24-5245c2657c60
    
    {
        "name": "John Doe",
        "email": "john@doe.com",
        "password": "1234Password",
        "phones": [
           {
                "number": "099 546 545",
                "cityCode": "2",
                "countryCode": "+098"
            }
        ]
    }

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Location: /users/8de147c7-492f-4b6d-ba24-5245c2657c60

    {
        "id": "8de147c7-492f-4b6d-ba24-5245c2657c60",
        "name": "John Doe",
        "email": "john@doe.com",
        "password": "$2a$10$/0kEA7n73srSw9hPuBCjIO.98hDlggS6Vtm2v2a6plK8EBHP/nfQ2",
        "created": "2020-08-28T19:48:18.229",
        "modified": "2020-08-28T19:48:55.846",
        "lastLogin": "2020-08-28T19:48:17.777",
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGRvZS5jb20iLCJleHAiOjE1OTk1MTg5MzV9.ouD1SuPIPlmND5hLj_UAPggXUthzYKOkog-uBrAfdmc2PRRR71Ol4NDTpsK9c6hMKWa_zQ1jfUAp5An2cejryw",
        "phones": [
            {
                "number": "099 546 545",
                "cityCode": "2",
                "countryCode": "+098"
            }
        ],
        "active": false
    }
    
## Delete an User

### Request

`DELETE /users/{id}`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:8080/users/8de147c7-492f-4b6d-ba24-5245c2657c60

### Response

    HTTP/1.1 204 No Content
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 204 No Content
    Connection: close

    
## Database Script
````
CREATE DATABASE nisumdb;

DROP TABLE IF EXISTS USER;
  
CREATE TABLE USER (
  USER_ID BINARY PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  IS_ACTIVE BOOLEAN NOT NULL,
  CREATED TIMESTAMP NOT NULL,
  MODIFIED TIMESTAMP NOT NULL,
  LAST_LOGIN TIMESTAMP NOT NULL,
  TOKEN VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS PHONE;
  
CREATE TABLE PHONE (
  PHONE_ID BINARY PRIMARY KEY,
  NUMBER VARCHAR(255) NOT NULL,
  CITY_CODE VARCHAR(255),
  COUNTRY_CODE VARCHAR(255) NOT NULL,
);

DROP TABLE IF EXISTS USER_PHONES;
  
CREATE TABLE USER_PHONES (
  USER_ID BINARY PRIMARY KEY,
  PHONE_ID BINARY PRIMARY KEY
);
````