PKG=edu.odu.cs.cs350
DIR=src/edu/odu/cs/cs350
CLASSPATH=-cp ".:../lib/*"
RUNPATH=-cp "src/:lib/*"

.PRECIOUS: %.java testReport.txt

all: $(DIR)/Magazine.class $(DIR)/TestMagazine.class $(DIR)/Article.class


src/%.class: src/%.java
	cd src; javac $(CLASSPATH)  -g $*.java

test: tests.found tests.passed testReport.txt

tests.found: testReport.txt
	grep "tests found" $< | sed -e 's/[^0-9]//g' | tee $@

tests.passed: testReport.txt
	grep "tests successful" $< | sed -e 's/[^0-9]//g' | tee $@

testReport.txt: all
	java $(RUNPATH) org.junit.platform.console.ConsoleLauncher \
	  -c $(PKG).TestMagazine 2>&1 | grep -v "was not tested" | tee $@

bugTests: bugtests.passed

bugtests.passed: bugtestReport.txt
	grep "tests successful" $< | sed -e 's/[^0-9]//g' | tee $@

bugtestReport.txt: all
	java $(RUNPATH) org.junit.platform.console.ConsoleLauncher \
	  -c $(PKG).TestMagazine 2>&1 | tee $@


clean:
	rm -rf test*.txt
	find src -name '*.class' | xargs rm
