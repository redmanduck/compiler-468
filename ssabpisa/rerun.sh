#!/bin/bash
make
java -cp lib/antlr.jar:classes/ Micro $1 
