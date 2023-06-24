-- Drop schema if exists and create new schema
DROP SCHEMA IF EXISTS registry CASCADE;
CREATE SCHEMA registry;

-- Create portal_users table
DROP TABLE IF EXISTS registry.portal_users CASCADE;
CREATE TABLE registry.portal_users (
    userid VARCHAR NOT NULL,
    username VARCHAR,
    password VARCHAR,
    email VARCHAR,
    crt_ts TIMESTAMP,
    PRIMARY KEY (userid)
);

-- Create employee_details table
DROP TABLE IF EXISTS registry.employee_details CASCADE;
CREATE TABLE registry.employee_details (
    employee_id VARCHAR NOT NULL,
    employee_first_name VARCHAR NOT NULL,
    employee_last_name VARCHAR,
    employee_profile_url VARCHAR,
    employee_email VARCHAR,
    employee_phone VARCHAR,
    crt_ts TIMESTAMP,
    crt_by VARCHAR,
    mod_ts TIMESTAMP,
    mod_by VARCHAR,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (crt_by) REFERENCES registry.portal_users (userid),
    FOREIGN KEY (mod_by) REFERENCES registry.portal_users (userid)
);
