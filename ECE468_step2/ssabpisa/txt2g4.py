#!/usr/bin/python
#
# magically convert the txt file provided into Context free grammar
import sys

if len(sys.argv) == 1:
	print("Usage: txt2g4.py grammar.txt")
	exit(1)

m = open(sys.argv[1], "r");
g = []
for line in m:
	line = line.strip();
	gline = line.replace("->",":")
	temp = gline.split(":");
	if ("/*" in line or "//" in line or len(temp) == 1):
		g.append(line)
		continue
	temp[0] = temp[0].strip();
	temp[1] = "(" + temp[1] + ")";
	gline = " : ".join(temp);
	gline = gline.replace(";", " SEMI "); 
	gline = gline.replace(":=", " COLEQ ");
	g.append(gline + ";")	
print "\n".join(g)
