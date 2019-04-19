#!/bin/bash
for d in */ ; do
    cd $d
    mvn clean package
    cd ..
done

docker-compose up --build
