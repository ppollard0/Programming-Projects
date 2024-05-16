package mandelbrotprocessor;

/**
 * ColorTable defines a table of 256 colors that can be used to determind a color.
 * 
 * @author S. Sigman
 * @version v1.0
 */
public class ColorTable {
	private final int NO_COLORS = 256;  // number of colors to use

	short [][] lookupData;  // 2 dimensional table of colors
	
	/**
	 * Constructs a table of 256 colors.
	 */
	public ColorTable() {
		short [][] baseColors = defaultBaseColors();
		
		// make the color arrays
		short [] red = new short[NO_COLORS];
		short [] green = new short[NO_COLORS];
		short [] blue = new short [NO_COLORS];
		
		// generate color arrays
		for (int i=0; i < 8; i=i+2) {
			double redM = (baseColors[i+1][0] - baseColors[i][0])/63.0;
			double greenM = (baseColors[i+1][1] - baseColors[i][1])/63.0;
			double blueM = (baseColors[i+1][2] - baseColors[i][2])/63.0;
			for (int j=i/2*64; j < i/2*64 + 64; j++) {
				short xPart = (short)(j - (i/2*64 + 63));
				red[j] = (short)(baseColors[i+1][0] + redM*(xPart));
				green[j] = (short)(baseColors[i+1][1] + greenM*(xPart));
				blue[j] = (short)(baseColors[i+1][2] + blueM*(xPart));
			}
		}
		
		// make the lookup table
		lookupData = new short[3][NO_COLORS];
		lookupData[0] = red;
		lookupData[1] = green;
		lookupData[2] = blue;
	}
	
	/**
	 * GetColor returns the color at the specified index.
	 * 
	 * @param index The number of the color to return.
	 * @return An array of three elements representing a color.
	 */
	public int [] getColor(int index) {
		int [] color = new int[3];
		
		color[0] = (int)lookupData[0][index];
		color[1] = (int)lookupData[1][index];
		color[2] = (int)lookupData[2][index];
		return color;
	}
	
	// Utility method used to generate a possible set of base colors.
	// These colors are used in the generation of the colors in the table.
	private short [][] defaultBaseColors() {
		short [][] baseColors;
		baseColors = new short[8][3];
		baseColors[0][0] = 59;
		baseColors[0][1] = 9;
		baseColors[0][2] = 241;
		
		baseColors[1][0] = 141;
		baseColors[1][1] = 111;
		baseColors[1][2] = 249;
		
		baseColors[2][0] = 229;
		baseColors[2][1] = 9;
		baseColors[2][2] = 90;
		
		baseColors[3][0] = 250;
		baseColors[3][1] = 0;
		baseColors[3][2] = 36;
		
		baseColors[4][0] = 250;
		baseColors[4][1] = 0;
		baseColors[4][2] = 36;
		
		baseColors[5][0] = 227;
		baseColors[5][1] = 250;
		baseColors[5][2] = 6;
		
		baseColors[6][0] = 2;
		baseColors[6][1] =170;
		baseColors[6][2] = 54;
		
		baseColors[7][0] = 111;
		baseColors[7][1] = 253;
		baseColors[7][2] = 155;
		
		return baseColors;
	}
}
