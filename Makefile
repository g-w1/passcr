all: SetterUpper.java
	javac SetterUpper.java
	javac Runner.java

setup: all
	$(RM) -r shortened
	mkdir shortened
	java SetterUpper

run: setup
	java Runner

clean:
	$(RM) *.class
	$(RM) -rf shortened
