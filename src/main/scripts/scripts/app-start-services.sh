#!/bin/bash
# wrapper script to invoke services-start script based on service name
#some environments use short name; some use FQDN

HOSTNAME=`hostname -s`

echo "Running: $HOSTNAME:$0 ..."
if [ $# -lt 2 ]; then
    echo "Service name and/or environment is not provided!"
	exit 1
fi

SERVICE_INSTANCE_INPUT=$1
SERVICE_ENV=$2
SERVICE_SCRIPT_PREFIX=/opt/apps
SERVICE_SCRIPT_SUFFIX=scripts/start-service.sh
SERVICE_SCRIPT_SUFFIX_STOP=scripts/stop-service.sh

echo "You have requested to start Service instance: $SERVICE_INSTANCE_INPUT"
SERVICE_SCRIPT_PATH=$SERVICE_SCRIPT_PREFIX/$SERVICE_INSTANCE_INPUT/$SERVICE_SCRIPT_SUFFIX
if [ ! -f $SERVICE_SCRIPT_PATH ]; then
	echo "$SERVICE_INSTANCE_INPUT is not a valid Service instance."
	exit 2
fi

echo "Kicking off: $SERVICE_SCRIPT_PATH"

#Stop if already running before start
SERVICE_SCRIPT_PATH_STOP=$SERVICE_SCRIPT_PREFIX/$SERVICE_INSTANCE_INPUT/$SERVICE_SCRIPT_SUFFIX_STOP
$SERVICE_SCRIPT_PATH_STOP $SERVICE_ENV

# run the service start script
$SERVICE_SCRIPT_PATH $SERVICE_ENV

# return the exit code from previous command
exit $?
