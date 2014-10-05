#!/bin/bash

dpath="java -cp lib/antlr.jar:classes/ Micro input/"
ins=$(ls input)
for i in ${ins[@]}
do
	mod=$(echo $i | cut -f1 -d'.');
  echo
	echo "=========" $mod "=========="
	echo
  #sdiff output/468/$mod.out
  $dpath$i > temp_result
  sdiff output/468/$mod.out temp_result
done
