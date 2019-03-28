#!/bin/sh
/usr/bin/mvn package -f /usr/src/eureka-server -s /usr/src/app/docker/settings.xml
mkdir -p /var/www/spring-eureka
cp /usr/src/app/target/eureka-server-lab.jar /var/www/spring-eureka
/usr/bin/java -jar /var/www/spring-app/eureka-server-lab.jar