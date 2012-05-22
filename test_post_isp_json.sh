#!/bin/bash

# curl must be available, otherwise this script can't run.
command -v curl >/dev/null 2>&1 || { echo "I require curl but it's not installed. Aborting." >&2; exit 1; }

# send JSON
curl -v -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"id":4,"name":"TMN"}' http://192.168.11.102:8080/jersey_rest/rest/isp/
