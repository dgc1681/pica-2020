#!/bin/bash
sudo docker build -t checkout .
sudo docker run -p 8186:8181 -it --name checkout checkout host.docker.internal 9092