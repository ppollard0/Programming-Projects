from gameUI import *
import time
import random

PAUSE_LENGTH = 3

print("Maze Escape")



def showIntroduction():
    introString = "Welcome to the maze!"
    introString += "\nI'm sure you read the waiver before you signed it."
    introString += "\n*unintelligible* Wait, What? We didn't get this guy through the signup?"
    introString += "\nOh well, Listen, all you need to do is find the bright light at the end"
    print(introString)

class Room:

    def __init__(self,roomName,description,doorList):
        self.roomName = roomName
        self.description = description
        self.doorList = doorList
        self.isKeyHere = False

    def describeDoors(self):
        if self.roomName == "a long hall":
            doorDescription = "\nThere is a door to the "

        elif self.roomName == "a wide hall":
            doorDescription = "\nThere is a door to the "

        elif self.roomName == "a short hall":
            doorDescription = "\nThere is a door to the "
            
        else:
            doorDescription = "\nThere are paths to the "
        for way in self.doorList:
            doorDescription = doorDescription + way + ", "
        return doorDescription

    def setIsKeyHere(self,value):
        self.isKeyHere = value

    def describeRoom(self):
        return "You're in " + self.roomName + ". " + self.description + self.describeDoors()

    def getRoomName(self):
        return self.roomName

    def whatRoom(self):
        return (self.roomName)

class House:

    def __init__(self,roomList,doorList,exitList):
        self.roomList = roomList
        self.doorList = doorList
        self.exitList = exitList

    def pickRoom(self,location,direction):
        for door in self.doorList:
            if door [0] == location and door[1] == direction:
                return door[2]
            elif door[2] == location and door[1] == opposite(direction):
                return door[0]
        return location

    def getRandomRoom(self):
        return random.choice(self.exitList)
    
    def whatRoom(self):
        if self.roomName == exit1:
            print("You've found the way out")

        elif self.roomName == exit2:
            print("You've found the way out")
            
        elif self.roomName == exit2:
            print("You've found the way out")

def opposite(direction):
    if direction == "north":
        return "south"
    elif direction == "south":
        return "north"
    elif direction == "east":
        return "west"
    elif direction == "west":
        return "east"

def makeHouse():
    startingRoom = Room("the starting room", "It's almost pitch black in here.", ["north","east","west"])
    hall = Room("a longhall", "It's a long dark hall with a door at the end.", ["north and south"])
    hall2 = Room("a wide hall", "The hall is dark with a door at thte end.",["north and south"])
    hall3 = Room("a short hall", "The hallway is large with a cracked door emitting a bright light.",["east"])
    exit1 = Room("a pretty bright room", "The room is small with just a short object and UV lights at the top of the walls.", ["south"])
    exit2 = Room("a very bright room", "The room is large and empthy with bright UV lights on the sides.", ["north and south"])
    exit3 = Room("a  extremely bright room", "The room is tall and round with bright UV lights covering the walls.", ["west"])

    roomList = [startingRoom,hall,hall2,hall3,exit1,exit2,exit3]
    exitList = [exit1,exit2,exit3]
    doorList = [(startingRoom, "north",hall),(startingRoom, "west",hall2),(startingRoom, "east",hall3),(hall, "south", startingRoom),(hall2, "east", startingRoom),(hall3, "west", startingRoom),(hall, "north", exit1),(hall2, "south", exit2),(hall3, "east", exit3),(exit1, "south", hall),(exit2, "north", hall2),(exit3, "west", hall3)]
    myhouse = House(roomList,doorList,exitList)

    return(myhouse,startingRoom)

def playGame():
    myhouse,location = makeHouse()
    keylocation = myhouse.getRandomRoom()
    keylocation.setIsKeyHere(True)
    done = False
    showIntroduction()
    direction = input("Which way? ")

    while not done:
        
        print(location.describeRoom() + "\nSeems like you need to keep looking")
        direction = input("Which way? ")
        location = myhouse.pickRoom(location,direction)
        escape = keylocation.whatRoom()

        if location == keylocation:
            keylocation.whatRoom()
            if escape == "a pretty bright room":
                print("You've found the way out yes")

            elif escape == "a very bright room":
                print("You've found the way out no")
            
            elif escape == "a extremely bright room":
                print("You've found the way out good")
            else:
                print("what are you doing?")
            done = True
