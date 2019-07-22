#!/bin/bash

SERVICE_SCRIPT_PREFIX=/opt/apps
SERVICE_SCRIPT_SUFFIX_STOP=scripts/stop-service.sh

servicesArr=("spring-boot-mongo" )

for i in "${servicesArr[@]}"
do
   SERVICE_INSTANCE_INPUT="$i"
   echo "You have requested to stop service instance: $SERVICE_INSTANCE_INPUT"
   
   #Stop
   SERVICE_SCRIPT_PATH_STOP=$SERVICE_SCRIPT_PREFIX/$SERVICE_INSTANCE_INPUT/$SERVICE_SCRIPT_SUFFIX_STOP
   $SERVICE_SCRIPT_PATH_STOP $SERVICE_ENV
   
   if [ $? -eq 0 ]
   then
	  echo "Successfully stop: $SERVICE_INSTANCE_INPUT"
   else
	  echo "Fail to stop: $SERVICE_INSTANCE_INPUT "	
	  exit $?
   fi
   
done

echo "Successfully stop all the services."
exit 0
