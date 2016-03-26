#!/bin/sh

rm ./RUNNING_PID 2> /dev/null

java \
  -Xms512M \
  -Xmx1536M \
  -XX:MaxPermSize=512M \
  -Dhttp.port=53525 \
  -Dfile.encoding=UTF-8 \
  $SBT_OPTS \
  -jar `dirname $0`/sbt-launch.jar \
  "$@"
