

##Run DERBY database

- wejdz do katalogu /bin b.d. DERBY
- uruchom
    ./startNetworkServer

## Web Service Client

- stworz w IDEA project Web Service Client


## OAUTH

- należało ustawić pole CLIENT_SECRET: 123456 w OAUTH_CLIENT_DETAILS

lub

  można usunać z parametrow zadan parametr 'client_secret=123456'


Test the `greeting` endpoint:

```sh
curl http://localhost:8080/greeting
```

You receive the following JSON response, which indicates you are not authorized to access the resource:

```json
{
  "error": "unauthorized",
  "error_description": "An Authentication object was not found in the SecurityContext"
}
```

In order to access the protected resource, you must first request an access token via the OAuth handshake. Request OAuth authorization:

```sh
curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
```

A successful authorization results in the following JSON response:

```json
{
  "access_token": "ff16372e-38a7-4e29-88c2-1fb92897f558",
  "token_type": "bearer",
  "refresh_token": "f554d386-0b0a-461b-bdb2-292831cecd57",
  "expires_in": 43199,
  "scope": "read write"
}
```

Use the `access_token` returned in the previous request to make the authorized request to the protected endpoint:

```sh
curl http://localhost:8080/greeting -H "Authorization: Bearer ff16372e-38a7-4e29-88c2-1fb92897f558"
```

If the request is successful, you will see the following JSON response:

```json
{
  "id": 1,
  "content": "Hello, Roy!"
}
```

After the specified time period, the `access_token` will expire. Use the `refresh_token` that was returned in the original OAuth authorization to retrieve a new `access_token`:

```sh
curl -X POST -vu clientapp:123456 http://localhost:8080/oauth/token -H "Accept: application/json" -d "grant_type=refresh_token&refresh_token=f554d386-0b0a-461b-bdb2-292831cecd57&client_secret=123456&client_id=clientapp"
```
To configure the project to run on HTTPS as shown in https://spring.io/guides/tutorials/bookmarks/[Building REST services with Spring], enable the `https` profile. You can do this by uncommenting the appropriate line in the application.properties file of this project. This will change the server port to `8443`. Modify the previous requests as in the following command.

```sh
curl -X POST -k -vu clientapp:123456 https://localhost:8443/oauth/token -H "Accept: application/json" -d "password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
```

The `-k` parameter is necessary to allow connections to SSL sites without valid certificates or the self signed certificate which is created for this project.

------------------------------------------------
#TODO

- nie dziala wystawienie WS

- wyjasnic roznice


-<persistence version="2.1"
-       xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
-       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd ">
-       <persistence-unit name="bank" transaction-type="RESOURCE_LOCAL">
-               <provider>org.hibernate.ejb.HibernatePersistence</provider>
-               <class>pl.training.bank.entity.Account</class>
-               <class>pl.training.bank.entity.Customer</class>
-               <class>pl.training.bank.entity.Address</class>
-               <properties>
-                       <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
-                       <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/bank" />
-                       <property name="javax.persistence.jdbc.user" value="root" />
-                       <property name="javax.persistence.jdbc.password" value="admin" />
-                       <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
-               </properties>
-       </persistence-unit>
+<persistence version="2.1" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="h
+  <persistence-unit name="bank" transaction-type="JTA">
+    <provider>org.hibernate.ejb.HibernatePersistence</provider>
+    <jta-data-source>jdbc/bank</jta-data-source>
+    <validation-mode>NONE</validation-mode>
+    <properties>
+      <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/>
+      <property name="hibernate.transaction.jta.platform" value="org.hibernate.service.jta.platform.internal.SunOneJtaPlatform"/>
+    </properties>
+  </persistence-unit>

