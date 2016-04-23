package com.ilp.proj1.exception;

public class InsuranceException extends Exception{
	
	/**
	 * @ default serial version
	 */
	private static final long serialVersionUID = 1L;

	public InsuranceException()
	{
		
	}
	/**
	 * construct :: insuranceActive  void ------> String
	 * @return String 
	 */
	public String insuranceActive()
    {
    	return "Sorry! An insurance is  purchased against this vehicle!!!";
    }
	

}
