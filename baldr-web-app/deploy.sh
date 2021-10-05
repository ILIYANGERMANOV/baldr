#!/bin/bash

SERVER_IP="212.111.42.173"

exec_command_on_server() {
  ssh root@$SERVER_IP "$1" || exit
}

#Machine requirements:
#sudo apt-get update
#sudo apt-get install git
#sudo apt-get install openjdk-11-jdk

#https://www.linode.com/docs/guides/set-up-web-server-host-website/
#sudo apt install nginx
#sudo mkdir -p /var/www/baldr.bg
#sudo rm /etc/nginx/sites-enabled/default

echo "[DEPLOY 1/2] Building web pack..."
./gradlew jsBrowserDevelopmentWebpack || exit

echo "[DEPLOY 2/2] Copying web pack files on server..."
scp build/developmentExecutable/* root@$SERVER_IP:/var/www/baldr.bg || exit

echo "baldr.bg deployed successfully on $SERVER_IP"
