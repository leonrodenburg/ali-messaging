#!/bin/bash
set -e

for d in */ ; do
    cd $d
    mvn package
    cd ..
done

docker-compose up --build
