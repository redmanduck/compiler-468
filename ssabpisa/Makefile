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
comb:
	javac -cp $(LIB_ANTLR) src/*.java build/*.java
	java -cp lib/antlr.jar:classes/ Micro input/test_combination.micro | tail -n +2
tmult:
	javac -cp $(LIB_ANTLR) src/*.java build/*.java
	java -cp lib/antlr.jar:classes/ Micro input/test_mult.micro
expr:
	javac -cp $(LIB_ANTLR) src/*.java build/*.java
	java -cp lib/antlr.jar:classes/ Micro input/test_expr.micro | tail -n +2
clean:
	rm -rf classes build

.PHONY: all group compiler clean
