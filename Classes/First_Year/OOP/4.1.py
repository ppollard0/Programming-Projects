from random import *

class MSDie:

    def __init__(self,sides):
        self.sides = sides
        self.value = 1

    def roll(self):
        self.value = randrange(1,self.sides + 1)

    def getValue(self):
        return(self.value)

    def setValue(self,value):
        self.value = value

def testDie():
    d = MSDie(6)
    for i in range(20):
        d.roll()
        print(d.getValue())

def testOutcome(n):
    d = MSDie(5)
    outcome = [0,0,0,0,0,0]
    for i in range(n):
        d.roll()
        index = d.getValue() - 1
        outcome[index] = outcome[index]+1
        print(outcome)
