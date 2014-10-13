LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler

group:
	@echo "ssabpisa"
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java
clean:
	rm -rf classes build

tloop:
	#rm -rf *.out 
	java -cp lib/antlr.jar:classes/ Micro loop.micro > loop.out
	diff -b loop.out loop.key

tfibonacci:
	#rm -rf *.out 
	java -cp lib/antlr.jar:classes/ Micro fibonacci.micro > fibonacci.out
	diff -b fibonacci.out fibonacci.key

tloopbreak:
	#rm -rf *.out 
	java -cp lib/antlr.jar:classes/ Micro loopbreak.micro > loopbreak.out
	diff -b loopbreak.out loopbreak.key 

tnested:
	#rm -rf *.out 
	java -cp lib/antlr.jar:classes/ Micro nested.micro > nested.out
	diff -b nested.out nested.key

tsqrt:
	#rm -rf *.out
	java -cp lib/antlr.jar:classes/ Micro sqrt.micro > sqrt.out
	diff -b sqrt.out sqrt.key

testall:
	make tloop 
	make tfibonacci 
	make tnested
	make tsqrt 
diffall:
	diff -b loop.out loop.key
	diff -b fibonacci.out fibonacci.key
	diff -b nested.out nested.key
	diff -b sqrt.out sqrt.key	

.PHONY: all group compiler clean
