default: cpsc2150/extendedConnectX/models/*.java
	javac cpsc2150/extendedConnectX/models/*.java

run: cpsc2150/extendedConnectX/models/*.class
	java cpsc2150.extendedConnectX.models.GameScreen

clean:
	rm -f cpsc2150/extendedConnectX/models/*.class