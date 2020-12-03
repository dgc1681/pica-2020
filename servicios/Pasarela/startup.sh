#!/bin/bash
sudo docker build -t pasarela .
sudo docker run -p 8187:8182 -it --name pasarela pasarela host.docker.internal 9092