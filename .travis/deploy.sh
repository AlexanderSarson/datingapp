#!/bin/bash

eval "$(ssh-agent -s)" # Start ssh-agent cache
chmod 600 .travis/deploy_rsa # Allow read access to the private key
ssh-add .travis/deploy_rsa # Add the private key to SSH

scp -p target/root.war $USERNAME@$IP:/opt/tomcat/webapps/