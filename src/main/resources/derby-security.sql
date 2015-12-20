CREATE TABLE AUTHORITIES
(
    USERNAME VARCHAR(45) PRIMARY KEY NOT NULL,
    AUTHORITY VARCHAR(45)
);


INSERT INTO AUTHORITIES VALUES ('maria','ROLE_ADMIN');
INSERT INTO AUTHORITIES VALUES ('tomek','ROLE_USER');

CREATE TABLE USERS
(
    USERNAME VARCHAR(50) PRIMARY KEY NOT NULL,
    PASSWORD VARCHAR(500) NOT NULL,
    ENABLED SMALLINT NOT NULL
);

INSERT INTO USERS VALUES ('maria','202cb962ac59075b964b07152d234b70',true);
INSERT INTO USERS VALUES ('tomek','202cb962ac59075b964b07152d234b70',true);

CREATE TABLE oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);