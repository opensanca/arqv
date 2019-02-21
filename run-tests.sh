#!/bin/bash

commands=(
  "./mvnw clean test"
  "./demos/java/spring-boot-demo/mvnw test -Dtest=ArqvTestSuite -f demos/java/spring-boot-demo"
)

for cmd in "${commands[@]}"
do
  echo " ========== RUNNING $cmd =========="
  
  eval $cmd

  ERROR_CODE=$?
  if [ ${ERROR_CODE} != 0 ]; then
    echo " ========== BUILD FAILURE. Tests are broken. =========="
    exit 1
  fi
done
