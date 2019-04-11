#!/bin/sh
/usr/bin/mvn package -f /usr/src/config-server -s /usr/src/config-server/docker/settings.xml
/usr/bin/java -jar /usr/src/config-server/target/config-server-lab.jar