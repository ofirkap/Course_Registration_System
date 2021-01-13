CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude -lpthread -lboost_thread
LDFLAGS:=-lboost_system -lpthread -lboost_thread

all: BGRSClient
	g++ -o bin/BGRSClient bin/ConnectionHandler.o bin/BGRSClient.o bin/Reader.o bin/Writer.o $(LDFLAGS)

BGRSClient: bin/ConnectionHandler.o bin/BGRSClient.o bin/Reader.o bin/Writer.o
	
bin/ConnectionHandler.o: src/ConnectionHandler.cpp
	g++ $(CFLAGS) -o bin/ConnectionHandler.o src/ConnectionHandler.cpp

bin/BGRSClient.o: src/BGRSClient.cpp
	g++ $(CFLAGS) -o bin/BGRSClient.o src/BGRSClient.cpp

bin/Reader.o: src/Reader.cpp
	g++ $(CFLAGS) -o bin/Reader.o src/Reader.cpp

bin/Writer.o: src/Writer.cpp
	g++ $(CFLAGS) -o bin/Writer.o src/Writer.cpp
	
.PHONY: clean
clean:
	rm -f bin/*
