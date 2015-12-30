compile:
	mvn compile

test:
	mvn test

clean:
	rm *~
	rm src/main/java/id/huang/yauhsien/*~
	rm res/*/*~
	mvn clean
