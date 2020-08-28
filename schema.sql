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