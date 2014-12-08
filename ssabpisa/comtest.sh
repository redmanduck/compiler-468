#!/bin/bash
if [[ ! -e $1 ]] 
then
   echo "Usage : comtest.sh <program_src>.micro"
   exit 1
fi
echo "Compiling compiler"
make > /dev/null
echo "Compiling Tiny4"
make tiny4 > /dev/null
echo "Compiling Program"
java -cp lib/antlr.jar:classes/ Micro $1 > step0_tests/temp
echo "============ Running $1 program ==============="
./tiny step0_tests/temp
