Installing OpenHMIS
=================================================================
 
General Notes:
-------------------
* This code base is expected to be built using Maven, and deployed to Tomcat.
* We are using MySQL as a data store.
* Although several core developers are using Eclipse, this is not a requirement to contribute.


To create a local build:
-------------------
The instructions below explain how to set up a development environment capable of running the API endpoints.  This section assumes you have already used git to download a local copy of the code base.

_In order for those endpoints to function correctly, you must also create a local copy of the schema._

1. Install JDK 1.7.x

2. Install [Maven (3.x)](https://maven.apache.org/download.cgi).

3. Install [Tomcat 7.x](https://tomcat.apache.org/download-70.cgi). Note that there may be a more recent version of Tomcat, but as of (6-22-2015) Maven plugins do not appear to exist beyond Tomcat 7.

4. Install [MySQL 5.x](http://dev.mysql.com/downloads/mysql/).

5. Create a Tomcat admin by editing `%TOMCAT7_PATH%/conf/tomcat-users.xml`.

	```XML
		<tomcat-users>
			<role rolename="manager-gui"/>
			<role rolename="manager-script"/>
			<user username="admin" password="password" roles="manager-gui,manager-script" />
		</tomcat-users>
	```

6. Restart Tomcat

	```Shell
		$> cd INSERT_PATH_TO_TOMCAT_HERE
		$> bin/shutdown.sh
		$> bin/startup.sh
	```

7. Update Maven's settings by editing `%MAVEN_PATH%/conf/settings.xml` so that Maven will be able to use the Tomcat user in step 3.

	* The `username` and `password` must match those set in step 3.
	* The ID must be `TomcatServer`.
	
		```XML
			<settings ...>
				<servers>
					<server>
						<id>TomcatServer</id>
						<username>admin</username>
						<password>password</password>
					</server>
				</servers>
			</settings>
		```


To create the schema:
---------------------
Database migrations are performed using [Flyway](http://flywaydb.org/).
_Note: you do not need to install anything for this to work.  Flyway is automatically loaded and used by Maven._

1. Using your tool of choice, create an empty MySQL schema.

	* The name of your schema is up to you.  In this example we use `openhmis`

	```mysql
	  mysql> create database openhmis;
	 ```

2. Using the tool of your choice, create a new user and grant access to the database you created in step 1.

	* The username and password is up to you.  In this example we use `openhmis_user` and `openhmis_password`
	* The database name must match the name created in step 1.

	```mysql
	  mysql> create user openhmis_user@localhost identified by "openhmis_password";
	  mysql> grant ALL on openhmis.* to openhmis_user@localhost identified by "openhmis_password";
	 ```

3. Create a local `config/flyway.properties` file with your database connection information

	* The schema name, username, and password entered in this file must match those created in steps 1 and 2

	```shell
	  $> cp src/config/flyway.properties.example src/config/flyway.properties
	  $> vi src/config/flyway.properties
	```


4. To initialize and update the schema, run the following command in the `pom.xml` directory


	```shell
	  $> mvn clean compile flyway:migrate
	```

5. Configure the code base to work with your schema by creating and populating the `src/main/resources/hibernate.cfg.xml` file.

	* The schema name, username, and password entered in this file must match those created in steps 1 and 2

	```shell
	  $> cp src/main/resources/hibernate.cfg.xml.example src/main/resources/hibernate.cfg.xml
	  $> vi src/main/resources/hibernate.cfg.xml
	```

To run the web service:
---------------------

1. Ensure that Tomcat is running (generally you can do this by going to http://localhost:8080)

2. Using a Command Line Interface, navigate to the root directory of this code base.  It should be the one containing `pom.xml`

3. Deploy using Maven:

	```shell
		$> mvn tomcat7:deploy
	```
	* If you have previously deployed this code with any tool, you will need to _redeploy_ using Maven.
	```shell
		$> mvn tomcat7:redeploy
	```
	
	If successful, the output will end with a message similar to the example below:
	
	```shell
		[INFO] tomcatManager status code:200, ReasonPhrase:OK
		[INFO] OK - Deployed application at context path /openhmis
		[INFO] ------------------------------------------------------------------------
		[INFO] BUILD SUCCESS
		[INFO] ------------------------------------------------------------------------
		[INFO] Total time: 4.488 s
		[INFO] Finished at: 2015-06-22T14:43:19-04:00
		[INFO] Final Memory: 20M/165M
		[INFO] ------------------------------------------------------------------------
	```

4. If your web service is properly configured, [http://localhost:8080/openhmis/services/healthcheck](http://localhost:8080/openhmis/services/healthcheck) should display "Your service is working." 

5. If the schema is properly set up, [http://localhost:8080/openhmis/services/clients/client/30486/user/password](http://localhost:8080/openhmis/services/clients/client/30486/user/password) should yield a valid XML object.


To debug the application in Eclipse:
-----------------------------------

1. Using a Command Line Interface, navigate to the root directory of this code base.  It should be the one containing `pom.xml`

```shell
		$> mvn eclipse:eclipse
	```
2. Import it as Existing Maven projects in the workspace

3. Configure the Tomcat to be used for deployment


Configuring Hibernate.Properties file:
-------------------------------------

1. Create a hibernate.properties file at any location on your hard drive

hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.connection.driver_class=com.mysql.jdbc.Driver
hibernate.connection.url=jdbc:mysql://servername:port/DB schema
hibernate.connection.username=<Your DB user name>
hibernate.connection.password=<Your DB password>
hibernate.connection.pool_size=1
hibernate.show_sql=true
hibernate.connection.autocommit=true
javax.persistence.validation.mode=none


2.Open the file <TOMCAT_HOME>/conf/context.xml

3.Add the following to the Context tag with the following value

<Environment name="config" value="<YOUR hibernate.properties folder location>"
            type="java.lang.String" />
	
