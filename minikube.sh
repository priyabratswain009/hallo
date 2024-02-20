#! /bin/bash

sudo apt update && apt install -y docker.io
sudo usermod -aG docker ubuntu

# logout of the system then again login and execute below commands

curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl &&   chmod +x ./kubectl && sudo mv ./kubectl /usr/local/bin/kubectl

curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
&& chmod +x minikube && sudo mv minikube /usr/local/bin/

minikube start --driver=docker

minikube status

kubectl get node




