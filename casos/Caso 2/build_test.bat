set /p case= Insert case of study:
javac -cp .;CLASSPATH;../../bin caso2_%case%.java
java -cp .;CLASSPATH;../../bin caso2_%case%
PAUSE