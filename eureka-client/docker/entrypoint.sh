#!/bin/sh
sleep 20
/usr/bin/mvn package -f /usr/src/eureka-client -s /usr/src/eureka-client/docker/settings.xml
/usr/bin/java -jar /usr/src/eureka-client/target/eureka-client-lab.jar