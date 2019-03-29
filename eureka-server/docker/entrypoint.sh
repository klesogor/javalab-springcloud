#!/bin/sh
/usr/bin/mvn package -f /usr/src/eureka-server -s /usr/src/eureka-server/docker/settings.xml
/usr/bin/java -jar /usr/src/eureka-server/target/eureka-server-lab.jar