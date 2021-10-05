#!/bin/bash

SERVER_IP="212.111.42.173"

exec_command_on_server() {
  ssh root@$SERVER_IP "$1" || exit
}

CURRENT_STEP=1
TOTAL_STEPS=3

log_step() {
  echo "[DEPLOY $CURRENT_STEP/$TOTAL_STEPS] $1"
  ((CURRENT_STEP=CURRENT_STEP+1))
}

#Machine requirements:
#sudo apt-get update
#sudo apt-get install git
#sudo apt-get install openjdk-11-jdk

#https://www.linode.com/docs/guides/set-up-web-server-host-website/
#sudo apt install nginx
#sudo mkdir -p /var/www/baldr.bg
#sudo rm /etc/nginx/sites-enabled/default

set -x
log_step "Building web pack..."
./gradlew jsBrowserDevelopmentWebpack || exit

log_step "Modifying index.html for deploy..."
sed -i 's,http://localhost:8080/baldr-web-app.js,/var/www/baldr.bg/baldr-web-app.js,g' build/developmentExecutable/index.html || exit

log_step "Copying web pack files on server..."
scp build/developmentExecutable/* root@$SERVER_IP:/var/www/baldr.bg || exit

echo "baldr.bg deployed successfully on $SERVER_IP"
