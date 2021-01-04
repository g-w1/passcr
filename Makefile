all: SetterUpper.java
	javac SetterUpper.java

run: all
	java SetterUpper

clean:
	$(RM) *.class
