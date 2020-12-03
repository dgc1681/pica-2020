#!/bin/bash
docker pull loreher/webapiorden
sudo docker run -p 8280:8081 --name webapiorden loreher/webapiorden host.docker.internal 9092
