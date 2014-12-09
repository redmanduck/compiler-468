LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Micro.g4

all: group compiler

group:
	@echo "ssabpisa"
tiny:
	g++ -o tiny tinyNew.C
tiny4:
	g++ -o tiny tiny4regs.C 
compiler:
	rm -rf build
	mkdir build
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o build $(ANTLR_SCRIPT)
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java build/*.java
single:
	java -cp lib/antlr.jar:classes/ Micro step6_tests/$(single).micro > output/$(single).T
clean:
	rm -rf classes build

.PHONY: all group compiler clean
