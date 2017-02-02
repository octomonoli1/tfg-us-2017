#!/bin/sh

if [ ! $JAVA_HOME ]
then
	echo JAVA_HOME not defined.
	exit
fi

export JAVA_OPTS="-Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true @java.security.config@ -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=384m"

$JAVA_HOME/bin/java $JAVA_OPTS -jar ../start.jar