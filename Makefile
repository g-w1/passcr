
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Bank.java \
	Runner.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

