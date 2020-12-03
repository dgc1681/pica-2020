#!/bin/bash
sudo docker build -t orden .
sudo docker run -p 8185:8180 -it --name orden orden host.docker.internal 9092