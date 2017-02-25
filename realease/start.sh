#!/bin/bash

LANG=en_US.utf8

#goto current shell file dir
cd `dirname $0`

if [ ! -d "static" ]
then
  mkdir ./static
fi


if [ -f "nohup.out" ]; then
  rm -f nohup.out
fi

if [ -f "pid" ]; then
  kill $(cat pid)
fi

nohup java -jar gitbookdeployer-0.0.1-SNAPSHOT.jar &