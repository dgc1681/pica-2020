#!/bin/bash
sudo docker build -t orden .
sudo docker run -p 8185:8180 --name orden orden