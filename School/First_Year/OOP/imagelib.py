# imagelib.py
# Image library for CSCI 251
# Authors: Chris Branton and Carol Browning
# Created 2019-10-01
# Utility functions to work with Zelle graphics library

# Change log October 3 Carol Browning added imageExplorer
# with ability to display pixel information on click

from graphics import *
from tkinter.filedialog import askopenfilename
from math import *


# Create and return a blank Image with optional background color
def makeEmptyPicture(width, height, background=None):
    blankImage = Image(Point(width//2,height//2),width, height)
    if background != None:
        for x in range(width):
            for y in range(height):
                blankImage.setPixel(x,y, background)
    return blankImage

# Create and return an Image from a file
def makePicture(fileName):
    img = Image(Point(0,0), fileName)
    img.move(img.getWidth()/2, img.getHeight()/2)
    return img

# save an Image to the given file
def savePicture(image, fileName):
    image.save(fileName)

# another name for the same savePicture function
def writePictureTo(image, fileName):
    image.save(fileName)
    
# Use the file dialog to choose a file
# Graphics library will open .gif and .png
def pickAFile():
    fileName = askopenfilename()
    return fileName

# Open an image in a window that allows the user to 
# click any pixel in the image and obtain the x coordinate,
# the  y coordinate, and the color rgb values.
def imageExplorer(fileName):
    #Create the picture and set picture and window width and height variables
    pic = makePicture(fileName)
    explore(pic)

# Draw an image in a window that allows the user to 
# click any pixel in the image and obtain the x coordinate,
# the  y coordinate, and the color rgb values.   
def explore(pic):

    #Define some window constants
    MENU_HT = 50 #height of menu bar
    MIN_W = 560 #minimum width of window = width of explore menu
    OFFSET = 25 #vertical offset for display menu

    # Set picture and window width and height variables
    picW = pic.getWidth()
    winW = max(picW,MIN_W)
    picH = pic.getHeight()
    winH = picH + MENU_HT
    boxY = winH - OFFSET #y value of bottom edge of a box
    
    #Create the explore window and draw the picture
    exploreWindow = GraphWin ("Explore", winW, winH)
    pic.draw(exploreWindow)

    #Draw the explore menu
    textX = Text(Point(25,boxY),"x:")
    xInbox = Entry(Point(65,boxY),5)
    textY = Text(Point(120,boxY),"y:")
    yInbox =Entry(Point(160,boxY),5)
    colorRect = Rectangle(Point(220,picH+10),Point(250,winH-10))
    colorRect.setFill("light blue")
    textRed = Text(Point(280,boxY),"red:")
    redBox = Text(Point(310,boxY),"53")
    textGreen = Text(Point(360,boxY),"green:")
    greenBox = Text(Point(400,boxY),"81")
    textBlue = Text(Point(440,boxY),"blue:")
    blueBox =Text(Point(475,boxY),"92")
    doneBox = Rectangle(Point(500,picH+10),Point(550,winH - 10))
    doneText = Text(Point(525,boxY),"DONE")
    menuElements = [textX,xInbox,textY,yInbox,colorRect,textRed,redBox,textGreen,greenBox,textBlue,blueBox,doneBox,doneText]
    for thing in menuElements:
        thing.draw(exploreWindow)

    #Enter main event loop
    done = False
    while not done:
        
        #Process mouse clicks.  A click on the picture changes values of x, y, color, r, g, and b to the values of clicked pixel
        clickPoint = exploreWindow.checkMouse()
        if type(clickPoint) == Point:
            x = int(clickPoint.getX())
            y = int(clickPoint.getY())
            if x < picW and y < picH:
                xInbox.setText(x)
                yInbox.setText(y)
                red,green,blue = pic.getPixel(x,y)
                colorRect.setFill(color_rgb(red,green,blue))
                redBox.setText(red)
                greenBox.setText(green)
                blueBox.setText(blue)
            else:
                if x > 500 and x < 550 and y > picH + 10 and y < winH - 10:
                    exploreWindow.close()
                    done = True

        #Process changes to x and y values.  Allow user to set x and y and see where mouse is and color there
    
# some sample code
def main():
    pic = makePicture(pickAFile())
    win = GraphWin ("Images", pic.getWidth(), pic.getHeight())
    pic.draw(win)
    bi = makeEmptyPicture(100, 100, 'red')
    bi.move (300,300)
    bi.draw (win)
    imageExplorer(pickAFile())
    
if __name__ == '__main__':
    main()

def colorDist(list1, list2):
    r1,g1,b1 = list1
    r2,g2,b2 = list2
    return(((r1 - r2)**2 + (g1 - g2)**2 + (b1 - b2)**2))**0.5

def chromaKey(person,background,mycolor,tolerance):
    width = background.getWidth()
    height = background.getHeight()
    newpic = Image(Point(width//2, height//2),width, height)
    for col in range(width):
        for row in range(height):
            personpixelcolor = person.getPixel(col,row)
            if colorDist(personpixelcolor,mycolor)<tolerance:
                r,g,b = background.getPixel(col,row)
            else:
                r,g,b = personpixelcolor
            newpic.setPixel(col,row,color_rgb(r,g,b))
    return(newpic)

def explorePic():
    pic = makePicture(pickAFile())
    explore(pic)

def copyIn(smallPic,bigPic,x,y):
    for col in range(smallPic.getWidth()):
        for row in range(smallPic.getHeight()):
            r,g,b = smallPic.getPixel(col,row)
            bigPic.setPixel(col + x, row + y, color_rgb(r,g,b))
    return(bigPic)
            
def copyInShow(smallPic,bigPic,x,y):
    for col in range(smallPic.getWidth()):
        for row in range(smallPic.getHeight()):
            r,g,b = smallPic.getPixel(col,row)
            bigPic.setPixel(col + x, row + y, color_rgb(r,g,b))
    explore(bigPic)

def makeBorder(pic,bordercolor,borderWidth):
    w = pic.getWidth() + 2*borderWidth
    h = pic.getHeight() + 2*borderWidth
    canvas = makeEmptyPicture(w, h, bordercolor)
    copyIn(pic, canvas, borderWidth, borderWidth)
    return(canvas)

def extract(pic,startX,startY,width,height):
    new = makeEmptyPicture(width,height)
    for col in range(width):
        for row in range(height):
            r,g,b = pic.getPixel(startX + col,startY + row)
            new.setPixel(col,row,color_rgb(r,g,b))
    return(new)

def flipLR(pic):
    w = pic.getWidth()
    h = pic.getHeight()
    for col in range(0,w//2):
        for row in range(0,h):
            r,g,b = pic.getPixel(col,row)
            pic.setPixel(w - 1 - col,row,color_rgb(r,g,b))
    return(pic)

def runFlipLR(pic):
    newPic = flipLR(pic)
    return(newPic)

def flipTopBottom(pic):
    w = pic.getWidth()
    h = pic.getHeight()
    for col in range(0,w):
        for row in range(0,h//2):
            r,g,b = pic.getPixel(col,row)
            pic.setPixel(col,h - 1 - row,color_rgb(r,g,b))
    return(pic)

def grayscale(pic):
    for col in range(pic.getWidth()):
        for row in range(pic.getHeight()):
            r,g,b = pic.getPixel(col,row)
            avg = (r+g+b)//3
            pic.setPixel(col,row,color_rgb(avg,avg,avg))
    return(pic)

def bluepic(pic,x,y,newblue):
    r,g,b = pic.getPixel(x,y)
    pic.setPixel(x,y,color_rgb(r,g,newblue))

def redpic(pic,x,y,newred):
    r,g,b = pic.getPixel(x,y)
    pic.setPixel(x,y,color_rgb(newred,g,b))

def greenpic(pic,x,y,newgreen):
    r,g,b = pic.getPixel(x,y)
    pic.setPixel(x,y,color_rgb(r,newgreen,b))

def blueandgreenpic(pic,x,y,newgreen,newblue):
    r,g,b = pic.getPixel(x,y)
    pic.setPixel(x,y,color_rgb(r,newgreen,newblue))

def swapBAndG(pic):
    for col in range(pic.getWidth()):
        for row in range(pic.getHeight()):
            r,g,b = pic.getPixel(col,row)
            z = g
            x = b
            blueandgreenpic(pic,col,row,x,z)
    return(pic)

def getred(pic,x,y):
    return(pic.getPixel(x,y)[0])

def getgreen(pic,x,y):
    return(pic.getPixel(x,y)[1])

def getblue(pic,x,y):
    return(pic.getPixel(x,y)[2])

def maxRedLeftBlueRight(pic):
    for col in range(pic.getWidth()//2):
        for row in range(pic.getHeight()):
            redpic(pic,col,row,255)
    for col in range(pic.getWidth()//2,pic.getWidth()):
        for row in range(pic.getHeight()):
            bluepic(pic,col,row,255)
    return(pic)

def shrink(n,pic):
    newWidth = pic.getWidth()//n
    newHeight = pic.getHeight()//n
    smallPic = makeEmptyPicture(newWidth,newHeight)
    for col in range(0,newWidth):
        for row in range(0,newHeight):
            r,g,b = pic.getPixel(col*n,row*n)
            smallPic.setPixel(col,row,color_rgb(r,g,b))
    return(smallPic)

def stretch(n,pic):
    newWidth = pic.getWidth()*n
    newHeight = pic.getHeight()*n
    bigPic = makeEmptyPicture(newWidth,newHeight)
    for col in range(0,newWidth):
        for row in range(0,newHeight):
            r,g,b = pic.getPixel(col//n,row//n)
            bigPic.setPixel(col,row,color_rgb(r,g,b))
    return(bigPic)

def exploreFunction():
    pic = makePicture(pickAFile())
    explore(pic)

