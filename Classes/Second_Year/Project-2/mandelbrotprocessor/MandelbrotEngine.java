package mandelbrotprocessor;

import cs.csmath.complexnumber.ComplexNumber;
import csimage.CSImage;

/**
 * The MandelbrotEngine class provides the facilities to generate an image of 
 * the Mandlebrot set.
 * 
 * @author S. Sigman
 * @version v1.0
 */
public class MandelbrotEngine {
    
	private double gap;   // Distance between complex numbers that can be represented
	private CSImage mandelbrotSet = null; // reference to an image of the set
	
	private ColorTable colorTable = null; // Talbe of colors to use in the 256 color
	                                      // table.

	private ComplexNumber aCorner;        // Southwest corner of the region to display.
	private int MAX_ITERATION = 1000;     // Limit on the number of iterations.
	private int RED_BASE = 203;           // Base value of red used for color generation.
	private int GREEN_BASE = 213;         // Base value of green used for color generation
	private int BLUE_BASE = 253;          // Base value of blue used for color generation
	private final int COLOR_MAX_3 = 319;  // Constant used in color generation
	
	public final static int CONT = 0;     // Flag indicating basic color generation algorithm.
	public final static int C8 = 1;       // Flag indicating 8 level discrete color generation.
	public final static int C16 = 2;      // Flag indicating 16 level discrete color generation.
	public final static int CONT3 = 3;    // Flag indicating 3 level continuous color generation.
	public final static int CONT4 = 4;    // Flag indicating 4 level continuous color generation.
	public final static int CONT5 = 5;    // Flag indicating 5 level continuous color generation.
	public final static int LOOKUP256 = 6; // Flag indicating 256 color table lookup.
	public final static int CUST = 7;
	public final static int CUST2 = 8;
		
    /**
     * Constructs a MandelbrotEngine given the southwest corner of a square region in the
     * complex plane, the length of the sides of the region, and an image to generate the
     * set in. 
     * 
     * @param swCorner A complex number representing the southwest corner of the region 
     *                 in the complex plane to use.
     * @param side The lenght of the sides of the region in the complex plane.
     * @param mandelSet The image in which the set will be generated.
     */
	public MandelbrotEngine(ComplexNumber swCorner, double side, CSImage mandelSet) {
		// calculate the size of gap
		gap = side/mandelSet.getWidth();
		
		// save the image that represents the set
		this.mandelbrotSet = mandelSet;
		
		// save the square to image
		aCorner = swCorner;
		
		// make a table to use for the 256 color lookup
		colorTable = new ColorTable();
	}
	
	/**
	 * GenerateSet generates the Mandlebrot set image for the region specified.  It uses the 
	 * algorithm from the 1985 Scientific American Computer Recreations column by A. K.
	 * Dewdney.
	 * 
	 */
	public void generateSet(int colorModel) {
		int width = mandelbrotSet.getWidth();
		int height = mandelbrotSet.getHeight();
		int [][] countPad = new int[width][height];
		int maxCount = 0;

		// TO DO - Write the code needed to loop through the pixels
		//         column by column.  Using Dewdney's approach generate 
		//         the value of the complex number using the southwest
		//         corner and the gap. Each complex number should be 
		//         iterated using Dewdney's iteration formula to determine 
		//         count.  The iteration should be performed by the 
		//         method iterateComplexNumber, which is stubbed below.
		//         Count values shoud be stored in the appropriate row 
		//         and column cell in the 2D array countPad and you should 
		//         calculate the maximum value of count and store it in the 
		//         variable maxCount, which is needed to caluclate the color.
		for (int col = 0; col < width; col++)
			for (int row = 0; row < height; row++) {
				double realC = col * gap + aCorner.getRealPart();
				double imagC = row * gap + aCorner.getImagPart();
				ComplexNumber c = new ComplexNumber(realC, imagC);
				int countVal = iterateComplexNumber(c);
				countPad[col][row] = countVal;
				if (countVal > maxCount) {
					maxCount = countVal;
				}
			}

		


		// set the colors
		for (int n=0; n < width; n++)
			for (int m=0; m < height; m++) {
				double ratio = ((double)(countPad[n][m]))/(double)maxCount;
				ratio = 1.0 - ratio;
				// get the pixel color
				int [] pixel = new int[3];
				if (colorModel==CONT) 
				   pixel = determineColorCont(ratio, countPad[n][m]);
				else if (colorModel==C8)
				   pixel = determineColorStep8(countPad[n][m]);
				else if (colorModel==C16) 
				   pixel = determineColorStep16(countPad[n][m]); 
				else if (colorModel==CONT3)
				   pixel = determineColorCont3(ratio, countPad[n][m]);
				else if (colorModel==CONT4)
					   pixel = determineColorCont4(ratio, countPad[n][m]);
				else if (colorModel == CONT5)
					   pixel = determineColorCont5(countPad[n][m]);
				else if (colorModel==LOOKUP256)
					pixel = determineColorTableLookup(countPad[n][m]);
				else if (colorModel==CUST)
					pixel = determineColorCust(countPad[n][m]);
				else if (colorModel==CUST2)
					pixel = determineColorCust2(countPad[n][m]);
				this.mandelbrotSet.setPixel(n, (height-1)-m, pixel);
			}
	}

	/**
	 * This method implements the iteration scheme on the complex numbers and
	 * determines the number of iterations until the absolute value of the 
	 * number exceeds 2.0 or count exceeds a specified maximum.
	 * 
	 *  z <- 0
	 *  count <- 0
	 *  repeat while abs(z) <= 2.0 and count <= MAX_ITERATION
	 *    z <- z*z + c
	 *    count <- count + 1
	 *    
	 *    @param c The conplex number to iterate.
	 *    @return The number of iterations until 2 was exceeded or 1000 if 
	 *            2 was not exceeded.
	 */
	private int iterateComplexNumber(ComplexNumber c) {
		int count = 0;
		ComplexNumber z = new ComplexNumber();
        // TO DO - Write the code necessary to calculate the
		//         value of count that results from the
		//         iteration scheme specified in the notes.
		while (z.abs() <= 2.0 && count <= MAX_ITERATION) {
			z.mult(z);
			z.add(c);
			count++;
		}

		// assert: z.abs()>2 or count > MAX_ITERATION

		return count;
	}
	
	/**
	 * GetMAX_ITERATION returns the maximum number of iterations.
	 * 
	 * @return The value of MAX_ITERATION.
	 */
	public int getMAX_ITERATION() {
		return MAX_ITERATION;
	}

	/**
	 * SetMAX_ITERATION sets the value of MAX_ITERATION.
	 * 
	 * @param mAX_ITERATION The new value of MAX_ITERATION.
	 */
	public void setMAX_ITERATION(int mAX_ITERATION) {
		MAX_ITERATION = mAX_ITERATION;
	}
	
	/**
	 * GetMandelbrotSet returns the image of the Mandelbrot set.
	 * 
	 * @return The image of the Mandelbrot set.
	 */
	public CSImage getMandelbrotSet() {
		return mandelbrotSet;
	}
	
	// Generate the colors continuously using the ratio
	private int [] determineColorCont(double ratio, int count) {
		// pixel color is based upon ratio that measures the number of iterations
		// The more iterations the darker the number, the less iterations the lighter.
		// Note: If the pixel is in the set the color is black
		int [] retColor = {0, 0, 0};
		if (count < getMAX_ITERATION()) {

		    retColor[0] = (int)(RED_BASE*ratio); 
		    retColor[1] = (int)(GREEN_BASE*ratio);
		    retColor[2] = (int)(BLUE_BASE*ratio); 
		}
			
	    return retColor;
	}
	
	// Generate the colors using 8 colors based upon a blue scale
	private int [] determineColorStep8(int count) {
		int[] retColor = new int[3];
		if (count < (int)(0.125*getMAX_ITERATION())){
			retColor[0] = 203;
			retColor[1] = 213;
			retColor[2] = 253;
		}
		else if (count < (int)(0.25*getMAX_ITERATION()) ){
			retColor[0] = 174;
			retColor[1] = 189;
			retColor[2] = 252;
		}
		else if (count < (int)(0.375*getMAX_ITERATION()) ){
			retColor[0] = 134;
			retColor[1] = 156;
			retColor[2] = 250;
		}
		else if (count < (int)(0.5*getMAX_ITERATION()) ){
			retColor[0] = 82;
			retColor[1] = 114;
			retColor[2] = 248;
		}
		else if (count < (int)(0.625*getMAX_ITERATION()) ){
			retColor[0] = 41;
			retColor[1] = 80;
			retColor[2] = 247;
		}
		else if (count < (int)(0.75*getMAX_ITERATION()) ){
			retColor[0] = 9;
			retColor[1] = 54;
			retColor[2] = 245;
		}
		else if (count < (int)(0.875*getMAX_ITERATION()) ){
			retColor[0] = 8;
			retColor[1] = 45;
			retColor[2] = 200;
		}
		else if (count < getMAX_ITERATION()) {
			retColor[0] = 3;
			retColor[1] = 17;
			retColor[2] = 77;
		}
		else {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		return retColor;
	}
	
    // Generate the colors using 16 colors base upon blue scale
	private int [] determineColorStep16(int count) {
		int[] retColor = new int[3];
		if (count < (int)(0.0625*getMAX_ITERATION())){
			retColor[0] = 203;
			retColor[1] = 213;
			retColor[2] = 253;
		}
		else if (count < (int)(0.125*getMAX_ITERATION()) ){
			retColor[0] = 188;
			retColor[1] = 200;
			retColor[2] = 252;
		}
		else if (count < (int)(0.1875*getMAX_ITERATION()) ){
			retColor[0] = 174;
			retColor[1] = 189;
			retColor[2] = 252;
		}
		else if (count < (int)(0.25*getMAX_ITERATION()) ){
			retColor[0] = 155;
			retColor[1] = 173;
			retColor[2] = 251;
		}
		else if (count < (int)(0.3125*getMAX_ITERATION()) ){ //5/16
			retColor[0] = 134;
			retColor[1] = 156;
			retColor[2] = 250;
		}
		else if (count < (int)(0.375*getMAX_ITERATION()) ){ //6/16
			retColor[0] = 107;
			retColor[1] = 134;
			retColor[2] = 249;
		}
		else if (count < (int)(0.4375*getMAX_ITERATION()) ){ //7/16
			retColor[0] = 82;
			retColor[1] = 114;
			retColor[2] = 248;
		}
		else if (count < (int)(0.5*getMAX_ITERATION()) ){ //8/16
			retColor[0] = 62;
			retColor[1] = 97;
			retColor[2] = 248;
		}
		else if (count < (int)(0.5625*getMAX_ITERATION()) ){ //9/16
			retColor[0] = 41;
			retColor[1] = 80;
			retColor[2] = 247;
		}
		else if (count < (int)(0.625*getMAX_ITERATION()) ){ //10/16
			retColor[0] = 26;
			retColor[1] = 68;
			retColor[2] = 246;
		}
		else if (count < (int)(0.6875*getMAX_ITERATION()) ){//11/16
			retColor[0] = 9;
			retColor[1] = 54;
			retColor[2] = 245;
		}
		else if (count < (int)(0.75*getMAX_ITERATION()) ){ //12/16
			retColor[0] = 9;
			retColor[1] = 50;
			retColor[2] = 225;
		}
		else if (count < (int)(0.8125*getMAX_ITERATION()) ){ // 13/16
			retColor[0] = 8;
			retColor[1] = 45;
			retColor[2] = 200;
		}
		else if (count < (int)(0.875*getMAX_ITERATION()) ){ // 14/16
			retColor[0] = 5;
			retColor[1] = 30;
			retColor[2] = 137;
		}
		else if (count < (int)(0.9375*getMAX_ITERATION())) { //15/16
			retColor[0] = 3;
			retColor[1] = 17;
			retColor[2] = 77;
		}
		else if (count < (int)(0.25*getMAX_ITERATION()) ){
			retColor[0] = 2;
			retColor[1] = 14;
			retColor[2] = 64;
		}
		else {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		return retColor;
	}
	
	// Generate the colors continuously using a specified ratio and 3 breaks
	private int [] determineColorCont3(double ratio, int count) {
        // scale count
		int scaledCount = (int)(count*ratio);
		int scaledItLimit = COLOR_MAX_3;
		
		int [] retColor = new int[3];
		if (count > MAX_ITERATION) {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		else {
			if (scaledCount < 16) {
				retColor[0] = scaledCount*8;
				retColor[1] = scaledCount*8;
				retColor[2] = 128+scaledCount*4;
			}
			if (scaledCount >= 16 && scaledCount < 64) {
				retColor[0] = 128 + scaledCount - 16;
				retColor[1] = 128 + scaledCount - 16;
				retColor[2] = 192 + scaledCount - 16;
			}
			if (scaledCount>= 64) {
				retColor[0] = scaledItLimit-scaledCount-(scaledItLimit - scaledCount)/2;
				retColor[1] = 128 + (scaledItLimit - scaledCount)/2;
				retColor[2] = scaledItLimit-scaledCount;
			}
		}
		
		return retColor;
		
		
	}
	
	// Generate the colors continuously using a specified ratio and 4 breaks
	private int [] determineColorCont4(double ratio, int count) {
        // scale count
		int scaledCount = (int)(count*ratio);
		int scaledItLimit = COLOR_MAX_3;
		
		int [] retColor = new int[3];
		if (count > MAX_ITERATION) {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		else {
			if (scaledCount < 16) {
				retColor[0] = scaledCount*8;
				retColor[1] = scaledCount*8;
				retColor[2] = 128+scaledCount*4;
			}
			if (scaledCount >= 16 && scaledCount < 64) {
				retColor[0] = 128 + scaledCount - 16;
				retColor[1] = 128 + scaledCount - 16;
				retColor[2] = 192 + scaledCount - 16;
			}
			if (scaledCount >= 64 && scaledCount < 200) {
				retColor[0] = 185-(int)(0.39*(scaledCount-200));
				retColor[1] = 161-(int)(0.412*(scaledCount-200));
				retColor[2] = 19 - (int)(0.51*(scaledCount-200));
			}
			if (scaledCount>= 200) {
				retColor[0] = scaledItLimit-scaledCount-(scaledItLimit - scaledCount)/2;
				retColor[1] = 128 + (scaledItLimit - scaledCount)/2;
				retColor[2] = scaledItLimit-scaledCount;
			}
		}
		
		return retColor;	
	}	
	
	// Generate colors using a continuous scale
	private int [] determineColorCont5(int count) {
		int [] retColor = new int[3];
		if (count > MAX_ITERATION) {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		else {
			retColor[0] = 180;
			retColor[1] = 2*count % 254;
			retColor[2] = 0;
		}
		
		return retColor;	
	}
	
	// Lookup the color in a table of 256 possible colors
	private int [] determineColorTableLookup(int count) {
		int [] retColor = new int[3];
		if (count > MAX_ITERATION){
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		else 
			retColor = colorTable.getColor(count%256);
			
		return retColor;
	}

	//Custom Colors
	private int [] determineColorCust(int count) {
		int [] retColor = new int [3];
		if (count > MAX_ITERATION) {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		}
		else {
			if (count < 16) {
				retColor[0] = 34;
				retColor[1] = 31;
				retColor[2] = 54;
			}
			if (count >= 16) {
				retColor[0] = 0;
				retColor[1] = 98;
				retColor[2] = 198;
			}
			if (count >= 64) {
				retColor[0] = 253;
				retColor[1] = 90;
				retColor[2] = 98;
			}
			if (count >= 200) {
				retColor[0] = 194;
				retColor[1] = 125;
				retColor[2] = 024;
			}

		}
		return retColor;
	}
	private int [] determineColorCust2(int count) {
		int[] retColor = new int[3];
		if (count > MAX_ITERATION) {
			retColor[0] = 0;
			retColor[1] = 0;
			retColor[2] = 0;
		} else {
			if (count < 2) {
				retColor[0] = 0;
				retColor[1] = 0;
				retColor[2] = 255;
			}if (count >= 2) {
				retColor[0] = 255;
				retColor[1] = 0;
				retColor[2] = 255;
			}
			if (count >= 3) {
				retColor[0] = 255;
				retColor[1] = 255;
				retColor[2] = 0;
			}
			if (count >= 4) {
				retColor[0] = 0;
				retColor[1] = 255;
				retColor[2] = 0;
			}
			if (count >= 5) {
				retColor[0] = 255;
				retColor[1] = 0;
				retColor[2] = 0;
			}
			if (count >= 6) {
				retColor[0] = 0;
				retColor[1] = 128;
				retColor[2] = 255;
			}
			if (count >= 7) {
				retColor[0] = 128;
				retColor[1] = 0;
				retColor[2] = 255;
			}
			if (count >= 8) {
				retColor[0] = 128;
				retColor[1] = 255;
				retColor[2] = 0;
			}
			if (count >= 9) {
				retColor[0] = 0;
				retColor[1] = 255;
				retColor[2] = 128;
			}
			if (count >= 10) {
				retColor[0] = 255;
				retColor[1] = 0;
				retColor[2] = 0;
			}
			if (count >= 16) {
				retColor[0] = 255;
				retColor[1] = 128;
				retColor[2] = 0;
			}
			if (count >= 24) {
				retColor[0] = 0;
				retColor[1] = 0;
				retColor[2] = 255;
			}
			if (count >= 32) {
				retColor[0] = 255;
				retColor[1] = 255;
				retColor[2] = 0;
			}
			if (count >= 64) {
				retColor[0] = 0;
				retColor[1] = 255;
				retColor[2] = 0;
			}
			if (count >= 96) {
				retColor[0] = 255;
				retColor[1] = 0;
				retColor[2] = 0;
			}
			if (count >= 128) {
				retColor[0] = 0;
				retColor[1] = 255;
				retColor[2] = 255;
			}
			if (count >= 160) {
				retColor[0] = 255;
				retColor[1] = 0;
				retColor[2] = 255;
			}
			if (count >= 200) {
				retColor[0] = 0;
				retColor[1] = 255;
				retColor[2] = 128;
			}
			if (count >= 300) {
				retColor[0] = 0;
				retColor[1] = 128;
				retColor[2] = 255;
			}
			if (count >= 350) {
				retColor[0] = 255;
				retColor[1] = 128;
				retColor[2] = 0;
			}
			if (count >= 400) {
				retColor[0] = 0;
				retColor[1] = 0;
				retColor[2] = 255;
			}
			if (count >= 450) {
				retColor[0] = 255;
				retColor[1] = 255;
				retColor[2] = 0;
			}
			if (count >= 500) {
				retColor[0] = 128;
				retColor[1] = 0;
				retColor[2] = 255;
			}
			if (count >= 550) {
				retColor[0] = 0;
				retColor[1] = 255;
				retColor[2] = 255;
			}
			if (count >= 600) {
				retColor[0] = 255;
				retColor[1] = 0;
				retColor[2] = 128;
			}

		}
		return retColor;
	}
}
