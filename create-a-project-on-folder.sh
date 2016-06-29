#!/usr/bin/env bash

this_script=`basename "$0"`

source="`pwd`/"
target=$1
project=`basename ${target}`

read -p "Copying the project $project, from $source to $target. Do you want to continue? [Y/n] " -n 1 -r
echo
if [[ ! $REPLY =~ ^[Yy]$ ]]
then
    exit 1
fi

# Check if the folder exists
if [[ -e $target ]]; then
    echo "$target already exists" 1>&2
    exit 99
fi

# Copy everything, except the ".git" folder and this script
rsync -av --exclude ".git" --exclude "$this_script" "$source" "$target"

# Update the configuration file with the name of this project
sed -i -e "s/Play24Template/${project}/g" "$target/build.sbt"