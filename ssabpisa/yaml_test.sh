#!/bin/bash
#
# Compile the compiler and test the result
#
# This program is part of an assignment for ECE468 at Purdue University, IN.
# Copying, modifying or reusing this program may result in disciplinary actions.

# Copyright (C) 2014-2075 S. Sabpisal

# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.

# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

TINY='tiny'
 if [[ $* == *-c* ]]
then
rm $TINY
make tiny4 2> /dev/null
fi
tc="${@: -1}*"
if [[ $* == *-c* ]]
then
  #compile the compiler first
  make clean && make 2> /dev/null
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
  echo "Output " $test >> transcript  
  echo "Solution " $gold >> transcript
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
