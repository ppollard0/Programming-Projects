from graphics import *
from imagelib import *

WINDOW_HEIGHT = 650
WINDOW_WIDTH = 1200

DESCRIPTION_PANEL_LEFT = 0
DESCRIPTION_PANEL_BOTTOM = WINDOW_HEIGHT//4
DESCRIPTION_PANEL_RIGHT = WINDOW_WIDTH
DESCRIPTION_PANEL_TOP = 0
DESCRIPTION_PANEL_P1 = Point(DESCRIPTION_PANEL_LEFT,DESCRIPTION_PANEL_TOP)
DESCRIPTION_PANEL_P2 = Point(DESCRIPTION_PANEL_RIGHT,DESCRIPTION_PANEL_BOTTOM)
DESCRIPTION_PANEL_BACKGROUND = "white smoke"

INPUT_PANEL_LEFT = 2*WINDOW_WIDTH//3
INPUT_PANEL_BOTTOM = WINDOW_HEIGHT//2
INPUT_PANEL_RIGHT = WINDOW_WIDTH
INPUT_PANEL_TOP = DESCRIPTION_PANEL_BOTTOM
INPUT_PANEL_P1 = Point(INPUT_PANEL_LEFT,INPUT_PANEL_TOP)
INPUT_PANEL_P2 = Point(INPUT_PANEL_RIGHT,INPUT_PANEL_BOTTOM)
INPUT_PANEL_BACKGROUND = "white smoke"

TEXT_SIZE = 24
TEXT_MARGIN_Y = 40
TEXT_MARGIN_X = 20

INPUT_WIDTH = 16
INPUT_HEIGHT = 20
PROMPT_SIZE = 18

PIC1_P1 = Point(0,WINDOW_HEIGHT//4)
PIC1_P2 = Point(2*WINDOW_WIDTH//3,WINDOW_HEIGHT)

PIC2_P1 = Point(2*WINDOW_WIDTH//3,WINDOW_HEIGHT//2)
PIC2_P2 = Point(WINDOW_WIDTH,WINDOW_HEIGHT)

class Panel(Rectangle):
    _contents = []
    def __init__(self,win,p1,p2,background = None):
        super().__init__(p1,p2)
        self.window = win
        self.width = int(abs(p2.getX() - p1.getX()))
        self.height = int(abs(p2.getY() - p1.getY()))
        self.background = background
        if background == None:
            self.setBackground(background)
        self.draw(win)

    def getWidth(self):
        return(self.width)

    def getHeight(self):
        return(self.height)

    def setBackground(self,background):
        self.setFill(background)
        self.background = background

    def getBackground(self):
        return(self.background)

    def containsPoints(self,p):
        x = p.getX()
        y = p.getY()
        if self.getP1().getX()<= x <= self.getP2().getX():
            if self.getP1().getY()<= y <= self.getP2().getY():
                return(True)
        return(False)

class TextPanel(Panel):
    
    def makeText(self,string):
        anchor = Point(self.getCenter().getX(),
                       self.getCenter().getY())
        self.text = Text(anchor,string)
        self.text.setSize(TEXT_SIZE)
        self.text.draw(self.window)

    def setText(self,text):
        self.text.setText(text)

class GameWindow(GraphWin):

    def __init__(self):
        super().__init__("Welcome to Adventure!",WINDOW_WIDTH,WINDOW_HEIGHT)
        self.description = TextPanel(self,DESCRIPTION_PANEL_P1,
                                     DESCRIPTION_PANEL_P2,
                                     DESCRIPTION_PANEL_BACKGROUND)
        self.description.makeText("Welcome to Adventure!")
        self.inputPanel = InputPanel(self,INPUT_PANEL_P1,INPUT_PANEL_P2)
        picPath = r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\startingRoom.png"
        picPath2 = r"C:\Users\PaulA\OneDrive\Documents\Drury\D - CS Class\OOP\Rooms\Base Map.png"
        self.pic1 = PicturePanel(self,PIC1_P1,PIC1_P2)
        self.pic1.loadPicture(picPath)
        self.pic2 = PicturePanel(self,PIC2_P1,PIC2_P2)
        self.pic2.loadPicture2(picPath2)

    def printDescription(self,message):
        self.description.setText(message)

    def printPrompt(self, message):
        self.inputPanel.setPrompt(message)

    def getWord(self):
        return(self.inputPanel.getWord(self))

    def showPicture(self,picPath):
        self.pic1.loadPicture(picPath)
        
    def showPicture2(self,picPath2):
        self.pic2.loadPicture2(picPath2)

def guiTest():
    win = GameWindow()
    win.printDescription("Testing, testing.")
    win.printPrompt("Which way?")
    win.getKey()
    myword = win.getWord()
    print(myword)
    win.getKey

    win.close()

class InputPanel(TextPanel):

    def __init__(self,win,p1,p2,background = "white"):
        super().__init__(win,p1,p2,background);
        self.input = Entry(Point(self.getCenter().getX(),
                                 INPUT_PANEL_BOTTOM - TEXT_MARGIN_Y),
                           INPUT_WIDTH)
        self.makeText("Type 'playGame()' to start")
        self.text.setSize(PROMPT_SIZE)
        self.text.move(0,self.getP1().getY()-self.getCenter().getY()
                       + TEXT_MARGIN_Y)
        self.input.setSize(TEXT_SIZE)
        self.input.setFill(INPUT_PANEL_BACKGROUND)
        self.input.draw(win)

    def setPrompt(self,mystring):
        self.setText(mystring)

    def getWord(self,win):
        """Collect characters in word until return is pressed."""
        word = ""
        lastKey = ""
        while True:
            thisKey = win.checkKey()
            if thisKey != lastKey:
                if thisKey.lower() == "return":
                    self.input.setText("")
                    return word
                elif thisKey.lower() == "backspace":
                    word = word[-1]
                    self.input.setText(word)
                else:
                    word += thisKey
                    self.input.setText(word)
                    

class PicturePanel(Panel):

    def __init__(self,win,p1,p2,background = 'white'):
        super().__init__(win,p1,p2,background);
        self.pic = Image(self.getCenter(),0,0)
        self.pic.draw(win)
            
    def loadPicture(self,pic):
        self.pic.undraw()
        self.pic = Image(self.getCenter(),pic)
        self.pic.draw(self.window)

    def loadPicture2(self,pic):
        self.pic.undraw()
        self.pic = Image(self.getCenter(),pic)
        self.pic.draw(self.window)
