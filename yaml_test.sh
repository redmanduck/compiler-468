#!/bin/bash
#
TINY='tiny'
dirs=$(ls output)
for n in ${dirs}
do
  FAIL=0
  f=$(echo $n | cut -d'.' -f1)
  echo -n "$(tput setaf 0) Testing $(tput bold) $f..$(tput sgr0)"
  test=$(./tiny output/$f.T | sed '/STATISTICS/q' )
  gold=$(./tiny ssabpisa/milind/$f.out | sed '/STATISTICS/q' )
  df=$(diff -b -B <(echo $test) <(echo $gold))  
  if [ "$df" = "" ] 
  then
    echo "$(tput setaf 6) [PASSED] $(tput setaf 0)"
  else
    echo "$(tput setaf 4) [FAILED] $(tput sgr0)"
    FAIL=1
  fi
done

  if (( $FAIL == 1 )) 
  then 
    exit 1
  fi

