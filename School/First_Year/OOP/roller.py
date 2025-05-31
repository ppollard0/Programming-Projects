# roller.py
# Python program to test the Button, MSDie, SixSidedDie
# and DieView classes

from random import randrange
from graphics import GraphWin, Point
from button import Button
from dieview import DieView
# also import SixSidedDie

WINDOW_BACKGROUND = 'green2'

def main():
    # Create the window
    win = GraphWin("Dice Roller")
    win.setCoords(0,0,10,10)
    win.setBackground(WINDOW_BACKGROUND)

    # Draw the widgets
    die1 = SixSidedDie()
    die2 = SixSidedDie()
    dieView1 = DieView(die1, win, Point(3,7), 2)
    dieView2 = DieView(die2, win, Point(7,7), 2)
    rollButton = Button(win, Point(5,4.5), 6, 1,"Roll Dice")
    rollButton.activate()
    quitButton = Button(win, Point(5,1),2, 1, "Quit")

    # Event loop
    pt = win.getMouse()
    while not quitButton.clicked(pt):
        if rollButton.clicked(pt):
            die1.roll()
            dieView1.update()
            die2.roll()
            dieView2.update()
            quitButton.activate()
        pt=win.getMouse()

    # all done
    win.close()


if __name__ == '__main__':
    main()
