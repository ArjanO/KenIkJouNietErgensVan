# Ken ik jou niet ergens van?

Services for the Dare2Date casus of the HAN minor Advanced Programming course Advanced Software Engineering. This services finds out if you and a other person may know each other from a event, school or work.

## Authors

**Arjan Oortgiese**

+ [https://github.com/ArjanO](https://github.com/ArjanO)

**Joëll Portier**

+ [https://github.com/Sjoel](https://github.com/Sjoel)

## Spring-ws
"Ken ik jou niet ergens van?" service implemented with Spring Web Services.

### Facebook
To add Facebook support create /spring-ws/src/main/webapp/facebook.xml and add the follow content.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- Configuration for Facebook. Don't publish this file with your Facebook app id and app secret -->
    <bean id="facebookApi" class="com.dare2date.externeservice.facebook.FacebookAPI">
        <property name="appId" value="YOUR_FACEBOOK_APP_ID" />
        <property name="appSecret" value="YOUR_FACEBOOK_APP_SECRET" />
    </bean>
</beans>
```

Note the file facebook.xml is ignored by git. 

## Building with maven notes

If it fails on javax.activation:

1.	Download the file manually: http://mirrors.ibiblio.org/maven2/javax/xml/bind/activation/1.0.2/activation-1.0.2.jar
2.	Add it to your local repo: mvn install:install-file -DgroupId=javax.activation -DartifactId=activation -Dversion=1.0.2 -Dpackaging=jar -Dfile=activation-1.0.2.jar

## Database script
```sql
CREATE DATABASE IF NOT EXISTS dare2date;
use dare2date;
CREATE TABLE IF NOT EXISTS userservicesauth
(
       dare2dateUsername varchar(50),
    username varchar(50),
    accesstoken varchar(50),
    platformname varchar(50),
    CONSTRAINT pk_Dare2date PRIMARY KEY (Dare2dateUsername,platformname)
);
```

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
