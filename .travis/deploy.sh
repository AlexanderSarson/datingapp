#!/bin/bash

eval "$(ssh-agent -s)" # Start ssh-agent cache
chmod 600 .travis/deploy_rsa # Allow read access to the private key
ssh-add .travis/deploy_rsa # Add the private key to SSH

git config --global push.default matching
git remote add deploy ssh://alex@$IP:$PORT$DEPLOY_DIR
git push deploy master

# Skip this command if you don't need to execute any additional commands after deploying.
ssh alex@$IP -p $PORT <<EOF
  cd $DEPLOY_DIR
  cp -f root.war /opt/tomcat/webapps
EOF