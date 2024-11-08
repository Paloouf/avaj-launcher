find . -name "*.class" -delete
find -name *.java > sources.txt
javac @sources.txt
java -cp . src.Simulator scenario.txt 