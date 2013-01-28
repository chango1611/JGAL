set /p case= Insert case of study:
javac -cp .;CLASSPATH;../bin caso%case%.java
java -cp .;CLASSPATH;../bin caso%case%
PAUSE