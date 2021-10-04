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
#File: /etc/nginx/conf.d/example.com.conf
#server {
#    listen         80;
#    listen         [::]:80;
#    server_name    baldr.bg www.baldr.bg;
#    root           /var/www/baldr.bg;
#    index          index.html;
#
#    gzip             on;
#    gzip_comp_level  3;
#    gzip_types       text/plain text/css application/javascript image/*;
#}
#sudo rm /etc/nginx/sites-enabled/default



echo "Cleaning up"
exec_command_on_server "rm -rf baldr" || exit

echo "Cloning the latest baldr version"
#git clone https://github.com/ILIYANGERMANOV/baldr.git

#cd baldr/baldr-web-app/

#./gradlew jsBrowserRun
