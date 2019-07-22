#!/bin/bash
PID=`ps axj |grep "spring-boot-mongo-[^\s]\+.jar" |grep -v grep|awk '{print $2}'`
exitStatus=1

if [ ! -z "${PID}" ]; then
    echo kill -9 ${PID}
    set -x;kill -9 ${PID};set +x

     sleep 5s
     # check if service stopped
    PID=`ps axj |grep "spring-boot-mongo-[^\s]\+.jar" |grep -v grep|awk '{print $2}'`
    if [ ! -z "${PID}" ]; then
       echo ${PID}" spring-boot-mongo is not stopped"
       exitStatus=1
    else
       echo "spring-boot-mongo is stopped"
       exitStatus=0  
  fi
else
  echo "spring-boot-mongo is already stopped"
  exitStatus=0
fi
echo "Returning exit code $exitStatus"

exit $exitStatus;