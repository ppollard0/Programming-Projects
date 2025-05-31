"""
Adapted from Python Programming 3rd Edition by John Zelle
"""
from graphics import *

DIE_BACKGROUND='white'
DIE_FOREGROUND='black'

class DieView:
    """ DieView is a widget that displays a graphical representation of a
        standard six-sided die """

    def __init__(self, mydie, win, center, size):
        """ Create a graphical representation of a die.
        For example: d1 = DieView(myWin, Point(40,50), 20)
        creates a die centered at x=40, y=50, with a side
        length of 20."""

        # define some properties
        self.win = win
        self.mydie = mydie
        self.background = DIE_BACKGROUND
        self.foreground = DIE_FOREGROUND
        self.pipSize = 0.1*size
        halfSize = size /2.0
        offset = 0.6 * halfSize

        # create the face
        cx, cy = center.getX(), center.getY()
        p1 = Point(cx-halfSize, cy-halfSize)
        p2 = Point(cx+halfSize, cy+halfSize)
        rect = Rectangle(p1,p2)
        rect.setFill(self.background)
        rect.draw(self.win)

        # create circles for the pips
        self.pip1 = self.__makePip(cx-offset, cy-offset)
        self.pip2 = self.__makePip(cx-offset, cy)
        self.pip3 = self.__makePip(cx-offset, cy+offset)
        self.pip4 = self.__makePip(cx, cy)
        # TODO: make pip5, pip6 and pip7

        # set the initial value
        self.value = self.mydie.getValue()

    def __makePip(self,x,y):
        "Helper method to draw a dot on a die"
        pip = Circle(Point(x,y),self.pipSize)
        pip.setFill(self.background)
        pip.setOutline(self.background)
        pip.draw(self.win)
        return pip

    def setValue(self,value):
        "Set this die to display value"
        self.pip1.setFill(self.background)
        self.pip2.setFill(self.background)
        self.pip3.setFill(self.background)
        self.pip4.setFill(self.background)
        self.pip5.setFill(self.background)
        self.pip6.setFill(self.background)
        self.pip7.setFill(self.background)

        # display correct pips
        if value == 1:
            self.pip4.setFill(self.foreground)
            
        # TODO: set the fill values appropriately
        elif value == 2:
        elif value == 3:
        elif value == 4:
        elif value == 5:
        else: #value is 6
            self.pip1.setFill(self.foreground)
            self.pip2.setFill(self.foreground)
            self.pip3.setFill(self.foreground)
            self.pip5.setFill(self.foreground)
            self.pip6.setFill(self.foreground)
            self.pip7.setFill(self.foreground)
            
    def update(self):
        value = self.mydie.getValue()
        self.setValue(value)
