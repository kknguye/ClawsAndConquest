default: Driver.java AdventureMap.java Room.java Item.java
	javac Driver.java AdventureMap.java Room.java Item.java

run: Driver.class AdventureMap.class Room.class Item.class
	java Driver

clean:
	rm -f *.class