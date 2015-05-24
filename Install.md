# How to Install and Run Kanbanik #

There are three ways how to install kanbanik:
  * The simple way: using the runtime
  * The intermediate way: using the WAR file
  * The hard way: from sources

# Install Using the Runtime #
  * Download the kanbanik-runtime-0.2.x.zip from the download section for your operating system.
  * Download the kanbanik-application-0.2.x.zip from the download section
  * Unzip both archives
  * Copy the kanbanik.war from the kanbanik-application to the jetty/webapps of the kanbanik-runtime (please note that the name must stay be kanbanik.war)
  * on Linux: make the start and stop scripts executable by
```
chmod +x start.sh stop.sh
```
  * run the start.sh or start.bat
  * access the kanbanik
    * for linux: http://localhost:4041/kanbanik/
    * for windows http://localhost:8080/kanbanik/
  * to stop the application
    * for linux run ./stop.sh
    * for windows press ctrl+c in both windows which got opened after running the start.bat

# Install Using WAR file #
  * Install MongoDB (2.2.0 or higher)
  * Run MongoDB on default port (27017)
  * Install a java web server of your choice (e.g. jetty, tomcat, etc.)
  * Download the kanbanik-application-0.2.x.zip from the download section
  * Unpack the application
  * Deploy the kanbanik.war file into the web server

## Changing the MongoDB connection settings ##
By default, Kanbanik will try to login to mongo instance with the following settings:
server: 127.0.0.1
port: 27017
db name: kanbanikdb
credentials: without authentication

If you need to change any of this settings, it can be done only in web.xml. To do it, you need to edit the web.xml inside the WAR archive in the kanbanik-application.war/WEB-INF/web.xml
The following options are there:
| **Param** | **Description** | **Default Value** |
|:----------|:----------------|:------------------|
| mongoServer | Server where the MongoDB instance is running | 127.0.0.1         |
| mongoPort | Port on which MongoDB is listening | 27017             |
| mongoDBName | The name of the DB in the MongoDB instance. Should not make sense to change it. | kanbanikdb        |
| mongoAuthenticationRequired | If should try to authenticate against MongoDB | false             |
| mongoUser | The user name used to connect to MongoDB - ignored when mongoAuthenticationRequired is false | empty             |
| mongoPassword | The password used to connect to MongoDB - ignored when mongoAuthenticationRequired is false | empty             |

# Install From Sources #
Preconditions:
  * JDK 1.6 or higher properly installed and set up
  * maven2 o higher properly installed and set up
  * If you want to execute also the tests during the build, than a MongoDB instance with no authorization running on default port on the same machine as the build is executed

If you have all the preconditions fulfilled, than:
  * First you need to checkout the sources:
```
git clone https://code.google.com/p/kanbanik/ 
```
  * Open a console and cd to the
```
<kanbanik-root>/kanbanik-parent directory
```
  * Type
```
mvn clean install
```
    * If you want to skip the tests, than
```
mvn clean install -DskipTests
```
  * This will take a while at the first time, as maven will need to download lots of dependencies.
  * If successfully finished, you should find the final WAR in kanbanik-web:
```
<kanbanik-root>/kanbanik-web/target/kanbanik.war
```
  * If yes, you can use this WAR file to deploy to the kanbanik-runtime (Install Using the Runtime) or to your own environment (Install Using WAR file)

# First Login #
After you have successfully installed Kanbanik, you will need to log in. The default credentials are:
  * **Username:** admin
  * **Password:** admin