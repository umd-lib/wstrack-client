#!/bin/bash

# command line arguments
STATUS="$1"
if [ -z "$STATUS" ]; then
  echo "Status is required (login or logout)"
  exit 1
fi

ENVIRONMENT=local
if [ -n "$2" ]; then
  ENVIRONMENT="$2"
fi

DEBUG="false"
if [ -n "$3" ]; then
  DEBUG="true"
fi

# gather information about this host
IP=$(/sbin/ifconfig | /usr/bin/grep 'inet ' | /usr/bin/grep -v 127\.0\.0\.1 | /usr/bin/cut -d' ' -f2)

OSNAME=$(/usr/bin/sw_vers -productName)
OSVER=$(/usr/bin/sw_vers -productVersion)

java \
  -Dwstrack.debug="$DEBUG" \
  -Dwstrack.env="$ENVIRONMENT" \
  -Dwstrack.ip="$IP" \
  -Dwstrack.status="$STATUS" \
  -Dwstrack.hostname="$HOSTNAME" \
  -Dwstrack.username="$USER" \
  -Dwstrack.os="$OSNAME $OSVER" \
  -jar ./wstrack-client.jar
