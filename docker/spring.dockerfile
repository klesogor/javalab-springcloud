FROM openjdk:8-jdk-alpine3.9
MAINTAINER osmolkov
RUN apk update && apk add --no-cache nss git curl
COPY ./config-awaiter.sh /cmd/awaiter.sh
RUN chmod +x /cmd/awaiter.sh