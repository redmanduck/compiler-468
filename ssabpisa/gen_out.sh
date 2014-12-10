tc=$1
dirs=$(ls $tc/*.micro)
for n in ${dirs}
do
	f=$(echo $n | cut -d'/' -f2 | cut -d'.' -f1)
	java -jar step7.jar $tc/$f.micro > $tc/$f.out
done
