# Ken ik jou niet ergens van?

Services for the Dare2Date casus of the HAN minor Advanced Programming course Advanced Software Engineering. This services finds out if you and a other person may know each other from a event, school or work.

## Authors

**Arjan Oortgiese**

+ [https://github.com/ArjanO](https://github.com/ArjanO)

**Joëll Portier**

+ [https://github.com/Sjoel](https://github.com/Sjoel)

## Spring-ws
"Ken ik jou niet ergens van?" service implemented with Spring Web Services.

### Configuration
Add spring-ws/src/main/webapp/service.properties

```plain
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/dare2date
jdbc.username=dare2datauser
jdbc.password=password

facebook.appid=YOUR_FACEBOOK_APP_ID
facebook.appsecret=YOUR_FACEBOOK_APP_SECRET

lastfm.apikey=YOUR_LASTFM_API_KEY
```

Note the file service.properties is ignored by git. 

## Building with maven notes

If it fails on javax.activation:

1.	Download the file manually: http://mirrors.ibiblio.org/maven2/javax/xml/bind/activation/1.0.2/activation-1.0.2.jar
2.	Add it to your local repo: mvn install:install-file -DgroupId=javax.activation -DartifactId=activation -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar

## Database script
```sql
CREATE DATABASE IF NOT EXISTS dare2date;
USE dare2date;
CREATE TABLE IF NOT EXISTS userservicesauth
(
	dare2dateUsername varchar(50),
	username varchar(50),
	accesstoken varchar(50),
	platformname varchar(50),
	CONSTRAINT pk_Dare2date PRIMARY KEY (dare2dateUsername, platformname)
);
```

## Facebook
Getting a users Facebook access token is out the scope of this project. To get a Facebook access token for testing:

1. Create a Facebook APP on http://developers.facebook.com/
2. Create service.properties config file and add the App ID and App Secret from your Facebook APP.

### Get users Access token 
The service expect that there is a access_token for users that have Facebook. For more information about the login flow see https://developers.facebook.com/docs/facebook-login/login-flow-for-web-no-jssdk/

To get a access_token for testing:

1. Upload the PHP script below to your webhosting:

  ```php
  <?php
  if (isset($_GET['code'])) {
    echo "Facebook code: " . $_GET['code'];
  }
  ?>
  ```

2. Visit https://www.facebook.com/dialog/oauth?scope=user_education_history,user_work_history,user_events&client_id={app-id}&redirect_uri={redirect-uri}\{script.php} to get the code.
3. To get the access_token from the code visit https://graph.facebook.com/oauth/access_token?client_id={app-id}&redirect_uri={redirect-uri}&client_secret={app-secret}&code={code-parameter}

Get the access_token from the response.

## MIT License
Copyright (c) 2013 HAN University of Applied Sciences
Arjan Oortgiese
Joëll Portier

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
