#!/bin/bash
#First parameter is test,stst or  utst

if [ $# -lt 1 ]; then
    echo "Environment is not provided!"
	exit 1
fi

SERVICE_ENV=$1
SERVICE_SCRIPT_PREFIX=/opt/apps
SERVICE_SCRIPT_SUFFIX=scripts/start-service.sh
SERVICE_SCRIPT_SUFFIX_STOP=scripts/stop-service.sh

# ex: servicesArr=("app1" "app2" "app3")
servicesArr=("spring-boot-mongo")

echo "You have requested to start service environment: $SERVICE_ENV"

for i in "${servicesArr[@]}"
do
   SERVICE_INSTANCE_INPUT="$i"
   echo "You have requested to start service instance: $SERVICE_INSTANCE_INPUT"
  
   SERVICE_SCRIPT_PATH=$SERVICE_SCRIPT_PREFIX/$SERVICE_INSTANCE_INPUT/$SERVICE_SCRIPT_SUFFIX
   if [ ! -f $SERVICE_SCRIPT_PATH ]; then
	echo "$SERVICE_INSTANCE_INPUT is not a valid Service instance."
	exit 2
   fi 
   
   #Stop if already running before start
   SERVICE_SCRIPT_PATH_STOP=$SERVICE_SCRIPT_PREFIX/$SERVICE_INSTANCE_INPUT/$SERVICE_SCRIPT_SUFFIX_STOP
   $SERVICE_SCRIPT_PATH_STOP 

   #run the service start script
   $SERVICE_SCRIPT_PATH $SERVICE_ENV
   
   if [ $? -eq 0 ]
   then
	  echo "Successfully started: $SERVICE_INSTANCE_INPUT"
   else
	  echo "Fail to start: $SERVICE_INSTANCE_INPUT"
          exit $?	
   fi
done

echo "Successfully started all the services."
exit 0
