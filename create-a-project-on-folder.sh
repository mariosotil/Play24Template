#!/usr/bin/env bash

this_script=`basename "$0"`

source="`pwd`/"
target=$1

read -p "Copying from $source to $target. Do you want to continue? [Y/n] " -n 1 -r
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

# Copy everything...
rsync -av "$source" "$target"

# Except this script
rm -f "$target/$this_script"
