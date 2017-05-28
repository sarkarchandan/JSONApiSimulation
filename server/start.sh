#!/bin/sh

echo 'building server'

/usr/local/glassfish4/bin/asadmin start-domain

/application/glassfishConfiguration/configure.sh

./gradlew build
./gradlew undeploy
./gradlew deploy

/usr/local/glassfish4/bin/asadmin stop-domain
/usr/local/glassfish4/bin/asadmin start-domain  --verbose