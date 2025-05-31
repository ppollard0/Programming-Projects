from gameUI import *
import time
import random

PAUSE_LENGTH = 3
win = GameWindow()

win.printDescription("Maze Escape")

time.sleep(PAUSE_LENGTH)

def showIntroduction(win):
    introString = "Welcome to the maze!"
    introString += "\nI'm sure you read the waiver before you signed it."
    introString += "\n*unintelligible* Wait, What? We didn't get this guy through the signup?"
    introString += "\nOh well, Listen, all you need to do is find the bright light at the end"
    win.printDescription(introString)

class Room:

    def __init__(self,roomName,description,doorList, picture,picture2):
        self.roomName = roomName
        self.description = description
        self.doorList = doorList
        self.isKeyHere = False
        self.picture = picture
        self.picture2 = picture2

    def describeDoors(self):
        if self.roomName == "a hall":
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
    startingRoom = Room("the starting room", "It's almost pitch black in here.", ["north","east","west"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\startingRoom.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\startingRoomMap.png")
    hall = Room("a hall", "It's a long dark hall with a door at the end.", ["north"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\hall.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\HallMap.png")
    hall2 = Room("a hall", "The hall is dark with a door at thte end.",["south"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\hall2.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\Hall2Map.png")
    hall3 = Room("a hall", "The hallway is large with a cracked door emitting a bright light.",["east"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\hall3.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\Hall3Map.png")
    exit1 = Room("a bright room", "The room is small with just a short object and UV lights at the top of the walls0.", ["south"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\exit1.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\Exit1Map.png")
    exit2 = Room("a bright room", "The room is large and empthy with bright UV lights on the sides.", ["north"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\exit2.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\Exit2Map.png")
    exit3 = Room("a bright room", "The room is tall and round with bright UV lights covering the walls.", ["west"], r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\exit3.png", r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\Exit3Map.png")

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
    showIntroduction(win)
    win.printPrompt("Which way?")
    direction = win.getWord()

    while not done:
        win.showPicture2(location.picture2)
        win.showPicture(location.picture)
        win.printDescription(location.describeRoom() + "\nSeems like you need to keep looking")
        win.printPrompt("Which way?")
        direction = win.getWord()
        location = myhouse.pickRoom(location,direction)
        win.showPicture(location.picture)

        if location == keylocation:
            win.printDescription("You've found the way out")
            done = True
