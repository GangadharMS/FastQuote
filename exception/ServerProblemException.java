package com.ilp.proj1.exception;

public class ServerProblemException extends Exception{

	/**
	 * @ default version
	 */
	private static final long serialVersionUID = 1L;
    public String errorMessage()
    {
    	return "Sorry! Due to some problem your request can not be processed now, " +
    			              "please try later!!!";
    }
}
