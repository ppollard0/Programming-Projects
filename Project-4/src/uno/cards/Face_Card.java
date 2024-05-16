package uno.cards;

// This file define an UNO face card.  
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : UNO_Model
//  @ File Name : Face_Card.java
//  @ Date : 4/1/2015
//  @ Author : S. Sigman
//
//


/** 
 * The Face_Card class defines a UNO face card.  A face card
 * has a color and a number.  Face cards are numbered from 
 * 0 to 9.
 */
public class Face_Card extends UNO_Card
{
	private int faceNumber;  // The number on the card
	
	/** 
	 * Constructs an UNO face card of the given color and with the specified number.
	 * 
	 * @param clor The color of the card.
	 * @param num The number on the card.
	 */
	public Face_Card(int clor, int num)
	{
	   super(clor);
	   faceNumber = num;
	}
	
	/**
	 * This method returns the number on the face of the card.
	 * 
	 * @return The number on the face of the card.
	 */
	public int getFaceNumber()
	{
	   return faceNumber;
	}
	
	/** 
	 * This method allows two face cards to be compared.  
	 * 
	 * @param other The card to compare with this one.  It must be a Face_Card
	 */
	public int compareTo(UNO_Card other)
	{
	    if (!(other instanceof Face_Card))
	      return 1;
	    Face_Card theOtherCard = (Face_Card)other;
	    if (this.getFaceNumber() == theOtherCard.getFaceNumber())
	      return 0;
	    else
	      return 1;
// 	    else if (this.getFaceNumber() < theOtherCard.getFaceNumber())
// 	      return -1;
// 	    else 
// 	      return 1;
	}
	
	public String toString()
	{
	    return "Face Card - color: " + getColorAsString() +
	                        " number: " + getFaceNumber();
	}
}