SRC_SCALA_PATH=./src/main/scala

run_gameless:
	scalac $(SRC_SCALA_PATH)/logic/*.scala
	scala $(SRC_SCALA_PATH)/Main.scala

run_game:
	sbt compile fastOptJS indigoRun

clean:
	rm -rf $(SRC_SCALA_PATH)/logic/*.class
