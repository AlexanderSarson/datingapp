#!/bin/bash

eval "$(ssh-agent -s)" # Start ssh-agent cache
chmod 600 .travis/deploy_rsa # Allow read access to the private key
ssh-add .travis/deploy_rsa # Add the private key to SSH
cd target && ls
echo scp
scp -P $PORT -p target/root.war alex@$IP:/opt/tomcat/webapps/
echo rsync
rsync -avz target/root.war alex@$IP:/opt/tomcat/webapps/

ssh alex@$IP -p $PORT <<EOF
  cd $DEPLOY_DIR
#  cp -f root.war /opt/tomcat/webapps
EOF