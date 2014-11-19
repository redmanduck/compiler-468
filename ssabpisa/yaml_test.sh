#!/bin/bash
#
#  Compile the compiler and test the result
#
TINY='tiny'
tc="${@: -1}_tests"
if [[ $* == *-c* ]]
then
  #compile the compiler first
  tc="${@: -1}_tests" 
  make clean && make
  rm -rf output/*
fi
dirs=$(ls $tc/*.micro)
FAIL=0
for n in ${dirs}
do
  f=$(echo $n | cut -d'/' -f2 | cut -d'.' -f1)
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
  inkey="$tc/$f.in"
  if [ ! -e $inkey ]
  then
    echo "$(tput setaf 1) [MISSING DEP] $(tput setaf 0)"
    touch $inkey
    FAIL=1
    continue
  fi
  t=$(./$TINY output/$f.T < $inkey 2>> transcript) #run my micro
  j=$?
  g=$(./$TINY $tc/$f.out < $inkey 2>> transcript)  #run given micro
  if (( $? != 0 )) || (( $j != 0 )) 
  then
      FAIL=1
      echo  "$(tput setaf 1) [ERROR] $(tput setaf 0)"
      continue
  fi
  test=$(echo $t | sed s/ST/\\\|/g | cut -d'|' -f1)
  gold=$(echo $g | sed s/ST/\\\|/g | cut -d'|' -f1)    
  echo "++++ $n"  >> transcript
  echo "===================================================================" >> transcript
  echo $test >> transcript  
  echo $gold >> transcript
  echo "===================================================================" >> transcript
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
