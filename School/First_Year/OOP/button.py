"""
Button class to help build a graphical user interface
Adapted from Python Programming 3rd edition by John Zelle

Button class creates a rectangular button with a text label.
The Button responds to mouse clicks
"""
from graphics import *

BUTTON_FILL = 'lightgray'
BUTTON_ACTIVE_FILL = 'black'
TEXT_INACTIVE_COLOR= 'darkgray'
TEXT_SIZE = 11

# Button class
class Button:
    """ Initialized with a GraphWin window, a center Point, width and height
        in pixels, and a label string. """

    def __init__(self, win, center, width, height, label):
        w,h = width/2, height/2
        x,y = center.getX(), center.getY()
        self.xmax, self.xmin = x+w, x-w
        self.ymax, self.ymin = y+h, y-h
        p1 = Point(self.xmin, self.ymin)
        p2 = Point(self.xmax, self.ymax)
        self.rect = Rectangle(p1, p2)
        self.rect.setFill(BUTTON_FILL)
        self.rect.draw(win)
        self.label = Text(center, label)
        self.label.setSize(TEXT_SIZE)
        self.label.draw(win)
        self.deactivate()

    def clicked(self, p):
        """ Returns true if p is inside rectangle"""
        return (self.active and
                self.xmin <= p.getX() <= self.xmax and
                self.ymin <= p.getY() <= self.ymax)

    def getLabel(self):
        """Returns the label string of the button """
        return self.label.getText()

    def activate(self):
        """Sets the button to active"""
        self.label.setFill(BUTTON_ACTIVE_FILL)
        self.rect.setWidth(2)
        self.active = True

    def deactivate(self):
        "Sets the button to inactive"
        self.label.setFill(TEXT_INACTIVE_COLOR)
        self.rect.setWidth(1)
        self.active = False
