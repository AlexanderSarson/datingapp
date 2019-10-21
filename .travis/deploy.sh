#!/bin/bash

eval "$(ssh-agent -s)" # Start ssh-agent cache
chmod 600 .travis/deploy_rsa # Allow read access to the private key
ssh-add .travis/deploy_rsa # Add the private key to SSH

echo scp
scp -p 'target/*.war' $USER@$IP:/opt/tomcat/webapps/
#echo rsync
#rsync -avz target/root.war alex@$IP:/opt/tomcat/webapps/

ssh $USER@$IP -p $PORT <<EOF
  cd $DEPLOY_DIR
#  cp -f root.war /opt/tomcat/webapps
EOF