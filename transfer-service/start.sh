#!/bin/sh

echo -n "start up nifa server ... "
java -jar nifa-sftp-service.jar --Dspring.config.location=application.yml -Dspring.profiles.active=test >> runlog 2>&1 &
echo "done"
