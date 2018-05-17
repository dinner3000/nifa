#!/bin/sh

#nohup /etc/alternatives/java -jar nifa-reporting-service-1.0.jar > /dev/null 2>&1 &
./daemon.sh start "/etc/alternatives/java -jar nifa-reporting-service-1.0.jar" 60
