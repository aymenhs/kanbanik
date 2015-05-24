The application is developed and tested on Linux. It can be started also on Windows, but not that simply.

# Install from all in one archive #

## Linux ##

The precondition is to have the Java 6 or newer installed and have java on the PATH. Than:

  * download the all in one zip from the Download section
  * unzip the kanbanik.zip file
  * open terminal, cd into the _kanbanik_ folder
  * run _chmod +x start.sh stop.sh_
  * to start the app, run _./start.sh_ (this will start HSQL and Jetty)
  * open a web browser and go to _http://localhost:4040/kanbanik/_
  * to stop the application, run _./stop.sh_ (this will stop HSQL and Jetty)

## Windows ##

The precondition is to have the Java 6 or newer correctly installed.
Than:
  * download the all in one zip from the Download section
  * unzip the kanbanik.zip file
  * open command line, cd into the _...\kanbanik\hsqldb\kanbanik_ folder
  * run _java -cp ../lib/hsqldb.jar org.hsqldb.Server -database.0 file:kanbanik -dbname.0 xdb_
  * this started the HSQL database. Now open a new terminal and cd into the _...\kanbanik\jetty_ folder
  * run _java -jar start.jar_
  * this started the Jetty web server. Now open a web browser and go to the page: _http://localhost:8080/kanbanik_
  * to stop the application, press _ctrl+c_ in both command lines

# Install from war + MySQL archive #

This steps will guide you how to install kanbanik on your own web server and MySQL DB:
  * download the war + MySQL dump zip from Download section
  * unzip the _kanbanik-version.zip_ file
  * it will create a folder named _kanbanik_
  * in this folder there are two files
    * the kanbanik.sql - the SQL dump
    * kanbanik.war - the war archive of the application
  * import the kanbanik.sql to your MySQL server. It will create the required schema and also some example data
  * now you need to configure kanbanik to be able to communicate with your DB server. To do this, you will have to edit (unfortunately) the Spring config file inside the kanbanik.war. You can find it here: _kanbanik.war/WEB-INF/lib/model-0.1.0.jar/dataSource.xml_
  * in this file you will find this three relevant properties:
```
<property name="url" value="jdbc:mysql://localhost:3306/kanbanik" />
<property name="username" value="" />
<property name="password" value="" />
```
  * adjust url to point to your MySQL server (don't touch the _jdbc:mysql://_ part and the _/kanbanik_ part. Only the _localhost:3306_ is the _server:port_ what should be adjusted)
  * set the correct name and password to your DB server
  * save this file
  * now you should have a kanbanik.war file which contains the dataSource.xml file with adjusted properties. Go ahead and deploy this war file to your favorite server (e.g. Tomcat)
  * that's it - kanbanik should be running on yourServer:yourPort/kanbanik/