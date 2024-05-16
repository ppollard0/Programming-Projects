package ui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import csimage.CSImage;
import csimage.CSImagePanel;

/**
 * MandelWin provides the main window for the Mandelbrot application.
 * 
 * @author Scott Sigman
 * @version v1.0
 */
public class MandelWin extends JFrame {
	
	private JPanel contentPane = null;      // Main content panel for the application.
	private CSImagePanel imagePanel = null; // Panel used to display the image
	private CSImage image = null;           // Image that holds the Mandelbrot set image
	
	/**
	 * Constructs an instance of the main window, which houses the 
	 * Mandelbrot set image.
	 * 
	 * @param mandelImage The Mandelbrot set image.
	 */
	public MandelWin(CSImage mandelImage) {
	    
	    // set uup a basic frame window of the right size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPanel();
		this.setTitle("Mandelbrot Set");
		this.setBounds(100,100, mandelImage.getWidth(), mandelImage.getHeight());
		  
		// add the image to the window in the center position
		this.image = mandelImage;
		getContentPanel().add(getImagePanel());
	}
	
	/**
	 * Update registers changes in the Mandelbrot set images so that 
	 * they are displayed in the image panel.  It should be called each
	 * time the image is changed in order that the changes be displayed.
	 */
	public void update() {
		this.getImagePanel().update();
	}
	
	/**
	 * This method ensures that a single instance of the content panel 
	 * occurs in the window.  It should be called each time the content
	 * panel needs to be accessed.
	 * 
	 * @return The content panel for the main window.
	 */
	private JPanel getContentPanel() {
		if (contentPane==null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
			this.setContentPane(contentPane);
		} 
		return contentPane;	
	}
	
	/**
	 * This method ensures that there is a single instance of the 
	 * image panel.  It should be called everytime the image panel needs
	 * to be accessed.
	 * 
	 * @return The image panel that contains the Mandelbrot set image.
	 */
	private CSImagePanel getImagePanel() {
	  if (imagePanel==null) {
		  imagePanel = new CSImagePanel(image);
	  }
	  return imagePanel;
	}
	

}
