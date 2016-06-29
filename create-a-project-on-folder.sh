#!/usr/bin/env bash

target=$1
me=`basename "$0"`
folder=`pwd`
mkdir $target
rsync -av "$folder/" "$target"
rm -f "$target/$me"
