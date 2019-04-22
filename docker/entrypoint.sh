#!/bin/sh
until mvn package -f /usr/src -s /usr/src/docker/settings.xml
do
    echo "Retrying mvn package"
done