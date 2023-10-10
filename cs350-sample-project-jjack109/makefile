all: highway.jar

highway.jar: Highway.class Interval.class Ranges.class
	jar --create --file $@ *.class

%.class: %.java
	javac -g $<

clean:
	rm -f *.class *.jar *.out

TestData=$(wildcard testData/*.dat)
TestOutputs=$(TestData:testData/%.dat=%.out)

test: $(TestOutputs)

%.out: testData/%.dat highway.jar
	java -cp highway.jar Highway < testData/$*.dat > $*.out