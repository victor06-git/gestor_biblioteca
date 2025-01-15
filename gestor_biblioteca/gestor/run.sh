#!/bin/bash

# Ejecutamos el comando de Maven
mvn clean test-compile exec:java -Dexec.mainClass=$1 -Djavafx.platform=$javafx_platform $mavenLogLevel
