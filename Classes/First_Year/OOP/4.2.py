from random import *
from graphics import *


class MSDie:
    def __init__(self,sides):
        self.sides = sides
        self.value = 1

    def roll(self):
        self.value = randrange(1,self.sides + 1)

    def getValue(self):
        return self.value

    def setValue(self,value):
        self.value = value

def test():
    d = MSDie(6)
    d.roll()
    print(d.getValue())
    d.setValue(15)
    print(d.getValue())

def setValueTest():
    d = MSDie(6)
    for myinput in [5,3.45,"ahoy there!",True,False,10,-10,4]:
        d.setValue(myinput)
        print("Given",myinput,", the value is ",d.getValue())

def setValue(self,value):
    if isinstance(value,int):
        if value > self.sides:
            value = self.sides
        elif value < 1:
            value = 1
        self.value = value
    else:
        print("Input to set value is not an interger")

class SixSidedDie(MSDie):
    occurrences = [0]*6

    def __init__(self):
        super().__init__(6)
        self.sides = 6
    
    def roll(self):
        self.value = randrange(1,self.sides+1)
        self.occurrences[self.value-1] += 1

    def getNumberOfTimes(self,number):
        if 1 <= number <= 6:
            return(self.occurrences[number-1])
        return(0)
        
def testSix():
    d = SixSidedDie()
    for rollNumber in range(100):
        d.roll()
    for rollValue in range(1,7):
        print(rollValue," was rolled ",d.getNumberOfTimes(rollValue)," times.")

def testDieView():
    testWin = GraphWin("die view",200,200)
    testWin.setBackground("lightblue")
    die1 = SixSidedDie()
    view1 = DieView(die1,testWin,Point(100,100),50)
    for i in range(1,7):
        die1.setValue(i)
        view1.update()
        time.sleep(2)

DIE_BACKGROUND = "white"
DIE_FOREGROUND = "black"

class DieView:

    def __init__(self, mydie, win, center, size):
        
        self.win = win
        self.mydie = mydie
        self.background = DIE_BACKGROUND
        self.foreground = DIE_FOREGROUND
        self.pipSize = 0.1*size
        halfSize = size/2.0
        offset = 0.6*halfSize

        cx,cy = center.getX(),center.getY()
        p1 = Point(cx-halfSize,cy-halfSize)
        p2 = Point(cx+halfSize,cy+halfSize)
        rect = Rectangle(p1,p2)
        rect.setFill(self.background)
        rect.draw(self.win)

        self.pip1 = self.__makePip(cx-offset,cy-offset)
        self.pip2 = self.__makePip(cx-offset, cy)
        self.pip3 = self.__makePip(cx-offset,cy+offset)
        self.pip4 = self.__makePip(cx,cy)
        self.pip5 = self.__makePip(cx+offset,cy-offset)
        self.pip6 = self.__makePip(cx+offset,cy)
        self.pip7 = self.__makePip(cx+offset,cy+offset)

        self.value = self.mydie.getValue()

    def __makePip(self,x,y):
        pip = Circle(Point(x,y),self.pipSize)
        pip.setFill(self.background)
        pip.setOutline(self.background)
        pip.draw(self.win)
        return (pip)

    def setValue(self,value):
        self.pip1.setFill(self.background)
        self.pip2.setFill(self.background)
        self.pip3.setFill(self.background)
        self.pip4.setFill(self.background)
        self.pip5.setFill(self.background)
        self.pip6.setFill(self.background)
        self.pip7.setFill(self.background)

        if value == 1:
            self.pip4.setFill(self.foreground)
        elif value == 2:
            self.pip1.setFill(self.foreground)
            self.pip7.setFill(self.foreground)
        elif value == 3:
            self.pip1.setFill(self.foreground)
            self.pip4.setFill(self.foreground)
            self.pip7.setFill(self.foreground)
        elif value == 4:
            self.pip1.setFill(self.foreground)
            self.pip3.setFill(self.foreground)
            self.pip5.setFill(self.foreground)
            self.pip7.setFill(self.foreground)
        elif value == 5:
            self.pip1.setFill(self.foreground)
            self.pip3.setFill(self.foreground)
            self.pip4.setFill(self.foreground)
            self.pip5.setFill(self.foreground)
            self.pip7.setFill(self.foreground)
        elif value == 6:
            self.pip1.setFill(self.foreground)
            self.pip2.setFill(self.foreground)
            self.pip3.setFill(self.foreground)
            self.pip5.setFill(self.foreground)
            self.pip6.setFill(self.foreground)
            self.pip7.setFill(self.foreground)

    def update(self):
        value = self.mydie.getValue()
        self.setValue(value)

def buttonTest():
    win = GraphWin("Button Test")
    b = Button(win,Point(100,100),100,50,"Click Me")
    b.activate()
    pt = win.getMouse()
    while not b.clicked(pt):
        pt = win.getMouse()
    win.close()

BUTTON_FILL = "lightgray"
BUTTON_ACTIVE_FILL = "black"
TEXT_INACTIVE_COLOR = "darkgray"
TEXT_SIZE = 11
  
class Button:

    def __init__(self, win, center, width, height, label):
        w,h = width/2, height/2
        x,y = center.getX(),center.getY()
        self.xmax, self.xmin = x+w, x-w
        self.ymax, self.ymin = y+h, y-h
        p1 = Point(self.xmin, self.ymin)
        p2 = Point(self.xmax, self.ymax)
        self.rect = Rectangle(p1,p2)
        self.rect.setFill(BUTTON_FILL)
        self.rect.draw(win)
        self.label = Text(center,label)
        self.label.setSize(TEXT_SIZE)
        self.label.draw(win)
        self.deactivate()
    def clicked(self, p):
        """ Returns true if p id inside rectangle"""
        return(self.active and
               self.xmin <= p.getX() <= self.xmax and
               self.ymin <= p.getY() <= self.ymax)
        
    def getLabel(self):
        """Returns the lavel string of the button """
        return(self.label.getText())

    def activate(self):
        """Sets the button to activate"""
        self.label.setFill(BUTTON_ACTIVE_FILL)
        self.rect.setWidth(2)
        self.activate = True

    def deactivate(self):
        "Sets the button to inactive"
        self.label.setFill(TEXT_INACTIVE_COLOR)
        self.rect.setWidth(1)
        self.active = False
