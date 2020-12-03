#!/bin/bash
docker pull loreher/webapicarrito
sudo docker run -p 8281:8080 --name webapicarrito loreher/webapicarrito host.docker.internal 9092
