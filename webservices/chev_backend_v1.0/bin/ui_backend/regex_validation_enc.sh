#!/bin/sh

#Config file that launches profiler script for regexp validation#
#Feb ... #

regexp=$1
file_name=$2

python regex_validation_enc.py "$regexp" "$file_name"
