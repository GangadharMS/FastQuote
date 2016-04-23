package com.ilp.proj1.exception;

public class Mainexception extends Exception
{

    /**
	 * @ default serial version
	 */
	private static final long serialVersionUID = 1L;
	String msg;

    public Mainexception(String err)
    {
    	msg=err;
    }
    //default constructor
    public Mainexception()
    {
    	
    }
    
    //Error message
    public String getmessage()
    {
    	return msg;
    }
    
    public String vehicleListException()
    {
    	return ("Sorry! You have already purchased this insurance for your all vehicles!!!");
    }
    
    public String bodyInsurancefailure()
    {
    	return ("Sorry! You have already purchased a body injury insurance!!!");
    }
    
    public String insuranceNotRegistered()
    {
    	return ("Sorry! You have not purchased any insurance!!!");
    }
    
    public String vehicleNumberException()
    {
    	return "Sorry! Vehicle is already registered!!!";
    }
    
    /*---------------------------------Nominee Details------------------------------------*/ 
    public String nomineeNotRegisteredException()
    {
    	return " Sorry! Nominee is absent against this insurance!!!";
    }
    
    
	/*--------------------------Bank Details Exception START by 592399---------------------*/

    
    /*@author: 592399
    */
	public String noVehicleRegisteredAgainstTheInsurance(){
		
		return ("Sorry! No vehicle is registered against this insurance!!!");
	}
/*--------------------------------Bank Details Exception START by 592399------------------------------*/
	 /*@author: 592399
	    */

	public String BankAlreadyRegisteredException()
	{
		return("Sorry! your bank details is already present!!!");
	}
	
	/*@author: 592399
	    */
	public String AccountNumberAlreadyExists(){

		return("Sorry! Account number already exists!!!");
	}
	 
	/*@author: 592399
	  */
	public String NoBankRegistered(){

		return("Sorry! You have not registered any bank yet!!!");
	}
	/*--------------------------Bank Details Exception END by 592399---------------------*/
}
