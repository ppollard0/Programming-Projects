package mandelbrotapp;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mandelbrotprocessor.MandelbrotEngine;

import cs.csmath.complexnumber.ComplexNumber;
import csimage.CSImage;
import ui.MandelWin;

/**
 * MandelApp constructs an instance of the Mandelbrot set and displays 
 * it in a frame window.  The application requires the user to enter
 * a complex number and a side length. The complex number specifies the
 * southwest corner of the display rectangle in the complex plane.  The
 * length of the side specifies the width and height of the rectangle 
 * to be displayed.  
 * 
 * @author Scott Sigman
 * @version v1.0
 */
public class MandelApp {
    
    private MandelWin win = null;              // reference to the UI main window
    private final static int WIN_WIDTH = 800;  // width of the display image
    private final static int WIN_HEIGHT = 800; // height of the display image
    private CSImage mandelbrotSet = null;      // reference to image that contains the set
    private MandelbrotEngine mandelGenerator = null; // reference to the engine that generates
                                                     // the set

    //************** Constructors ***************
    /**
     * This constructs an instance of the application that contains
     * a image of the correct size.
     * 
     * * @param int The width of the image containing the Mandelbrot set to display.
     * * @param int The height of the image containing the Mandelbrot set to display.
     */
    public MandelApp(int width, int height) {
        mandelbrotSet = this.makeWhiteImage(width, height);
        win = new MandelWin(mandelbrotSet);
    }
    
    //********** Property Methods ***************
    /**
     * GetMandelbrotSet returns a reference to the image containing the
     * Mandelbrot set.
     */
    public CSImage getMandelbrotSet() {
        return mandelbrotSet;
    }

    /**
     * SetMandelbrotSet assigns the image containing a Mandelbrot
     * set to the application.
     * 
     * @param mandelbrotSet The image of the Mandlebrot set the application 
     *                      is to display.
     */
    public void setMandelbrotSet(CSImage mandelbrotSet) {
        this.mandelbrotSet = mandelbrotSet;
    }

    /**
     * GetWin returns a refernce to the application's main UI window.
     */
    public MandelWin getWin() {
        return win;
    }
    
    /**
     * GetMandelGenerator returns a reference to the engine that
     * is used to generate the Mandelbrot set image.
     */
    public MandelbrotEngine getMandelGenerator() {
        return mandelGenerator;
    }

    /**
     * SetMandelGenerator assigns the specified Mandelbrot set generation
     * engine as the generator used by the application.
     * 
     * @param mandelGenerator The engine to use to generate the Mandelbrot set.
     */
    public void setMandelGenerator(MandelbrotEngine mandelGenerator) {
        this.mandelGenerator = mandelGenerator;
    }

    
    //************** Utility Methods ************
    /**
     * This method ceates a white CSImage object that whose dimensions are
     * width x height.
     * 
     * @param width
     * @param height
     * @return  A white CSImage object.
     */
    private CSImage makeWhiteImage(int width, int height) {
        int [] [] [] whiteImage = new int[width][height][3];
        for (int x=0; x < width; x++)
            for(int y = 0; y < height; y++){
                whiteImage[x][y][0] = 255;
                whiteImage[x][y][1] = 255;
                whiteImage[x][y][2] = 255;
            }
                
        return CSImage.createImage(whiteImage);
    }
    
    
    /**
     * This method is the applications main method.  It queries the user for the
     * southwest corner of the region to display and the lenght of the side of 
     * the region in the complex plane to display.  In order to execute the 
     * program a command line parameter of one of the following string values
     * must be provided.  Legal parameter values ("Strings") are:
     *   - CONT
     *   - C8
     *   - C16
     *   - CONT4
     *   -   CONT5 
     *   - LOOKUP256
     *
     * @param args The color model to use to assign colors in the image.
     */
    public static void main(String[] args) {
 
        int colorModel = 0;  //Color Model to use in the application 
        
        // Get the color model to use from the command line
        if (args.length != 1) {
            // The default color model is a discrete 4 color model
            colorModel = MandelbrotEngine.CUST2;
        }
        else {
            if (args[0].equals("CONT"))
                colorModel = MandelbrotEngine.CONT;
            else if (args[0].equals("C8"))
                colorModel = MandelbrotEngine.C8;
            else if (args[0].equals("C16"))
                colorModel = MandelbrotEngine.C16;
            else if (args[0].equals("CONT3"))
                colorModel = MandelbrotEngine.CONT3;
            else if (args[0].equals("CONT4"))
                colorModel = MandelbrotEngine.CONT4;
            else if (args[0].equals("CONT5"))
                colorModel = MandelbrotEngine.CONT5;
            else if (args[0].equals("LOOKUP256"))
                colorModel = MandelbrotEngine.LOOKUP256;
            else if (args[0].equals("CUST"))
                colorModel = MandelbrotEngine.CUST;
            else if (args[0].equals("CUST2"))
                colorModel = MandelbrotEngine.CUST2;
        }
        
        // Print message for informational purpose
        System.out.println("Color Model is:" + colorModel);
        
        // Get the southwest corner and the length of the side of the 
        // square in the complex plane to image.
        JTextField swCornerTB = new JTextField();
        JTextField sideTB = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Enter the square in the complex plane to display."),
                new JLabel("Southwest Corner as a + bi"),
                swCornerTB,
                new JLabel("Length of side. (ex. 1.23)"),
                sideTB
        };
        JOptionPane.showMessageDialog(null, inputs, "Enter Square", JOptionPane.PLAIN_MESSAGE);
        
        // Parse the input complex number
        String [] swCornerStr = (swCornerTB.getText()).split("\\+");
        
        // If the string does not have a "+" then it must have a "-".
        if (swCornerStr.length==1) {
            // no + sign in the middle, so reformat
            int idxMinus = swCornerStr[0].lastIndexOf('-');
            String firstPart = swCornerStr[0].substring(0, idxMinus);
            String secondPart = swCornerStr[0].substring(idxMinus);
            String modifiedNum = firstPart + "+" + secondPart;
            swCornerStr = modifiedNum.split("\\+");
        }
            
        // Convert the input from a string format to the needed format
        ComplexNumber swCorner = null;
        double side = 0;
        try {
          // convert southwest corner to complex number 
          double swReal = Double.parseDouble(swCornerStr[0]);
          int posI = swCornerStr[1].indexOf('i');
          if (posI == -1) 
              throw new NumberFormatException();
          swCornerStr[1] = swCornerStr[1].substring(0, posI);
          double swImag = Double.parseDouble(swCornerStr[1]);
          
          swCorner = new ComplexNumber(swReal, swImag);
          
          // covert the length of the side
          side = Double.parseDouble(sideTB.getText());
        }
        catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "You must enter a complex number in the specified format.", 
                    "Illegal Complex Number", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        System.out.printf("Southwest Corner: %f + %fi, Side Length: %f\n",swCorner.getRealPart(),
                                                          swCorner.getImagPart(),side);
        
        // Make an instance of the app. This solves a problem with main being declared static.                                                 
        MandelApp app = new MandelApp(WIN_WIDTH, WIN_HEIGHT);
        
        // assign an instance of the Mandelbrot set generator
        app.setMandelGenerator(new MandelbrotEngine(swCorner, side, app.getMandelbrotSet()));
        
        // generate the specified Mandelbrot set image
        app.getMandelGenerator().generateSet(colorModel);
        
        // set the title in the main window
        app.getWin().setTitle("Mandelbrot Set ( SW corner: " + swCorner.toString() + " side: " + side+ " )");

        // update the image in the window to reflect the changes to the image
        app.getWin().update();
        
        // make the window visible
        app.getWin().setVisible(true);
    }
    

}
