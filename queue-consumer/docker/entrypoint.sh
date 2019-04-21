#!/bin/sh
sleep 15
/usr/bin/mvn package -f /usr/src/queue-consumer -s /usr/src/queue-consumer/docker/settings.xml
/usr/bin/java -jar /usr/src/queue-consumer/target/hosting-queue-consumer-lab.jar
