default: Driver.java AdventureMap.java Room.java Weapons.java
	javac Driver.java AdventureMap.java Room.java Weapons.java

run: Driver.class AdventureMap.class Room.class Weapons.class
	java Driver

clean:
	rm -f *.class