#!/bin/bash

if [ $# -lt 1 ]; then
    echo "Environment name (test, stst, etc.) is not specified! Usage: ./start-service.sh stst"
	exit 1
fi

source /opt/apps/spring-boot-mongo/version.properties
JAR_VERSION=$version
ACTIVE_PROFILE=$1
nohup java -Xmx1024m -Xms256m -Djava.security.egd=file:/dev/urandom -jar /opt/apps/spring-boot-mongo-"$JAR_VERSION".jar  --spring.profiles.active=$ACTIVE_PROFILE > /dev/null 2>&1 &
 # check if the service is started
 sleep 25s
 PID=`ps axj |grep "spring-boot-mongo-[^\s]\+.jar" |grep -v grep|awk '{print $2}'`
 echo ${PID}


 if [ ! -z "${PID}" ]; then
       echo ${PID}" spring-boot-mongo is started successfully"
       exitStatus=0
    else
       echo "spring-boot-mongo cannot be started"
       exitStatus=1  
  fi

echo "Returning exit code $exitStatus"

exit $exitStatus;
