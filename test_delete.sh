#!/bin/bash

# curl must be available, otherwise this script can't run.
command -v curl >/dev/null 2>&1 || { echo "I require curl but it's not installed. Aborting." >&2; exit 1; }

# send DELETE
curl -i -H "Accept: application/json" -X DELETE http://192.168.11.102:8080/jersey_rest/rest/isp/1
