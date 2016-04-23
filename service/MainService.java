package com.ilp.proj1.service;



import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import com.ilp.proj1.exception.Mainexception;
import com.ilp.proj1.service.AES;
import com.ilp.proj1.exception.*;
import com.ilp.proj1.dao.MainDao;
import com.ilp.proj1.model.MainModel;


public class MainService {
	
	/**********************************************************************************************************************/
	/*--------------------------------------------------------------------------------------------------------------------*/
	/*****************************submodule one****************************************************************************/
	/**
	 * Customer Registration and Log in
	 * @author 592286
	 * @author 678232
	 * 
	 */
			
			
	/**-----------------------------------------------------------------------------*/



	/**
	 * @author Debashis-592274
	 * START CODE
	 * SUBMODULE 1
	 */




	/*
	 * 
	 * DECLARING ALL VERIALBES 
	 */




	ArrayList<MainModel> ar=new ArrayList<MainModel>();
	/*static int atmt=0;
	public int attempt(){
		  atmt++;
		  return atmt;
		  }*/

	
	


	/*
	 * 
	 * METHOD FOR GETING THE LOGIN DETAILS LIST FROM DATABASE USING USERNAME
	 * @param lim
	 * @return
	 */



	public String getLogInDetails(MainModel lim)throws Mainexception
	{	
		String message = null;
		if(lim.getUserId()==null && lim.getPassword()==null)
		{
			throw new Mainexception("Enter log in details");
		}
		try 
		{
			boolean valid=false;

			MainDao lid=new MainDao();
			ar=lid.getLogIn(lim);

			//CHECKING ARRAYLIST IS EMPTY OR NOT

			//IF ARRAYLIST IS EMPTY


			if(ar.isEmpty())
			{
				//int a=attempt();
				
				
				//IF ATTEMPT IS LESS THAN 3 TIMES
				//if(a<=3)
				//{
					message="3"; //WRONG USERNAME AND PASSWORD
				//}
				
				
				//IF ATTEMPT IS MORE THAN 3 TIMES
				/*if(a>3)
				{
					message="6"; //CAN NOT ACCESS
				}*/
				
			}


			//IF ARRAYLIST IS NOT EMPTY


			if(!ar.isEmpty())
			{

				// MATCH PASSWORD

				valid=matchPassword(ar, lim);

				//IF PASSWORD MATCH

				if(valid)
				{

					// CHECK ACCOUNT STATUS

					//IF ACOUNT STATUS IS INACTIVE
					if(!checkLogInStatus(ar))
					{
						message="User inactive";
					}

					//IF ACCOUNT STATUS IS ACTIVE
					if(checkLogInStatus(ar))
					{
						//IF FIRST LOG IN
						if(checkFirstlogIn(ar))
						{
							message="1";
						}
						
						//IF NOT FIRST LOG IN
						if(!checkFirstlogIn(ar))
						{
							message="2";
						}

					}

				}


				// IF PASSWORD DOES NOT MATCH


				else
				{
					message="4"; //WRONG PASSWORD
				}
			}
		}
		catch (Exception e)
		{
			//System.out.println("Service-->check getLogInDetails");
		}

		return message;		

	}







	/*
	 * 
	 * METHOD FOR CHECKING THE STATUS OF THE USER 
	 * @param rs
	 */







	public boolean checkLogInStatus(ArrayList<MainModel> ar)
	{
		boolean valid=false;
		String status = null;
		String status2="active";

		// GETING THE STATUS OF USER


		for(MainModel x:ar)
		{
			status=x.getStatus();
		}

		//CHECKING THE STATUS OF USER


		if(status.toLowerCase().equals(status2))
		{
			valid=true;
		}


		return valid;
	}





	/*
	 * METHOD FOR CHECKING FIRST LOG IN
	 * @param ar
	 * @return
	 */





	public boolean checkFirstlogIn(ArrayList<MainModel> ar)
	{

		boolean valid = false;
		int count = 0;

		//GETING THE COUNT OF USER

		for(MainModel x:ar)
		{
			count=x.getCount();
		}

		//CHECKING THE COUNT

		if(count==0)
		{
			valid=true;
		}
		if(count!=0)
		{
			valid=false;
		}
		return valid;

	}





	/*
	 * MACHING TWO PASSWORD
	 * @param ar
	 * @param lim
	 * @return
	 */






	public boolean matchPassword(ArrayList<MainModel> ar, MainModel lim)
	{
		boolean valid=false;


		try
		{
			String password1 = null, password2,password3 = null,oldkey = null;

			//GETING THE PASSWORD FROM ARRAYLIST


			for(MainModel x:ar)
			{
				password1=x.getPassword();
				oldkey=x.getKey();
			}

			//password1=decrypt(password3, oldkey);
			
			
			
			//GETING THE PASSWORD FROM CONTROLLER

			password2=lim.getPassword();

			//MATCHING TWO PASSWORD


			if(password1.equals(password2))
			{
				valid=true;
			}


			else
			{
				valid=false;
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			//System.out.println("Service--> Check matchPassword");
		}

		return valid;
	}






	/*
	 * METHOD FOR CHECKING OLD PASSWORD FOR CHANGE PASSWORD PAGE 
	 * @param op
	 * @return
	 */






	public boolean checkOldPassword(MainModel op)
	{
		boolean valid=false;
		MainDao lid=new MainDao();

		//GETING DATA FROM DATABASE


		ar=lid.getLogIn(op);

		//MATCHING PASSWORD WITH matchPassword()
		valid=matchPassword(ar, op);
		return valid;
	}






	/*
	 * METHOD UPDATE NEW PASSWORD
	 * 
	 */





	public boolean setNewPassword(MainModel np)
	{
		//GETTING NEW KEY

		String key=getKey();
		String password=np.getPassword();


		// ENCRYPTING NEW PASSWORD

		//String encryptPwd=encrypt(password, key);
		String encryptPwd=password;
		//String encryptPwd=password;
		boolean valid=false;
		MainDao md=new MainDao();

		//SENCING NEW PASSWORD AND KEY TO DAO CLASS

		valid=md.updatePassword(encryptPwd,key,np);
		return valid;
	}



	/*
	 * 
	 * METHOD FOR GENERATING NEW KEY
	 * 
	 */


	public String getKey()
	{
		//GETTING SYSTEM DATE AND TIME

		long k=Calendar.getInstance().getTimeInMillis();
		String key1=String.valueOf(k);
		String key2=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());


		//GENERATING KEY

		String key=key1+key2;

		//RETURNING KEY

		return key;
	}


	/*
	 * 
	 * CODE FOR ENCRYPTION
	 * 
	 */

	/*public String encrypt(String data, String key)
	{

		//ENCRYPTING DATA

		byte[] enc = AES.encrypt(data.getBytes(), key.getBytes());

		//RETURNING ENCRYPTED DATA

		return new String(enc);

	}*/


	/*
	 *  CODE FOR DECRYPTION
	 */

	/*public String decrypt(String data, String key)
	{
			//DECRYPTING DATA

			byte[] dec = AES.decrypt(data.getBytes(), key.getBytes());



			//RETURNING DECRYPTED DATA

		return new String(dec);
	}*/



	/*
	 * 
	 * CODE FOR UPDATING COUNT
	 */

	public void update(int count, String userid)
	{
		MainDao md=new MainDao();
		md.getUpdate(count, userid);
	}



	/*
	 * 
	 * CODE FOR GETTING SECURITY QUESTION
	 * 
	 */

	public ArrayList<MainModel> getQuestion(String userid, String dob) throws Mainexception
	{
		ArrayList<MainModel> ar=new ArrayList<MainModel>();
		MainDao md=new MainDao();
		ar=md.getQuestion(userid, dob);
		if(ar.isEmpty())
		{
			throw new Mainexception("Enter correct Userid/Date of Birth");
		}
		return ar;

	}


	/*
	 * 
	 * CODE FOR MATCHING SECURITY ANSWER
	 * 
	 */
	
	/**
	 * 	Contract     ::  matchAnswer :Object--->Boolean
	 * Purpose       ::  This method is to match the Security answer
	 * @param answer::String reference
	 * @param     ::MainDao object
	 * @return password      :: String
     **/

	public boolean matchAnswer(ArrayList<MainModel> ar, String answer)throws Mainexception
	{
		boolean valid=false;
		String answer2=null;
		if(answer.equals(null))
		{
			throw new Mainexception("Enter answer");
		}
		for(MainModel x:ar)
		{
			answer2=x.getSecurityQusAnswer();
		}
		if(answer2.equals(answer))
		{
			valid=true;
		}
		else
		{
			throw new Mainexception("Enter correct answer");
		}

		return valid;

	}
	
	/*
	 * 
	 * METHOD FOR GET PASSWORD
	 * 
	 */

	/**
	 * 	Contract     ::  getPassword :Object--->String
	 * Purpose       ::  This method is to get password
	 * @param password,key::String references
	 * @param md     ::MainDao object
	 * @return password      :: String
     **/
	
	
	public String getPassword(String userid) throws Mainexception
	{
		
		String password=null;
		String password1=null;
		String key=null;
		MainDao md=new MainDao();
		ArrayList<MainModel> ar=new ArrayList<MainModel>();
		ar=md.getPassword(userid);
		if(ar.isEmpty())
		{
			throw new Mainexception("Process failed. Please try again");
		}
		for (MainModel x:ar)
		{
			password=x.getPassword();
			key=x.getKey();
		}
		
		//password=decrypt(password1,key);
		return password;
		
	}
	//End of getPassword method
	
	
	/**
	 * 	Contract     ::  validate :Object--->Boolean
	 * Purpose       ::  This method is for validation
	 * @return       :: boolean
     **/
	
	public boolean validate(String newPwd, String oldPwd, String confirmPwd) throws Mainexception
	{
		if(newPwd.equalsIgnoreCase(null) && oldPwd.equalsIgnoreCase(null) && confirmPwd.equalsIgnoreCase(null))
		{
			throw new Mainexception("Enter all details");
		}
		return false;
	}
	
	//End of validate method
	
	
	/**
	 * 	Contract                 ::  getUser :Object--->String
	 * Purpose                   ::  This method is to get User
	 * @param custName           :: String  reference
	 * @param md                 :: MainDao Object
	 * @return  custName         :: String
     **/
	
	public String getUser(String userid)
	{
		String custName=null;
		MainDao md=new MainDao();
		custName=md.getUser(userid);
		return custName;
	}
//end of getUser method 

	/**
	 * @author Debashis-592274	
	 * END CODE
	 * SUBMODULE 1
	 */


	/**---------------------------------------------------------------------*/



	
	
	/*
	 * Start of 678232
	 * 
	 */
	
	/**
	 * 	Contract          :: createPwd :Object--->String
	 * Purpose            ::  This method is to create Password
	 * @param password,a  :: String  references
	 * @return            :: String
     **/
	  	public String createPwd(String fName, String lName )
	  	{
	  		int count=0;
	  		String password;
	  		String a;
	  		/*
	  		 *counting vowel for first name 
	  		 */
	  		
	  		for(int i=0; i<fName.length();i++)
	  		{
	  			if(fName.charAt(i)=='a' || fName.charAt(i)=='e' || fName.charAt(i)=='i' || fName.charAt(i)=='o' || fName.charAt(i)=='u')
	  			{
	  				count++;
	  			}
	  		}
	  		
	  		/*
	  		 * counting vowel for last name
	  		 * 
	  		 */
	  		
	  		
	  		for(int i=0; i<lName.length();i++)
	  		{
	  			if(lName.charAt(i)=='a' || lName.charAt(i)=='e' || lName.charAt(i)=='i' || lName.charAt(i)=='o' || lName.charAt(i)=='u')
	  			{
	  				count++;
	  			}
	  		}
	  		
	  		/*
	  		 * 
	  		 * Taking first 4 characters of name 
	  		 * 
	  		 */
	  		
	  		if(fName.length()>=4)
	  		{
	  			a=fName.substring(0,4);
	  		}
	  		else
	  		{
	  			a=fName;
	  		}
	  		
	  		/*
	  		 * Generating password
	  		 * 
	  		 */
	  		
	  		password=a+"@"+count;
	  		
	  		/*
	  		 * 
	  		 * Returning password 
	  		 */
	  		
	  		return password.toUpperCase();
	  	}
	  	
	  	//End of createPwd method
	  	
	  	
	  	/*
	  	 * CODE TO GENERATE USERNAME
	  	 * 
	  	 */
	  	
	  	/**
		 * Contract          :: getUsername :Object--->String
		 * Purpose            ::  This method is to get User Name
		 * @param  username   :: String  reference
		 * @return  username  :: String
	     **/
	  	public String getUsername(String fName,String dob,String key)
	  	{
	  		String username;
	  		
	  		
	  		
	  		//User_Registration_Service gun=new User_Registration_Service();
	  		
	  		/*
	  		 * 
	  		 * Calling method createUserName
	  		 */
	  		username=createUserName(fName, dob);
	  		return username;
	  		//System.out.println(userName);
	  		
	  	}

	  	//End of getUsername method
	  	
	  	/*
	  	 * 
	  	 * Declaring createUserName method
	  	 * 
	  	 */
	  	/**
		 * 	Contract          :: createUserName :Object--->String
		 * Purpose            ::  This method is to create User Name
		 * @param count,l     :: Integer  reference
		 *  @param valid      :: Boolean reference
		 * @return            :: String
	     **/

	  	public String createUserName(String fName, String dob)
	  	{
	  		
	  		boolean valid;
	  		//int tableCount=3;
	  		int count=0;
	  		int l=dob.length();
	  		MainDao ab=new MainDao();
	  		String d=dob.substring(l-2, l);			//taking last two numbers from date
	  		String username=fName+d+"@"+"FQC";		//creating initial user name
	  		
	  		do			 
	  		{
	  			
	  			valid=ab.check(username);
	  			if(valid==true)					//if user name not available then adding count to user name
	  			{
	  				//System.out.println(count);
	  			count++;
	  			username=fName+d+"@"+count+"FQC";	//generating user name with count			
	  			}
	  			
	  		}while(valid==true);
	  		return username;						//returning user name
	  	}
	  	
	  	//End of createUserName method
	  	
	  	/**
		 * 	Contract  :: insertdata :Object--->boolean
		 * Purpose    ::  This method is for  inserting data 
		 * @param rg  :: MainDao object 
		 * @return t  :: Boolean
	     **/
	  	
	public boolean insertdata(MainModel rm)throws Mainexception
	  {
		MainDao rg=new MainDao();
		  boolean t=rg.getinsertregistration(rm);
		  if(rg.getstatus())
		     return t;
		  throw new Mainexception("Customer has already registred using the provided SSN");
	  }
	 
	//End of insertdata method
	
	/*
	 * CODE TO PASS USERID AND PASSWORD TO DAO
	 * 
	 */
	/**
	 * 	Contract  :: UserLogin :Object--->ArrayList
	 * Purpose    ::  This method passes user id and password
	 * @param rm1 :: MainModel class reference
	 * @return    ::  ArrayList
     **/
	public ArrayList<MainModel>UserLogin(MainModel rm1)
	{
		MainDao rg1=new MainDao();
	    ArrayList<MainModel> t=rg1.getinsertlogin(rm1);
     	return t;
		  
	}
	//End of UserLogin method
	
	/*
	 * CODE TO RETRIVE DATA FROM DB TO UPDATE PAGE
	 * 
	 */
	/**
	 * 	Contract  :: getdata:Object--->ArrayList
	 * Purpose    ::  This method retrieve data 
	 * @param rg  ::  MainDao object
	 * @param uid :: String reference
	 * @return    ::  ArrayList
     **/
	
	
	public ArrayList<MainModel> getdata(String uid) throws Mainexception
	{
		MainDao rg=new MainDao();
		ArrayList<MainModel> res=rg.getdata1(uid);
		if(rg.getstatus())
			return res;
		else
			 throw new Mainexception("Insertion failure");
		
	}
	//End of getdata method
	
	/*
	 * CODE TO UPDATE USERDATA
	 * 
	 */
	/**
	 * 	Contract  ::updatedata :Object--->Boolean
	 * Purpose    ::  This method is for updating data
	 * @param rg  :: MainDao reference
	 * @param res1:: boolean reference
	 * @return    ::  Void
     **/
	
	public boolean updatedata(MainModel rm,String uid) throws Mainexception
	{
		MainDao rg=new MainDao();
	   boolean res1=rg.updatedata(rm,uid);
	   if(res1==false)
		   throw new Mainexception("Update failure");
	   else
		   return res1;
	}
	//End of updatedata method 
	
	/*
	 * CODE TO DEACTIVATE ACCOUNT.
	 */
	
	/**
	 * 	Contract           :: deletepassword :Object--->Void
	 * Purpose             ::  This method is for deleting password
	 * @param uid,password :: String references 
	 * @return             ::  Void
     **/
	public  ArrayList<MainModel> deletepassword(String uid,String password) throws Mainexception
	{
		
		MainDao rg=new MainDao();
	   MainModel res1=rg.deletepassword(uid);
	   ArrayList<MainModel> ar2=new ArrayList<MainModel>();
	    	/*if(res1.getStatus()!="INACTIVE")
	    	{*/
	    		String temp1=res1.getUserId();
	    		String temp2=res1.getPassword();
	    		//String temp=decrypt(temp1,temp2);
	   			
	    		if(temp1.equals(password))
	    		{
	    			ar2=rg.gangaremove(uid);
	    			if(rg.getstatus())
	    				return ar2;
	    			else
	    				throw new Mainexception("You don't have any insurance in fast Quote. Do you really want to deactivate");
	    			
	    		}
	    		else
	    			throw new Mainexception("Password mismatch");	
	    	
	    			
	    	/*else
	    		throw new Mainexception("you are already deactivated");*/
	    		
	   
	   
	}
	public void finaldelete1(String uid) throws Mainexception
	{
		MainDao rg=new MainDao();
		rg.remove(uid);
		if(rg.getstatus())
		{
			
		}
			
		else
			throw new Mainexception("Something went wrong. Please try again after sometime");
	}
	//End of deletepassword method
	
	
	/**
	 * 	Contract   ::  actacc :Object--->Void
	 * Purpose     ::  This method is for activating account
	 * @param obb  ::  obb stands for MainModel object
	 * @return     ::  Void
     **/
	
	public void actacc(MainModel obb) throws Mainexception
	{
		MainDao rg=new MainDao();
		String temp=rg.actacc(obb.getUserId(),obb.getPassword());
		if(rg.getstatus())
		{
			//boolean temp1=matchPassword(temp,obb);
			//if(temp1)
			//{
				String temp2=rg.activate(obb.getUserId());
				if(temp2.equals("INACTIVE"))
				{
				   boolean temp3=rg.activate1(obb.getUserId());
				}
				else
					throw new Mainexception("You are not deactivated yet");
			//}
			//else
				//throw new Mainexception("Userid/password is wrong");
			
			
		}
		else
			throw new Mainexception("Userid/password is wrong");
	}
	
 //End of actacc method

	/**
	 * 592274	
	 * 678232
	 * END CODE
	 * SUBMODULE 1
	 */

	
	/*****************************************************************************************************************/		
	/**----------------------------------Sub-Module Two-------------------------------------------------*/
				
				                    /**
				                       * Vehicle Registration 
				                       * @author 
				                        * @author 
				                        */
	/**
	 * Construct :: insertdataVehicle :  Object---->Object
	 * purpose    : This method will insert vehicle details 
	 * Parameter a1
	 * return a
	 */
	public MainModel insertdataVehicle(MainModel a1)throws ServerProblemException,Mainexception
	 {
		MainDao bsl = new MainDao();
		MainModel a = new MainModel();
		/*
		 * falg  is boolean type variable
		 * if vehicle number is present then then it's value false 
		 */
		boolean flag = bsl.searchVehicleNumber(a1);
		
		if(flag)
		{
			//Setting up vehicle id in model class veriable 
			a1.setVehicleId(new MainService().getidVehicle());
			//Invoking getInsertVehicle method in MainDao 
			a = bsl.getInsertVehicle(a1);
		    if(a==null)
		       {
		        	throw new ServerProblemException();
		        }
		}
		else
		{
			throw new Mainexception();
		}
		return a;
		}//end of insertdataVehicle
	
	/**
	 *Construct   ::  getidVehicle : void ---> integer
	 *purpose     ::  This method will generate vehicle id
	 * @return m
	 */
		private int getidVehicle()
		{
			//Creating random class object 
			Random rand = new Random();
			//m holds generated id
			int m = rand.nextInt(900000) + 100000;
			return m;   
		}//end of getidVehicle method
		
	/**
	 * Construct   ::  detailsVehicle : Object ---> Object
	 * Purpose     ::  This method will fetch vehicle details
	 * @param abc  ::  abc stands for MainModel object
	 * @return mm  ::  mm stands for MainModel object
	 * @throws ServerProblemException
	 */
		public MainModel detailsVehicle(MainModel abc)throws ServerProblemException
		{
			//Creating Maindao object reference
			MainDao md=new MainDao();
			//Creating MainModel object reference
			MainModel mm=new MainModel();
			//Invoking getDetailsVehicle method
			mm=md.getDetailsVehicle(abc);
			/**
			 * If object references is null it will  throw exception
			 */
			if(mm==null)
			{
				throw new ServerProblemException();
			}
			return mm;
		}//end of detailsVehicle method
		
	/**
	 * 	Contract   ::  updateVehicle :Object--->boolean
	 * Purpose     ::  This method will update vehicle details
	 * @param abc  ::  abc stands for MainModel object
	 * @return ar1 ::  ar1 stands for boolean value

		**/
		public boolean updateVehicle(MainModel abc)throws Mainexception
		{
			boolean ar1=false;
			//creating MainDao object reference
			MainDao md=new MainDao();
			//Invoking getUpdateVehicle method
			 ar1=md.getUpdateVehicle(abc);
			//If boolean value is false it will throw exception
			if(!ar1)
			{
				throw new Mainexception("Data not inserted,Please try again.");
			}
			
			return ar1;

	     }//end of updateVehicle method
		
		/**
		 * 	Contract   :: getDeleteVehicle  :Object--->boolean
		 * Purpose     ::  This method will deletes vehicle details
		 * @param m  ::  m stands for MainModel object
		 * @return ar1 ::  ar1 stands for boolean value
		 * @throws Mainexception
		 */

		public boolean getDeleteVehicle(MainModel m)throws Mainexception,InsuranceException
           {
			boolean ar1=false;
			//creating MainDao object reference
			MainDao md=new MainDao();
			//Invoking isInsuredVehicleDAO method 

			boolean isInsuredVehicle = md.isInsuredVehicleDAO(m);
			//If boolean value is false it will throw exception
			if(isInsuredVehicle)
			{
				throw new InsuranceException();
			}
			else
			{
				//Invoking getDeleteVehicle method 
				ar1=md.getDeleteVehicle(m);
				//If boolean value is true it will throw exception
				if(!ar1)
				{
					//Invoking getDeleteVehicle method 
					ar1=md.getDeleteVehicle(m);
					//If boolean value is true it will throw exception
					if(!ar1)
					{
						throw new Mainexception("Sorry! Details do not exist.");
					}
				}
			}
			
			return ar1;
		}//end of getDeleteVehicle method
		
		/**
		 * 	Contract   :: gtVehicleId  :Object--->ArrayList
		 * Purpose     ::  This method will get vehicle Id
		 * @param m  ::  m stands for MainModel object
		 * @return ar1 ::  ar1 stands for ArrayList
		 * @throws Mainexception
		 */

		public ArrayList<Integer> gtVehicleId(MainModel m)throws Mainexception
		{
			ArrayList<Integer> ar1=new ArrayList<Integer>();
			//creating MainDao object reference
			MainDao md=new MainDao();
			//Invoking getVehicle method
			ar1=md.getVehicleId(m);
			//If ArrayList is empty it will throw exception
			if(ar1.isEmpty())
			{
				throw new Mainexception("You don't have any registered vehicle.");
			}
			return ar1;
		}
		//end of  gtVehicleId method
	
	/********************************************************************************************************************/

	/**---------------------------------------Buy Insurance--------------------------------------------------------*/
		/**
		 * Buy Insurance 
		 * @author 594486
		 * @author 592399
		 * @author 
		 */	
	
	/*
	 * Contract  ::generateInsuranceId  :void---->String
	 * Purpose   ::This method will generate insurance id
	 * 
	 */
	private String generateInsuranceId()
	{ 
		String insId  = ""; //insId ---->Insurance id
		String date = String.valueOf(new java.util.Date());
		//invoking genrateId method
		insId  =  new MainService().genrateId(date);
		return insId;
	} //end of generateInsuranceId method
	
	/**
	 * contract     ::genrateId :String--->String
	 * Purpose      :: This method will generate id from timestamp
	 * @param date   current date and time
	 * @return str   
	 */
	private String genrateId(String date)
	{
		//str holds timestamp in formeted way
		String str="";
		String str3 = date.substring(11, 13);
		String str4 = date.substring(14, 16);
		String str5 = date.substring(17, 19);
		MainService g = new  MainService();
		String ss=g.ran();
		str=ss.charAt(0)+str3+ss.charAt(1)+str4+ss.charAt(2)+str5+ss.charAt(3);
		return str;
	}//end of genrateId
	/**
	 * Cotract  ::ran  void--->String
	 * Purpose  :: This method will generate random number
	 * @return
	 */
	private String ran()
	{
		String ss="";
		Random rn = new Random();
		int a=333;
		while(a<=1000)
		{
			a = rn.nextInt(10000);
			ss = String.valueOf(a);
		}
		return ss;
	}//end of ran method
	
/**
 * contact ::	isBodyInsuranceExists  Object  --->boolean
 * Purpose  :: This method will search any body insurance exists in system
 * @param model
 * @return
 * @throws Mainexception
 */
	public boolean isBodyInsuranceExists(MainModel model)throws Mainexception
	{
		boolean flag=false;
		flag=new MainDao().searchBodyInjuryInsurance(model);
		if(flag)
		{
			throw new Mainexception();
		}
		return flag;
	}//end of isBodyInsuranceExists method
	
	/**
	 * Contract :: searchInsurances  Object--->List
	 * Purpose  :: This method search insurance
	 * @param mm
	 * @return
	 * @throws Mainexception
	 */
	public ArrayList<MainModel>searchInsurances(MainModel mm)throws Mainexception
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		al=new MainDao().insuranceList(mm);
		if(al.isEmpty())
		{
			throw new Mainexception();
		}
		return al;
	}//end of searchInsurances
	
/**
 * Contact  ::	registredVehicleList  :object--->List
 * Purpose :: This method will list out all the vehicle against a user
 * @param model
 * @return al   ArrayList object 
 * @throws Mainexception
 */
	public ArrayList<MainModel> registredVehicleList(MainModel model)throws Mainexception
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		al=new MainDao().listRegirteredVehicle(model);
		if(al.isEmpty())
		{
			throw new Mainexception();
		}
		return al;
	}//end of registredVehicleList method
	
  
// Buy Vehicle Insurance
	/**
	 * Contract ::buyVehicleInsurance  :Object--->Object
	 * Purpose  ::This method for buying insurance
	 * @param mainModel  MainModel class object
	 */
	public MainModel buyVehicleInsurance(MainModel mainModel)throws InvalidCoverageAmountException,
	                                                                                   ServerProblemException
	{
		boolean is_buy_vecIns_success  =  false;
		//creating model class object
		MainModel model  =  new MainModel();
		//fetching road price of vehicle
		double onRoadPrice  =  new MainDao().getRoadPriceOfVehicle(mainModel.getVehicleId());
		
		/*if coverage amount more than the 80 % of on road price,
		 *then it will throw exception
		 **/
		if(mainModel.getCoverageAmount() > (onRoadPrice*0.8))
		{
			throw new InvalidCoverageAmountException(onRoadPrice);
		}
		//if coverage amount not more than road price then user can buy insurance
		else
		{
			model  =  new MainDao().getCustomerDetails(mainModel.getUserId());
			String dob  =  model.getDateOfBirth();
			String licensedStatus  =  model.getLicenseStatus();
			double monthlyPremium  =  0.0; //monthlyPremium ---->Monthly premium for a insurances
			monthlyPremium  =  new MainService().calculatePremiumOfVD(licensedStatus,dob,mainModel.getCoverageAmount(),mainModel.getDuration());
			String insuranceId  =  new MainService().generateInsuranceId();
			String startDate  =  new MainService().find_To_Date();
			
			model.setInsuranceId(insuranceId);
			model.setCoverageAmount(mainModel.getCoverageAmount());
			model.setDuration(mainModel.getDuration());
			model.setStartDate(startDate);
			model.setMonthlyPremium(monthlyPremium);
			model.setInsuranceType(mainModel.getInsuranceType());
			//statusOfInsurance --->it will holds two values i.e active or surrenderd 
			String statusOfInsurance="active";
			model.setStatusOfInsurance(statusOfInsurance);
			model.setUserId(mainModel.getUserId());
			model.setVehicleId(mainModel.getVehicleId());
			is_buy_vecIns_success=new MainDao().buyVehicleInsurance(model);
			if(!is_buy_vecIns_success)
			{
				throw new ServerProblemException();
				//return model;
			}
			else
				return model;
		}
		
		 
		//return model;
	}
	
//Buy Body Injury Insurances
	/**
	 * Contract :: buyBodyInjuryInsurance Object--->Object
	 * Purpose  :: This method is for buying insurance
	 * @param mainModel MainModel class object  
	 */
	
	public MainModel buyBodyInjuryInsurance(MainModel mainModel)throws InvalidCoverageAmountException,
	                                                                                    ServerProblemException
	{
		boolean is_buy_bodyInsurances_success  =  false;
		//creating model class object
		MainModel model = new MainModel();
		
		/*if coverage amount more than 40lacs
		 *then it will throw exception
		 **/
		if(mainModel.getCoverageAmount() > 4000000.00)
		{
			throw new InvalidCoverageAmountException();
		}
		//if coverage amount not more than road price then user can buy insurance
		else
		{
			model  =  new MainDao().getCustomerDetails(mainModel.getUserId());
			String dob  =  model.getDateOfBirth();
			String licensedStatus  =  model.getLicenseStatus();
			double monthlyPremium  =  0.0; //monthlyPremium ---->Monthly premium for a insurances
			monthlyPremium  =  new MainService().calculatePremiumOfBodyInjury(licensedStatus,dob,mainModel.getCoverageAmount(),mainModel.getDuration());
			String insuranceId  =  new MainService().generateInsuranceId();
			String startDate  =  new MainService().find_To_Date();
			
			model.setInsuranceId(insuranceId);
			model.setCoverageAmount(mainModel.getCoverageAmount());
			model.setDuration(mainModel.getDuration());
			model.setStartDate(startDate);
			model.setMonthlyPremium(monthlyPremium);
			model.setInsuranceType(mainModel.getInsuranceType());
			//statusOfInsurance --->it will holds two values i.e active or surrenderd 
			String statusOfInsurance="active";
			model.setStatusOfInsurance(statusOfInsurance);
			model.setUserId(mainModel.getUserId());
			model.setVehicleId(mainModel.getVehicleId());
			is_buy_bodyInsurances_success=new MainDao().buyInsuranceOfBodyInjury(model);
			if(!is_buy_bodyInsurances_success)
			{
				throw new ServerProblemException();
			}
		}
		
		 
		return model;
	}
	
/**
 * Constract ::	buyLiabilityInsurance :Object--->Object
 * Purpose   :: This method for buying insurance
 * @param mainModel
 * @return
 * @throws InvalidCoverageAmountException
 * @throws ServerProblemException
 */
	
	public MainModel buyLiabilityInsurance(MainModel mainModel)throws InvalidCoverageAmountException,
	                                                                                     ServerProblemException
	{
		boolean is_buy_liabilityIns_success  =  false;
		//creating model class object
		MainModel model  =  new MainModel();
		
		/*  if coverage amount more than the 70 lac of on road price,
		 *  then it will throw exception
		 **/
		if(mainModel.getCoverageAmount() > 7000000.00)
		{
			throw new InvalidCoverageAmountException();
		}
		//if coverage amount not more than road price then user can buy insurance
		else
		{
			model  =  new MainDao().getCustomerDetails(mainModel.getUserId());
			String dob  =  model.getDateOfBirth();
			String licensedStatus  =  model.getLicenseStatus();
			double monthlyPremium  =  0.0; //monthlyPremium ---->Monthly premium for a insurances
			monthlyPremium  =  new MainService().calculatePremiumOfLiability(licensedStatus,dob,mainModel.getCoverageAmount(),mainModel.getDuration());
			String insuranceId  =  new MainService().generateInsuranceId();
			String startDate  =  new MainService().find_To_Date();
			
			model.setInsuranceId(insuranceId);
			model.setCoverageAmount(mainModel.getCoverageAmount());
			model.setDuration(mainModel.getDuration());
			model.setStartDate(startDate);
			model.setMonthlyPremium(monthlyPremium);
			model.setInsuranceType(mainModel.getInsuranceType());
			//statusOfInsurance --->it will holds two values i.e active or surrenderd 
			String statusOfInsurance="active";
			model.setStatusOfInsurance(statusOfInsurance);
			model.setUserId(mainModel.getUserId());
			model.setVehicleId(mainModel.getVehicleId());
			is_buy_liabilityIns_success = new MainDao().buyInsuranceForLiability(model);
			if(!is_buy_liabilityIns_success)
			{
				throw new ServerProblemException();
			}
		}
		
		 
		return model;
	}
	
	
	/***************************View Insurance****************************/
	
	
	/*
	 * Code for View Insurance
	 * By 594621
	 * Kuntal
	 * 
	 */
	public ArrayList<MainModel> viewVehicleInsurance( String uid )
	{
		ArrayList<MainModel> al = new ArrayList<MainModel>();
		MainDao md = new MainDao();
		al = md.getVehicleInsurance(uid);
		
		
		return al;
	}
	
	
	
	public ArrayList<MainModel> viewBodyInjuryInsurance( String uid )
	{
		ArrayList<MainModel> al = new ArrayList<MainModel>();
		MainDao md = new MainDao();
		al = md.getBodyInjuryInsurance(uid);
		
		
		return al;
	}
	
	
	
	public ArrayList<MainModel> viewLiabilityInsurance( String uid )
	{
		ArrayList<MainModel> al = new ArrayList<MainModel>();
		MainDao md = new MainDao();
		al = md.getLiabilityInsurance(uid);
		
		
		return al;
	}
	
	public MainModel viewDetailsOfIns(String insId)
	{
		MainModel mmm = new MainModel();
		MainDao mdd = new MainDao();
		mmm=mdd.viewIns(insId);
		return mmm;
	
	}
	
	
	/****************************End of View Insurance**************************/
	
	
	/**************************Delete Insurances************************/
	
	/**
	 * by Kuntal
	 * Emp Id: 594621
	 * 
	 * */
	public boolean delDetailsOfIns( String insId )
	{
		boolean f=false;
		MainDao mdd = new MainDao();
		f = mdd.delIns(insId);
		
		return f;
	}
	
	
	
	/*********************end of delete Insurances***********************/
	
	
	
	private String find_To_Date()
	{
		//to_date ----->To day's date
		String to_date = ""; 
		Date date  =  new Date();
		Formatter fmt  =  new Formatter();
		to_date  =  fmt.format("%td/%tm/%tY",date,date,date).toString();
		return to_date;
	}
//Premium calculation details
	//dob --->DATE OF BIRTH 
	//covAmt ---->COVERAGE AMOUNT
	private double calculatePremiumOfBodyInjury(String licensedStatus,String dob,double covAmt,int duration)
	{
		double monthlyPremium  =  0.0;
		
		int BASE_RATE  =  5000;
		int age  =  new MainService().calculateAge(dob);
		float licenseFactor  =  new MainService().getLicensesFactor(licensedStatus);  /**********************/
		float ageFactor  =  new MainService().getAgeFactor(age);    /******************************/
		double totalPremium  =  BASE_RATE*licenseFactor*ageFactor;
		double totalAmount  =  covAmt+totalPremium;
		monthlyPremium  =  totalAmount/duration;
		/*
		 * Convert monthly premium into dacimal format of ########.##
		 */
		return Double.parseDouble(new DecimalFormat("########.##").format(monthlyPremium));
	}
	
	//dob --->DATE OF BIRTH 
	//covAmt ---->COVERAGE AMOUNT
	private double calculatePremiumOfVD(String licensedStatus,String dob,double covAmt,int duration)
	{
		double monthlyPremium  =  0.0;
		
		int BASE_RATE  =  4000;
		int age  =  new MainService().calculateAge(dob);
		float licenseFactor  =  new MainService().getLicensesFactor(licensedStatus); /**********************/
		float ageFactor  =  new MainService().getAgeFactor(age);  /******************************/
		double totalPremium  =  BASE_RATE*licenseFactor*ageFactor;
		double totalAmount  =  covAmt+totalPremium;
		monthlyPremium  =  totalAmount/duration;
		/*
		 * Convert monthly premium into dacimal format of ########.##
		 */
		return Double.parseDouble(new DecimalFormat("########.##").format(monthlyPremium));
	}
	
	/**
	 * 
	 * @param licensedStatus
	 * @param dob
	 * @return
	 */
	private double calculatePremiumOfLiability(String licensedStatus,String dob,double covAmt,int duration)
	{
		double monthlyPremium  =  0.0;
		
		int BASE_RATE  =  2000;
		int age  =  new MainService().calculateAge(dob);
		float licenseFactor  =  new MainService().getLicensesFactor(licensedStatus);
		float ageFactor  =  new MainService().getAgeFactor(age);
		double totalPremium  =  BASE_RATE*licenseFactor*ageFactor;
		double totalAmount  =  covAmt+totalPremium;
		monthlyPremium  =  totalAmount/duration;
		/*
		 * Convert monthly premium into dacimal format of ########.##
		 */
		return Double.parseDouble(new DecimalFormat("########.##").format(monthlyPremium));
	}
	
	
	/**
	 * contract :: calculateAge String-------------->int
	 * purpose  :  This class evaluate age from date of birth
	 * @param dob  date of birth
	 * @return age
	 */
	private int calculateAge(String dob)
	{
		int age  =  0;
		//Creating instance of Date class
		Date date  =  new Date();
		//Creating instance of Calender class
		Calendar cal  =  Calendar.getInstance();
		//Set the date
		cal.setTime(date);
		//Fetching Current Year
		int cYear  =  cal.get(Calendar.YEAR); //cYeAR --->CURRENT YEAR
		//Fetching Year OF DATE OF BIRTH
		int fYear  =  Integer.parseInt(dob.substring(6,10));  //fYear ---->FROM YEAR
		//FIND AGE BY DIFFERENCIATE CURRENT YEAR AND DATE OF BIRTH YEAR 
		age  =  cYear-fYear;
		return age;
	}
	
	/**
	 * contract :: getLicensesFactor String-------------->float
	 * purpose  : This class evaluate factor based on license status
	 * @param status
	 * @return factor
	 */
	private float getLicensesFactor(String status)
	{
		float factor  =  0.0f;
		if(status.equalsIgnoreCase("Active"))
		factor  =  0.5f;
		if(status.equalsIgnoreCase("Inactive"))
		factor  =  0.75f;
		if(status.equalsIgnoreCase("Suspended"))
		factor  =  1.0f;
		if(status.equalsIgnoreCase("Revoked"))
		factor  =  2.0f;
		return factor;
	}
	
	/**
	 * contract :: getAgeFactor int-------------->float
	 * purpose  : This class evaluate age factor based on age
	 * @param age
	 * @return float
	 */
	private float getAgeFactor(int age)
	{
		float ageFactor  =  0.0f;
		if(age<25)
			ageFactor  =  1.55f;
		if(age>=25 && age<46)
			ageFactor  =  0.99f;
		if(age>=46 && age<61)
			ageFactor  =  1.2f;
		if(age>=61 && age<99)
			ageFactor  =  1.2f;
		return ageFactor;
		
	}

/*-----------------------------Nominee---------------------------------------------------------*/
	
	/**
	 * construct:: addNomineeBSL :Object----->int
	 * purpose : to add nominee to a particular insurance
	 * @param mainModel
	 * @return noOfRows
	 */
	public boolean addNomineeBSL(MainModel mainModel)throws ServerProblemException
	{
		boolean temp=false;
		temp  =  new MainDao().addNominee(mainModel);
		//if details not inserted in database then it will throw exception
		if(!temp)
		{
			throw new ServerProblemException();
		}
		return temp;
	}//end of addNomineeBSL
	/**
	 * Contract :: viewNominee Object--->Object
	 * Purpose :This method will show nominee details
	 * @param mainModel
	 * @return
	 * @throws Mainexception
	 */
	public MainModel viewNominee(MainModel mainModel)throws Mainexception
	{
		MainModel mm=new MainModel();
		mm=new MainDao().viewNominee(mainModel);
		if(mm==null)
		{
			throw new Mainexception();
		}
		return mm;
	}//end of viewNominee
/**
 * Contract ::  updateNomineeDetailsBSL Object-->boolean
 * Purpose  :: This method will show a particular nominee details	
 * @param mm
 * @return
 * @throws ServerProblemException
 */
	public boolean updateNomineeDetailsBSL(MainModel mm)throws ServerProblemException
	{
		boolean flag=false;
		flag=new MainDao().updateNomineeDetails(mm);
		if(!flag)
		{
			throw new ServerProblemException();
		}
		return flag;
	}//end of updateNomineeDetailsBSL
	
	/*------------------------START tag-----BankDetails Service-------------------------------------------------------*/
	/**
	 * contract:: getInsertBankDetails :Object----->String
	 * purpose : to add bank details to a particular customer
	 * @param MainModel
	 * @return String
	 * @author 592399
	 */	
	public String getInsertBankDetails(MainModel mm_InsertBankdetails){
			String check = "";
			//Creating constructor of MainDao
			MainDao md_InsertBankdetails = new MainDao();
			check = md_InsertBankdetails.insertBankDetails(mm_InsertBankdetails);
			return check;
		}
	/**
	 * contract:: getInsertBankDetails :Object----->String
	 * purpose : to add bank details to a particular customer
	 * @param MainModel
	 * @return String
	 * @author 592399
	 */	
	public MainModel getViewBankDetails(MainModel mm_ViewBankdetails){
		//Creating constructor of MainModel
		MainModel mm_viewbankDetails = new MainModel();
		//Creating constructor of MainDao
		MainDao md_ViewBankdetails = new MainDao();
		boolean check = false;
		mm_viewbankDetails = md_ViewBankdetails.getBankDetails(mm_ViewBankdetails);
		return mm_viewbankDetails;
	}
	/**
	 * contract:: getInsertBankDetails :Object----->String
	 * purpose : to add bank details to a particular customer
	 * @param MainModel
	 * @return String
	 * @author 592399
	 */	
	public boolean getUpdateBankDetails(MainModel mm_UpdateBankdetails){
		//Creating constructor of MainDao
		MainDao md_UpdateBankdetails = new MainDao();
		boolean check = false;
		check = md_UpdateBankdetails.updateBankDetails(mm_UpdateBankdetails);
		return check;
	}
	
	public ArrayList<MainModel>searchInsurancesForNominee(MainModel model)throws Mainexception
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		al=new MainDao().insuranceListForNomination(model);
		if(al.isEmpty())
		{
			throw new Mainexception();
		}
		return al;
	}//end of searchInsurances
	
	/**-----------------------------------------------------------------------------*/

}
