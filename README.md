

##Run DERBY database

- wejdz do katalogu /bin b.d. DERBY
- uruchom
    ./startNetworkServer


#TODO

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

