#!/bin/bash

#$1 gitUrl
#$2 repoName (by caller splite from gitUrl)
#$3 deploy dir (such as "mybook",will be deployed to /var/www/html/mybook)
rootDir=`dirname $0`
gitUrl=$1
repoName=$2

echo "--------start build--------"
curday=`date '+%Y-%m-%d %H:%M:%S'`
echo $curday

#goto current shell file dir
cd $rootDir

if [ ! -d ./static ]; then
  mkdir ./static
fi


if [ ! -d ./localGitbook ]; then
  mkdir ./localGitbook
fi

#goto local books dir
cd ./localGitbook

if [ ! -d $repoName ]; then
    git clone $gitUrl.git
    cd ./$repoName
else
    cd ./$repoName
    git pull origin
fi

gitbook install
gitbook build ./ ./__book__
rm -r ../../static/$repoName
mv ./__book__ ../../static/$repoName
echo "---------end build---------"