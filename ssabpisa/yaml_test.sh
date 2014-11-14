#!/bin/bash
#
#  Compile the compiler and test the result
#
TINY='tinyR'
tc="${@: -1}_tests"
if [[ $* == *-c* ]]
then
  #compile the compiler first
  tc="${@: -1}_tests" 
  make clean && make
fi
dirs=$(ls $tc)
FAIL=0
for n in ${dirs}
do
  f=$(echo $n | cut -d'.' -f1)
  if [ "no_auto" == "$f" ] 
  then
  	continue
  fi
  echo -n "$(tput setaf 0) Testing $(tput bold) $f..$(tput sgr0)"
  if [[ $* == *-c* ]]
  then
    #process our micro
    java -cp lib/antlr.jar:classes/ Micro $tc/$f.micro > output/$f.T
  fi
  test=$(./$TINY output/$f.T | sed '/STATISTICS/q' ) #run my micro
  gold=$(./$TINY $tc/$f.out | sed '/STATISTICS/q' )  #run given micro
  df=$(diff -b -B <(echo $test) <(echo $gold))
  if [ "$df" = "" ]
  then
    echo "$(tput setaf 6) [PASSED] $(tput setaf 0)"
  else
    echo "$(tput setaf 1) [FAILED] $(tput sgr0)"
    FAIL=1
  fi
done

if (( $FAIL == 1 ))
  then
    exit 1
fi
