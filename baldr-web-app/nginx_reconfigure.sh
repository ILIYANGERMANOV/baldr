#!/bin/bash

SERVER_IP="212.111.42.173"

exec_command_on_server() {
  ssh root@$SERVER_IP "$1" || exit
}

CURRENT_STEP=1
TOTAL_STEPS=3

log_step() {
  bold=$(tput bold)
  normal=$(tput sgr0)
  echo "${bold}[STEP $CURRENT_STEP/$TOTAL_STEPS] $1${normal}"
  ((CURRENT_STEP=CURRENT_STEP+1))
}

log_step "Copying \"baldr.bg.conf\" nginx config to server..."
scp nginx_baldr.bg.conf root@$SERVER_IP:/etc/nginx/conf.d/baldr.bg.conf || exit

log_step "Testing nginx configuration..."
exec_command_on_server "nginx -t" || exit

log_step "Reload nginx...."
exec_command_on_server "nginx -s reload" || exit

echo "SUCCESS: Nginx reconfigured successfully."
