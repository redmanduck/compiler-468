#!/bin/bash

dpath="java -cp lib/antlr.jar:classes/ Micro testcases/input/"
ins=$(ls testcases/input)
for i in ${ins[@]}
do
	mod=$(echo $i | cut -f1 -d'.');
	echo "-----" $mod 
	cat testcases/output/$mod.out | head -n 1
	$dpath$i
done
