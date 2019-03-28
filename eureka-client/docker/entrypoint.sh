#!/bin/sh
/usr/bin/mvn package -f /usr/src/app -s /usr/src/app/docker/settings.xml
mkdir -p /var/www/eureka-client
cp /usr/src/eureka-client/target/eureka-client-lab.jar /var/www/spring-app
sleep 10
/usr/bin/java -jar /var/www/eureka-client/eureka-client-lab.jar