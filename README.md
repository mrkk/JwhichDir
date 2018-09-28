"# JwhichDir" 
Helps you find a class in a directory full of jars!
You can drop these java files in C:\ in windows
Note that JWhichDir.java has the classpath to itself hard-coded, you can change it to a value you like

Then javac *.java to compile

set current CLASSPATH=Whatever;C:\

then run
java JWhichDir %1 %2

e.g.
java JWhichDir some.ClassName .

where . is current dir that has tons of jars

For verbose mode

java -Dverbose JWhichDir some.ClassName .
