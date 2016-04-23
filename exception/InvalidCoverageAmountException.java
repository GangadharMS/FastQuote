package com.ilp.proj1.exception;
/**
 * This class will fired when coverage amount is more than the 
 * specified amount of a particular insurance
 * 
 *@author Debabrata:594486
 */
public class InvalidCoverageAmountException extends Exception{
	/**
	 * @default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	private double roadPriceVec;    //road price of vehicle
	 
	public InvalidCoverageAmountException()
	{
		
	}
	//initializing by constructor
	public InvalidCoverageAmountException(double roadPriceVec)
	{
		this.roadPriceVec=roadPriceVec;
		
	}
	
	public String showErrorMessage()
	{
		return "Sorry! Vehicle damage insurance coverage can not be more than 80% of "+
	            "vehicle's road price!!!";
	}
	
	public String errorMsgOfLiabilityInsurance()
	{
		return "Sorry! Liability insurance coverage can not be more than $ 70lacs!!!";
	}
	
	public String errorMsgOfBodyInsurance()
	{
		return "Sorry! Body injury insurance coverage can not be more than $ 40lacs!!!";
	}

}
