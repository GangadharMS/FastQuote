package com.ilp.proj1.dao;

import com.ilp.proj1.model.MainModel;
import com.ilp.proj1.utility.MainUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainDao {

	//Creating database connection class object
	MainUtility mainUtility=new MainUtility();


	/**********************************************************************************************************************/
	/*--------------------------------------------------------------------------------------------------------------------*/
	/*****************************submodule one****************************************************************************/
	/**
	 * Customer Registration and Log in
	 * @author 592286
	 * @author 678232
	 * 
	 */	


	/**
	 * @author Debashis-592274
	 * START CODE
	 * SUBMODULE 1
	 */



	//Creating the object of MainUtility class
	MainUtility oc=new MainUtility();
	Connection con=oc.getConnection();
	PreparedStatement psmt=null;



	//Declaration of Variables
	int t=0;
	boolean valid=false;




	 
	/**
	 * Construct  :: getLogIn : Object ------>ArrayList
	 * purpose    :: This method will  getting log in details
	 * @param  lim  :: MainModel class ArrayList  reference
	 * @return ar ::  ArrayList 
	 */

	public ArrayList<MainModel> getLogIn(MainModel lim)
	{

		//creating ArrayList for model class

		ArrayList<MainModel> ar=new ArrayList<MainModel>();
		ResultSet rs=null;
		try 
		{	

			//Database operation to check User Login Details
            String sql="select * from fq_userlogin where user_id=?";
			String userid=lim.getUserId();
			psmt=con.prepareStatement(sql);
			psmt.setString(1, userid);
			//Executing the Query
            rs=psmt.executeQuery();


          //If query execution is successful then set above user id


			while(rs.next())
			{
				MainModel lim2=new MainModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
				ar.add(lim2);
				//adding to ArrayList
			}


		}

          //If query execution is not successful then catch Exception

		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			
		}
       
		//Closing Connection
		finally
		{
			oc.closeConnection();
		}
       //Returning ArrayList to MainService class
       return ar;
	   }


   //end of getLogIn  method


	/**
	 * Construct  :: updatePassword : Object ------>boolean
	 * purpose    :: This method is for Updating Password
	 * @param  encryptPwd,key  :: String references
	 * @param   np ::MainModel class object  reference
	 * @return valid ::  Boolean value
	 */


	public boolean updatePassword(String encryptPwd,String key,MainModel np)
	{
       try
		{
			//Database operation to check Update Password

			String sql="update fq_userlogin set password=?, key=? where user_id=?";
			psmt=con.prepareStatement(sql);
			String username=np.getUserId();
			String password=encryptPwd;
			String newkey=key;
			psmt.setString(1, password);
			psmt.setString(2, newkey);
			psmt.setString(3, username);
			//Executing the Query
            t=psmt.executeUpdate();


            //If query execution is successful then set above Values and check if t>0


			if(t>0)
			{
				valid=true;
			}
            //else set valid to false

            else
			{
				valid=false;
			}
		}
		//If query execution is not  successful then catch Exception

		catch(SQLException e)
		{
			
		}

        finally
		{
			oc.closeConnection();
			//Closing Connection
		}

      return valid;	
      //Returning Boolean Value
	}

    //End of updatePassword method

	/**
	 * Construct    :: getUpdate : Object ------>Void
	 * purpose      :: This method is for Updating count
	 * @param count ::  Integer  reference
	 * @param userid:: String references
	 * @return 
	 */

	public void getUpdate(int count, String userid)
	{
		try
		{
			//Database operation to check Update Count

			String sql="update fq_userlogin set count=? where user_id=?";
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, count);
			psmt.setString(2,userid);
			//Executing the Query
            psmt.executeUpdate();

		}
         //If execution is not successful then catch Exception

		catch(SQLException e)
		{
			
		}
        finally
		{
			oc.closeConnection();
			//closing Connection
		}
        }
	
	//End of getUpdate method
	
	/**
	 * Construct        :: getQuestion : Object ------>ArrayList
	 * purpose          :: This method is for getting security question
	 * @param userid,dob:: String references
	 * @return ar       :: ArrayList
	 */
	
	public ArrayList<MainModel> getQuestion(String userid, String dob)
	{
		
		//Creating the ArrayList of MainModel
		
		ArrayList<MainModel> ar=new ArrayList<MainModel>();
		
		try{
			
			//Database operation to get Security question
			String sql="select security_question, security_answer from fq_customerdetails where user_id=? and dob=? ";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, userid);
			psmt.setString(2, dob);
			//Executing the Query
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
			
				MainModel mm=new MainModel(rs.getString(1), rs.getString(2), userid, dob);
				ar.add(mm);
				//Storing the result to ArrayList if execution is successful
			}
		}
		//If execusion is not successful then catch Exception
		catch(SQLException e)
		{
			
		}
		//Returning ArrayList
		return ar;
	}
	
	//End of getQuestion method
	
	/**
	 * Construct        :: getPassword : Object ------>ArrayList
	 * purpose          :: This method is for getting Password
	 * @param userid    :: String references
	 * @return ar       :: ArrayList
	 */
	
	public ArrayList<MainModel> getPassword(String userid)
	{

		//Creating ArrayList of MainModel class

		ArrayList<MainModel> ar=new ArrayList<MainModel>();
		ResultSet rs=null;
		try 
		{	
			//Database operation to get Password
		    String sql="select * from fq_userlogin where user_id=?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, userid);
             
            //Execution of Query
            rs=psmt.executeQuery();


           while(rs.next())
			{
				MainModel lim2=new MainModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
				ar.add(lim2);
				//Storing the result to ArrayList if execution is successful
			}


		}


        //If execution is not successful then catch Exception
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			
		}
		finally
		{
			//Closing Connection
			oc.closeConnection();
		}
     // Returning ArrayList
      return ar;
	}
	
	//End of getPassword  method
	
	/**
	 * Construct         :: getUser : Object ------>String
	 * purpose           :: This method is for getting User
	 * @param userid     :: String reference
	 * @return custName :: String
	 */
	
	public String getUser(String userid)
	{
		//Initializing ResultSet and CustName
		ResultSet rs=null;
		String custName=null;
		try 
		{	
             //Defining sql query
			
          String sql="select name from fq_customerdetails where user_id=?";
			psmt=con.prepareStatement(sql);
			psmt.setString(1, userid);


			//EXECUTING THE QUARY


			rs=psmt.executeQuery();


			//INSERTING DATA TO ARRAYLIST


			while(rs.next())
			{
				custName=rs.getString(1);
			}


		}



		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			
		}




		//CLOSING CONNECTION

		finally
		{
			oc.closeConnection();
		}




		//RETURNING THE ARRAYLIST TO SERVICE CLASS


		return custName;
	}//end of getUser method
	
	

	
	



	/**
	 * @author Debashis-592274
	 * END CODE
	 * SUBMODULE 1
	 */



	/**-----------------------------------------------------------------------------------------------*/


	/**
	 * 678232
	 * SUBMODULE 1.2
	 * 
	 */

	/*
	 * CODE TO CHECK WHETHER USERID IS EXISTING OR NOT AT THE TIME 
	 * OF GENERATING USERID
	 */
 /**
  * 
  */
	public boolean check(String uname)
	{
		boolean valid=true;
		MainUtility obj=new MainUtility();
		Connection conn = obj.getConnection();
		Statement smt=null;
		ResultSet rs1=null;
		try
		{
			smt=conn.createStatement();
			String sql="select * from FQ_UserLogin where user_id='"+uname+"'";
			rs1=smt.executeQuery(sql); //storing the data to result set
			if(!rs1.next())
			{
				valid=false;
			}
			obj.closeConnection();
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}

		return valid;		

	}

	/*
	 * CODE TO INSERT USER DATA AT THE TIME OF REGISTRATION.
	 * 
	 */
	public boolean getinsertregistration(MainModel rm) 
	{
		// TODO Auto-generated method stub
		MainUtility obj=new MainUtility();
		Connection cnn = obj.getConnection();
		try 
		{
			String sql=null;
			sql="insert into FQ_CustomerDetails values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt=cnn.prepareStatement(sql);
			pstmt.setString(1,rm.getUserId());
			pstmt.setString(2,rm.getUserName());
			pstmt.setString(3,rm.getGender());
			pstmt.setString(4,rm.getDateOfBirth());
			pstmt.setString(5,rm.getAddress());
			pstmt.setString(6, rm.getLicensedFirst());
			pstmt.setString(7, rm.getLicenseStatus());
			pstmt.setString(8, rm.getCustomerSSN());
			pstmt.setString(9, rm.getOccupation());
			pstmt.setDouble(10, rm.getAnnualIncome());
			pstmt.setString(11, rm.getHigherEducation());
			pstmt.setString(12, rm.getPhoneNumber());
			pstmt.setString(13, rm.getEmailId());
			pstmt.setString(14,rm.getCustomerImage());
			pstmt.setString(15,rm.getSecurityQuestion());
			pstmt.setString(16,rm.getSecurityQusAnswer());
			pstmt.executeQuery();
			obj.closeConnection();
			valid=true;
		}	
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	//End of getinsertregistration method
	/**
	   * Construct  :: getinsertlogin  Object --->ArrayList
	   * Purpose    :: This method will insert data to login table
	   * @param rm1 :: rm1 is model class object reference 
	   * @return    :: ArrayList
	   */

	public ArrayList<MainModel> getinsertlogin(MainModel rm1)
	{
		MainUtility obj=new MainUtility();
		Connection cnn = obj.getConnection();
		ArrayList<MainModel> arr=new ArrayList<MainModel>();
		try 
		{
			//Executing SQL Query to insert details 
			String sql=null,sql1;
			sql="insert into FQ_UserLogin values(?,?,?,?,?)";
			PreparedStatement pstmt=cnn.prepareStatement(sql);
			pstmt.setString(1,rm1.getUserId());
			pstmt.setString(2,rm1.getPassword());
			pstmt.setString(3,rm1.getStatus());
			pstmt.setLong(4,rm1.getCount());
			pstmt.setString(5,rm1.getKey());
			pstmt.executeQuery();
			sql1="select * from FQ_UserLogin where user_id=?";
			PreparedStatement pstmt1=cnn.prepareStatement(sql1);
			pstmt1.setString(1,rm1.getUserId());
			ResultSet rs=pstmt1.executeQuery();
			//If execution is successful then set above values
			while(rs.next())
			{
				MainModel ob=new MainModel(rs.getString(1),rs.getString(2));
				arr.add(ob);
				//adding to ArrayList
			}

			obj.closeConnection();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return arr; 
	}
//End of getinsertlogin method
	

	/**
	   * Construct  :: getdata1  Object --->ArrayList
	   * Purpose    :: This method will fetch data at the time of Update
	   * @param uid :: String  reference 
	   * @return arr  :: ArrayList
	   */


	public ArrayList<MainModel> getdata1(String uid)
	{
		MainUtility obj=new MainUtility();
		Connection cnn = obj.getConnection();
		ArrayList<MainModel> arr=new ArrayList<MainModel>();
		try 
		{
			//Executing SQL query
			String sql=null;
			sql="select user_id,name,email_id,phone_no,gender,dob,address,licence_first,licence_status,ssn,occupation,annual_income,highest_education,security_question,security_answer from FQ_CustomerDetails where user_id=(?)";
			PreparedStatement pstmt=cnn.prepareStatement(sql);
			pstmt.setString(1,uid);
			ResultSet rs=pstmt.executeQuery();
			//If execution is successful then set above value
			while(rs.next())
			{
				MainModel ob=new MainModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getDouble(12),rs.getString(13),rs.getString(14),rs.getString(15));
				arr.add(ob);
				valid=true;
			}
			obj.closeConnection();
		} 

		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}

//End of getdata1 method

	/*
	 * CODE TO RETURN STATUS WHICH HELPS TO THROW EXCEPTION
	 * 
	 */
	

	public boolean getstatus()
	{
		return valid;
	}


	
	/**
	   * Construct  ::updatedata  Object --->Boolean
	   * Purpose    :: This method will Update user data
	   * @param uid :: String  reference 
	   * @param rm  :: Model reference
	   * @return valid   :: Boolean
	   */

	public boolean updatedata(MainModel rm,String uid) {
		// TODO Auto-generated method stub
		String sql=null;
		MainUtility obj=new MainUtility();
		Connection cnn = obj.getConnection();
		try 
		{
			//executing SQL Query
			sql="update FQ_CustomerDetails set email_id=?,phone_no=?,address=?,licence_status=?,occupation=?,annual_income=?,highest_education=? where user_id=?";
			PreparedStatement pstmt=cnn.prepareStatement(sql);
			pstmt.setString(1,rm.getEmailId());
			pstmt.setString(2,rm.getPhoneNumber());
			pstmt.setString(3,rm.getAddress());
			pstmt.setString(4,rm.getLicenseStatus());
			pstmt.setString(5,rm.getOccupation());
			pstmt.setDouble(6,rm.getAnnualIncome());
			pstmt.setString(7,rm.getHigherEducation());
			pstmt.setString(8,uid);
			pstmt.executeUpdate();
			//If execution is successful then set above values
			valid=true;
			 obj.closeConnection();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
	}

	//End of updatedata method
	
	
	/**
	   * Construct   ::deletepassword  Object --->Object
	   * Purpose     :: This method De-activates Account
	   * @param uid  :: String  reference 
	    * @return    :: Object
	   */
	
	public MainModel deletepassword(String uid)
	{
		 MainModel ob=null;
		String sql=null;
		MainUtility obj=new MainUtility();
		Connection cnn = obj.getConnection();
		try 
		{
			//Executing SQL Query
			  sql="select password,key from fq_userlogin where user_id=?";
			  PreparedStatement pstmt=cnn.prepareStatement(sql);
			  pstmt.setString(1,uid);
			  ResultSet rs1=pstmt.executeQuery();
			//If execution is successful then set above value
			  while(rs1.next())
			  {
				 ob=new MainModel(rs1.getString(1),rs1.getString(2));
				 valid=true;
			  }
			  obj.closeConnection();
			 
			  
		}
			  catch (SQLException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return ob;
		}
// End of deletepassword method
	
	
	/**
	   * Construct   ::remove  Object --->Void
	   * Purpose     :: This method will set the Status
	   * @param uid  :: String  reference 
	    * @return    :: Void
	   */
	
	  public void remove(String uid)
	  {
		  String sql=null;
			MainUtility obj=new MainUtility();
			Connection cnn = obj.getConnection();
			try 
			{
				//Executing SQL Query
				  sql="update  fq_userlogin set status=? where user_id=?";
				  PreparedStatement pstmt=cnn.prepareStatement(sql);
				  pstmt.setString(1,"INACTIVE");
				  pstmt.setString(2,uid);
				  pstmt.executeQuery();
				//If execution is successful then set above values
				  obj.closeConnection();
				  valid=true;
			}
			  catch (SQLException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	  
	  public ArrayList<MainModel> gangaremove(String userid)
	  {
		  String sql=null;
		 
		  ArrayList<MainModel> ar1=new ArrayList<MainModel>();
			MainUtility obj=new MainUtility();
			Connection cnn = obj.getConnection();
			try 
			{
				sql="select INSURANCE_ID,TYPE_OF_INSURANCE from FQ_Buy_Insurance "+
				"where USER_ID=?";
				PreparedStatement pstmt=cnn.prepareStatement(sql);
				  pstmt.setString(1,userid);
				  ResultSet rs1=pstmt.executeQuery();
				  while(rs1.next())
				  {
					  MainModel ob = new MainModel();
					 ob.setInsuranceId(rs1.getString(1));
					ob.setInsuranceType(rs1.getString(2));
						ar1.add(ob);
					 valid=true;
				  }
					//If execution is successful then set above values
					  obj.closeConnection();
				
			}
			 catch (SQLException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return ar1;
	  }
	  
	  
	  
	  
	 // End of  remove method
	  
	  /**
	   * Construct   ::actacc  Object --->String
	   * Purpose     :: This method is for activate Account
	   * @param uid,password :: String  references 
	    * @return    :: String
	   */
	  
	  public String actacc(String userid,String password)
	  {
		  ArrayList<MainModel> arr=new ArrayList<MainModel>();
		  //MainModel ob=null;
		  String pwd=null;
		  String sql=null;
			MainUtility obj=new MainUtility();
			Connection cnn = obj.getConnection();
			try 
			{ 
				//Executing SQL Query
				sql="select password from fq_userlogin where user_id=?";
				PreparedStatement pstmt=cnn.prepareStatement(sql);
				  pstmt.setString(1,userid);
				  ResultSet rs=pstmt.executeQuery();
				//If execution is successful then set above value
				  while(rs.next())
				  {
					pwd=rs.getString(1);
					// arr.add(ob);
					if(pwd.equals(password))
					 valid=true;
				  }
				  obj.closeConnection();
			}
				  catch (SQLException e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	  
			return pwd;
	  }
// End of actacc method
	  
	  /**
	   * Construct   ::activate  Object --->String
	   * Purpose     :: This method is for setting status
	   * @param userid :: String  reference 
	    * @return    :: String
	   */
	  
	  public String activate(String userid)
	  {
		  String sql=null,stat=null;
			MainUtility obj=new MainUtility();
			Connection cnn = obj.getConnection();
			try 
			{ 
				//Executing SQL Query
				sql="select status from fq_userlogin where user_id=? ";  
				PreparedStatement pstmt=cnn.prepareStatement(sql);
				pstmt.setString(1,userid);
				ResultSet rs=pstmt.executeQuery();
				//If execution is successful then set above value
				  while(rs.next())
				  {
					  stat=rs.getString(1);
				  }
				  obj.closeConnection();
			}
			catch (SQLException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			return stat;
				  
	  }
	  //End of activate method
	  
	  
	  /**
	   * Construct   :: activate1  Object --->String
	   * Purpose     :: This method is for Updating Account
	   * @param userid :: String  reference 
	    * @return    :: String
	   */
	  public boolean activate1(String userid)
	  {
		  String sql=null;
		  boolean ss=false;
			MainUtility obj=new MainUtility();
			Connection cnn = obj.getConnection();
			try 
			{
				//executing SQL Query
				  sql="update  fq_userlogin set status=? where user_id=?";
				  PreparedStatement pstmt=cnn.prepareStatement(sql);
				  pstmt.setString(1,"ACTIVE");
				  pstmt.setString(2,userid);
				  pstmt.executeQuery();
				//If execution is successful then set above values
				  obj.closeConnection();
				  ss=true;
			}
			  catch (SQLException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			  return ss;
	  }
	 //End of activate1 method
	
/**
 * END OF 678232
 * 	
 */
	
	

	/*****************************************************************************************************************/		
	/**----------------------------------Sub-Module Two-------------------------------------------------*/

	/**
	 * Vehicle Registration 
	 * @author 678271
	 * @author 677398
	 */	
	 
	  /**
	   * construct :: searchVehicleNumber : Object ----> boolean
	   * purpose    : This method will search wheather vehicle number is already registered 
	   * 
	   */
	  public boolean searchVehicleNumber(MainModel obj)
	  {
		  boolean temp = true;
		    //Creating MainUtility object
		  MainUtility utilityObj = new MainUtility();
		   //conn --->Connection class object references
		  Connection conn=null; 
		    
		  ResultSet rs = null;
		  try
		  {
			  //Invoking actual database connection
			  conn = utilityObj.getConnection();
			   //Query for fetching vehicle number
			  String sql_query="select VEHICLE_NUMBER from FQ_Vehicle_Details where VEHICLE_NUMBER=?";
			  PreparedStatement pst = conn.prepareStatement(sql_query);
			  pst.setString(1,obj.getVehicleNumber());
			  rs = pst.executeQuery();
			  //If vehicle number already registered in system then set temp value as false
			  if(rs.next())
			  {
				  temp = false;
			  }
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  return temp;
	  }//end of searchVehicleNumber method
	  
	  /**
	   * Construct  :: getInsertVehicle  Object --->Object
	   * Purpose    :: This method will insert vehicle data
	   * @param a1  :: 'a1' is model class object reference which holds vehicle information
	   * @return    :: model
	   */
	  public MainModel getInsertVehicle(MainModel a1) {
		  //a variable for storingg number of rows update after excuteing of query
	        int a = 0; 
	        //Creating MainModel class object reference
	        MainModel model=new MainModel();
			MainUtility mu=null;
			try {

				mu=new MainUtility();
				//Invoking actual database connection
				Connection con=mu.getConnection();
				//Database operation 
				PreparedStatement pst = con.prepareStatement("insert into FQ_Vehicle_Details values(?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1,a1.getVehicleId());
				pst.setString(2,a1.getVehicleNumber());
				pst.setString(3,a1.getMaker());
				pst.setString(4,a1.getModel());
				pst.setInt(5,a1.getManufacturingYear());
				pst.setString(6,a1.getType());
				pst.setDouble(7,a1.getRoadPrice());
				pst.setString(8, a1.getImageOfNumberPlate());
				pst.setString(9,a1.getPrimaryUseOfVehicle());
				pst.setString(10, a1.getUserId());
				a=pst.executeUpdate();
                //If query execution is successful then set below details
				if(a>0)
				{
					//setting up vehicle id in model class variable 
					model.setVehicleId(a1.getVehicleId());
					//setting up Maker in model class variable 
					model.setMaker(a1.getMaker());
					//setting up Model in model class variable 
					model.setModel(a1.getModel());
				}
				//else set model class object reference as null
				else
				{
					model=null;
				}
				
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
			//closing connection
			finally
			{
				mu.closeConnection();
			}
		 return model;
		}//end of getInsertVehicle method
		
		/**
		 * Construct  :: getDetailsVehicle  : Object ------> Object
		 * purpose    :  This method will  fetch vehicle vehicle details
		 * @param a1  : MainModel class object references
		 * @return m2 : Object reference
		 */
		public MainModel getDetailsVehicle(MainModel a1)
		{
			//Creating MainModel class object reference
			MainModel m2= new MainModel();
			MainUtility mu=null;
			try {
				//Creating MainModel class object reference
				MainModel ab=new MainModel();
				mu=new MainUtility();
				//Invoking actual database connection
				Connection con=mu.getConnection();
				//Database operation to get details of vehicle
				PreparedStatement psmt=null;
	            ResultSet rs=null;
				psmt=con.prepareStatement("Select * from  FQ_Vehicle_Details where VEHICLE_ID=?");
                psmt.setInt(1, a1.getVehicleId());
				rs=psmt.executeQuery();
				//If query execution is successful then set below details
				while(rs.next())
					{
						m2.setVehicleId(rs.getInt(1));
						m2.setVehicleNumber(rs.getString(2));
					    m2.setMaker(rs.getString(3));
						m2.setModel(rs.getString(4));
						m2.setManufacturingYear(rs.getInt(5));
						m2.setType(rs.getString(6));
						m2.setPrimaryUseOfVehicle(rs.getString(9));
						m2.setRoadPrice(rs.getDouble(7));
					}
			
				}
			//else catch Exception
			catch(Exception se)
			{
				se.printStackTrace();
			}
		    finally
		    {
		    	mu.closeConnection();
		    }
		
			return m2;
		}
		//end of getDetailsVehicle method
		
		/**
		 * Construct  :: getDetailsVehicle  : Object ------> Object
		 * purpose    :: This method will updates vehicle details
		 * @param a1  :: MainModel class object references
		 * @return b  :: boolean value reference
		 */
		
	  public boolean getUpdateVehicle(MainModel a1)
	   {
        boolean b=false;
		int temp=0; 
		Connection con=null;
		MainUtility mu=null;
		try 
		{
			mu=new MainUtility();
			//Invoking actual database connection
			con=mu.getConnection();
			//Database operation to update details of vehicle
			PreparedStatement psmt=null;
			
			psmt=con.prepareStatement("update  FQ_Vehicle_Details set  MAKER=?,MODEL=?, MANUFACTURING_YEAR=?,TYPE=?, ON_ROAD_PRICE=?, PRIMARY_USE=?,VEHICLE_NUMBER=? where VEHICLE_ID=?");

		    psmt.setString(1,a1.getMaker());
			psmt.setString(2,a1.getModel());
			psmt.setInt(3,a1.getManufacturingYear());
			psmt.setString(4,a1.getType());
			psmt.setDouble(5,a1.getRoadPrice());
			psmt.setString(6,a1.getPrimaryUseOfVehicle());
			psmt.setString(7,a1.getVehicleNumber());
			psmt.setInt(8, a1.getVehicleId());
			
			temp=psmt.executeUpdate();
			//If query execution is successful then set temp 
			
			if(temp>0)
			{
				//If temp>0 set b to true
				b=true;
			}
			
		}
		//If temp<0 catch Exception
		catch(Exception se)
		{
			
		}
		finally
		{
			mu.closeConnection();
		}

	return b;
	}
	//end of getUpdateVehicle method
	  
	  /**
		 * Construct  :: getDeleteVehicle : Object ------>boolean
		 * purpose    :: This method will  deletes vehicle details
		 * @param a1  :: MainModel class object references
		 * @return b  :: boolean value reference
		 */
	public boolean getDeleteVehicle(MainModel a1)
	{
		boolean b=false;
		int temp=0;
		Connection con=null;
		MainUtility mu=null;
		try 
		{
				mu=new MainUtility();
				//Invoking actual database connection
				con=mu.getConnection();
				//Database operation to update details of vehicle
				PreparedStatement psmt=null;
				psmt=con.prepareStatement("delete from  FQ_Vehicle_Details where VEHICLE_ID=? and user_id=?");
				psmt.setLong(1,a1.getVehicleId());
				psmt.setString(2, a1.getUserId());
				temp=psmt.executeUpdate();
				//If query execution is successful then set temp 
				if(temp>0)
					//If temp>0 set b to true
				{
					b=true;
				}
		}
		//If temp<0 catch Exception
		catch(Exception se)
		{
			se.printStackTrace();
		}
		finally
		{
			mu.closeConnection();
		}
		return b;
	}
	//end of getDeleteVehicle method
	
	 /**
	 * Construct  :: getVehicleId : Object ------>ArrayList
	 * purpose    :: This method will  fetch vehicle id 
	 * @param a1  :: MainModel class object references
	 * @return ar ::  ArrayList reference
	 */
	

	 public ArrayList<Integer> getVehicleId(MainModel a1)
	   {
        ArrayList<Integer> ar=new ArrayList<Integer>();
        ResultSet rs=null;
		int temp=0; 
		Connection con=null;
		MainUtility mu=null;
		
		try 
		{
			mu=new MainUtility();
			//Invoking actual database connection
			con=mu.getConnection();
			//Database operation to get vehicle id
			PreparedStatement psmt=null;
			psmt=con.prepareStatement("select VEHICLE_ID from FQ_Vehicle_Details where USER_ID=?");
			psmt.setString(1,a1.getUserId());
			rs=psmt.executeQuery();
			//If query execution is successful then get below value
			while(rs.next())
			{
				int mn;
				mn=rs.getInt(1);
				
				ar.add(mn);
				// ar adds to arraylist
			}
		}
		//else catch Exception
		catch(Exception se)
		{
			se.printStackTrace();
		}
		finally
		{
			mu.closeConnection();
		}
		return ar;
	}
	 
	//end of getVehicleId method
	 
	 
	 /**
		 * Construct  :: isInsuredVehicleDAO : Object ------>boolean
		 * purpose    :: This method will  check insured vehicle
		 * @param mm  :: MainModel class object references
		 * @return temp  :: boolean value reference
		 */
	 public boolean isInsuredVehicleDAO(MainModel mm)
	 {
		 Connection  dbConn = null;
		//Creating MainUtility class object reference
		 MainUtility utility = new MainUtility();
		 ResultSet rs=null;
		 boolean temp=false;
		 try
		 {
			 dbConn = utility.getConnection();
			 dbConn = utility.getConnection();
			//Database operation to check insured vehicle
			 String sql = "select INSURANCE_ID from FQ_Buy_Insurance where USER_ID=? and "+
			 "TYPE_OF_INSURANCE not in 'Body Injury' and VEHICLE_ID=? and STATUS_OF_INSURANCE=?";
			 PreparedStatement psmt = dbConn.prepareStatement(sql);
			 psmt.setString(1,mm.getUserId());
			 psmt.setInt(2,mm.getVehicleId());
			 psmt.setString(3,"active");
			 rs = psmt.executeQuery();
			//If query execution is successful then set temp to true
			 if(rs.next())
			 {
				 temp = true;
			 }
		 }
		 //else catch exception
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 finally
		 {
			 utility.closeConnection();
		 }
		 return temp;
	 }
	//end of isInsuredVehicleDAO method

	/**********************************************************************************************************************/
	/*****************************submodule three**************************************************************************
/**---------------------------------------Buy Insurance--------------------------------------------------------*/
	               /**
	                 * Buy Insurance 
	                 * @author 594486
	                 * @author 592399
	                 * @author 
	                 */	
	/**--------------------------------------------------View Customer Details--------------------------------------------------------*/

   /** contract::getCustomerDetails : String----->MainModel Object
	 * purpose : this method will get all details of a particular customer
	 * @param customerId     customerId system generated unique id of customer
	 * @return mm    
	 */


	public MainModel getCustomerDetails(String customerId){

		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		//creating references of ResultSet and initialize by null
		ResultSet rs=null;
		MainModel mm = new MainModel();
		try{
			//Creating database connection
			connection=mainUtility.getConnection();
			//query to fetch all details of a particular customer
			String sql_getAllDetails="select * from FQ_CustomerDetails";   //not correct sql query.................
			preparedStatement=connection.prepareStatement(sql_getAllDetails);
			//storing values into ResultSet
			rs=preparedStatement.executeQuery();
			while(rs.next()){
				mm.setUserName(rs.getString(2));
				mm.setDateOfBirth(rs.getString(4));
				mm.setAddress(rs.getString(5));
				mm.setLicenseStatus(rs.getString(7));
				mm.setOccupation(rs.getString(9));
				double annualIncome = Double.parseDouble(rs.getString(10));
				mm.setAnnualIncome(annualIncome);
				mm.setHigherEducation(rs.getString(11));
				mm.setPhoneNumber(rs.getString(12));
				mm.setEmailId(rs.getString(13));
				mm.setCustomerImage(rs.getString(14));
			}
			//Closing PreparedStatement
			preparedStatement.close();
			//Commiting Connecction
			connection.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			mainUtility.closeConnection();
		}
		return mm;
	}//end of getCustomerDetails method

   /**
	 * construct   ::     getRoadPriceOfVehicle  : integer-------->double  
	 * purpose     ::     this class will find on road price of  a vehicle
	 * @param vehicleId   system generated vehicle id          
	 * @return roadPrice           
	 */

    public double getRoadPriceOfVehicle(int vehicleId)                           //594486
	  {
		double roadPrice=0.0;
		//creating connection class object and initialize by null
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		//creating references of ResultSet and initialize by null
		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_serPriceVehicle="select ON_ROAD_PRICE from FQ_Vehicle_Details where VEHICLE_ID=?";

			preparedStatement=connection.prepareStatement(sql_serPriceVehicle);
			preparedStatement.setInt(1,vehicleId);
			//storing values into ResultSet interface reference
			rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				roadPrice=rs.getDouble(1);
			}
			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}


		return roadPrice;
	}//end of getRoadPriceOfVehicle
    
    
    /**
     * Construct  :: searchBodyInjuryInsurance : Object --->boolean
     * purpose    :: This method will search wheather insurance already registered or not
     * @param mm     mm is MainModel class object
     * @return       isExists
     */
	
	public boolean searchBodyInjuryInsurance(MainModel mm)
	{
		boolean isExists=false;
		Connection connection=null;
        PreparedStatement preparedStatement=null;
		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching INSURANCE_ID,TYPE_OF_INSURANCE of vehicle
			String searchBodyInjury="select INSURANCE_ID,TYPE_OF_INSURANCE from FQ_Buy_Insurance where USER_ID=? ";
				
            preparedStatement=connection.prepareStatement(searchBodyInjury);
			preparedStatement.setString(1,mm.getUserId());
			//preparedStatement.setString(2,mm.getInsuranceType());
			
			//SETTING UP VALUES
			rs=preparedStatement.executeQuery();
			if(rs.next())
			{
				while(rs.next())
				{
					//check wheather insurance type is Body Injury 
					if(rs.getString(2).equalsIgnoreCase("Body Injury"))
					{
						isExists=true;;
					}
					
				}
			}
			
			//closing PreparedStatement
			preparedStatement.close();
			
			//commiting transction
			
			connection.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}
	   
	   
		return isExists;
	}//end of searchBodyInjuryInsurance
	
	
	/**
	 * Construct    :: insuranceList   Object---->ArrayList
	 * Purpose      :: This method will list out all insurance registered  
	 * @param mm       MainModel class object
	 * @return al
	 */
	
	public ArrayList<MainModel> insuranceList(MainModel mm)
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		
		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching INSURANCE_ID,TYPE_OF_INSURANCE of vehicle
			String sql_listVehicle="select INSURANCE_ID,TYPE_OF_INSURANCE from FQ_Buy_Insurance "+
				"where USER_ID=?";
		    preparedStatement=connection.prepareStatement(sql_listVehicle);
			preparedStatement.setString(1,mm.getUserId());
			
			//SETTING UP VALUES
			
			
			rs=preparedStatement.executeQuery();
			//if(rs.next())
			//{
				while(rs.next())
				{
					MainModel model=new MainModel();
					model.setInsuranceId(rs.getString(1));
					model.setInsuranceType(rs.getString(2));
					al.add(model);
				}
		//	}
			//else
			//{
			//	al=null;
		//	}
			//closing PreparedStatement
			preparedStatement.close();
			
			//commiting transction
			
			connection.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}
	   
	   return al;
	}//end of insuranceList method
	
 /**
  * Construct   :: 	listRegirteredVehicle   Object--->ArrayList
  * Purpose     ::  Thsi method list registereg vehicle against a particular customer
  * @param mm       MainModel class object
  * @return
  */
	public ArrayList<MainModel> listRegirteredVehicle(MainModel mm)
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
	    Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		
		ResultSet rs;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching VEHICLE_ID of vehicle
			String sql_listVehicle="(select VEHICLE_ID from FQ_Vehicle_Details where USER_ID=? )minus "+ 
              "(select VEHICLE_ID from FQ_Buy_Insurance where TYPE_OF_INSURANCE=? and USER_ID=?)";
			
			preparedStatement=connection.prepareStatement(sql_listVehicle);
			preparedStatement.setString(1,mm.getUserId());
			preparedStatement.setString(2,mm.getInsuranceType());
			preparedStatement.setString(3,mm.getUserId());
			//SETTING UP VALUES
			rs=preparedStatement.executeQuery();
			while(rs.next())
				{
					MainModel model=new MainModel();
					int a=rs.getInt(1);
					model.setVehicleId(rs.getInt(1));
					
					al.add(model);
				}
		
			//closing PreparedStatement
			preparedStatement.close();
			
			//commiting transction
			
			connection.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}
	   
	   
		return al;
	}//end of listRegirteredVehicle method 
	
	
	
	/************************--------Buy Insurances-------****************************/
	

	
	
	/*Buy Vehicle Insurance
	 * 
	 * 
	 * */
	
	/**construct::buyVehicleInsurance : Object -----> boolean
	 * purpose : this method is used to buy a vehicle insurance
	 */
	
	public boolean buyVehicleInsurance(MainModel mModel)
	{
		boolean is_success=false;
		//creating connection class object and initialize by null
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;

		int noOfRows=0;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_insVehicle="insert into FQ_Buy_Insurance values(?,?,?,?,?,?,?,?,?)";

			preparedStatement=connection.prepareStatement(sql_insVehicle);

			//SETTING UP VALUES
			preparedStatement.setString(1,mModel.getInsuranceId());
			preparedStatement.setDouble(2,mModel.getCoverageAmount());
			preparedStatement.setInt(3,mModel.getDuration());
			preparedStatement.setString(4,mModel.getStartDate());
			preparedStatement.setDouble(5,mModel.getMonthlyPremium());
			preparedStatement.setString(6,mModel.getInsuranceType());
			preparedStatement.setString(7, mModel.getStatusOfInsurance());
			preparedStatement.setString(8,mModel.getUserId());
			preparedStatement.setInt(9,mModel.getVehicleId());//mModel.getVehicleId()

			//no of rows updated
			noOfRows=preparedStatement.executeUpdate();
			if(noOfRows>0)
			{
				is_success=true;
			}
			else
			{
				is_success=false;
			}

			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}

		return is_success;
	}
  //End of buyVehicleInsurance method
	
	/*Buy Insurance for Liability
	 * 
	 * 
	 * */
	
	/**Contract::buyInsuranceForLiability : Object -----> boolean
	 * purpose : this method is used to buy a insurance for liability
	 * 
	 */
	
	
	public boolean buyInsuranceForLiability(MainModel mModel)
	{
		boolean is_success=false;
		//creating connection class object and initialize by null
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;

		int noOfRows=0;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_insVehicle="insert into FQ_Buy_Insurance values(?,?,?,?,?,?,?,?,?)";

			preparedStatement=connection.prepareStatement(sql_insVehicle);

			//SETTING UP VALUES
			preparedStatement.setString(1,mModel.getInsuranceId());
			preparedStatement.setDouble(2,mModel.getCoverageAmount());
			preparedStatement.setInt(3,mModel.getDuration());
			preparedStatement.setString(4,mModel.getStartDate());
			preparedStatement.setDouble(5,mModel.getMonthlyPremium());
			preparedStatement.setString(6,mModel.getInsuranceType());
			preparedStatement.setString(7, mModel.getStatusOfInsurance());
			preparedStatement.setString(8,mModel.getUserId());
			preparedStatement.setInt(9,mModel.getVehicleId());//mModel.getVehicleId()

			//no of rows updated
			noOfRows=preparedStatement.executeUpdate();

			if(noOfRows>01)
			{
				is_success=true;
			}
			else
			{
				is_success=false;
			}

			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}

		return is_success;
	}
//End of buyInsuranceForLiability method
	
	/*Buy Insurance of Body Injury
	 * 
	 * 
	 * */
	
	/**Contract::buyInsuranceOfBodyInjury : Object -----> boolean
	 * purpose : this method is used to buy a insurance of body injury
	 * 
	 */
	
	
	
	public boolean buyInsuranceOfBodyInjury(MainModel mModel)
	{
		boolean is_success=false;
		//creating connection class object and initialize by null
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;

		int noOfRows=0;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_insVehicle="insert into FQ_Buy_Insurance values(?,?,?,?,?,?,?,?,?)";

			preparedStatement=connection.prepareStatement(sql_insVehicle);
			
			//SETTING UP VALUES
		
			preparedStatement.setString(1,mModel.getInsuranceId());
			preparedStatement.setDouble(2,mModel.getCoverageAmount());
			preparedStatement.setInt(3,mModel.getDuration());
			preparedStatement.setString(4,mModel.getStartDate());
			preparedStatement.setDouble(5,mModel.getMonthlyPremium());
			preparedStatement.setString(6,mModel.getInsuranceType());
			preparedStatement.setString(7, mModel.getStatusOfInsurance());
			preparedStatement.setString(8,mModel.getUserId());
			preparedStatement.setInt(9,mModel.getVehicleId());//mModel.getVehicleId()

			//no of rows updated
			noOfRows=preparedStatement.executeUpdate();

			if(noOfRows>0)
			{
				is_success=true;
			}
			else
			{
				is_success=false;
			}

			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}

		return is_success;
	}
	//End of buyInsuranceOfBodyInjury method
	
	
/****************************View Particular insurance**********************************/
	/*
	 * By Kuntal
	 * Emp Id - 594621
	 * 
	 * */
	/**Contract   ::  getVehicleInsurance : String -----> ArrayList<Object>
	 * purpose     :: this method will fetch data and display for a particular insurance
	 * @ param  uid  uid for userId
	 * @ return al   al is List of insurance 
	 */
	
	public ArrayList<MainModel> getVehicleInsurance(String uid)
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;

		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_insVehicle="select INSURANCE_ID,COVERAGE_AMOUNT,TYPE_OF_INSURANCE, " +
			"VEHICLE_ID from FQ_Buy_Insurance where USER_ID=? and TYPE_OF_INSURANCE=?";

			preparedStatement=connection.prepareStatement(sql_insVehicle);

			//SETTING UP VALUES
			preparedStatement.setString(1, uid);
			preparedStatement.setString(2, "Vehicle Damage");
			rs=preparedStatement.executeQuery();
				while(rs.next())
				{
					MainModel model=new MainModel();
					model.setInsuranceId(rs.getString(1));
					model.setCoverageAmount(rs.getDouble(2));
					model.setInsuranceType(rs.getString(3));
					model.setVehicleId(rs.getInt(4));

					al.add(model);
				}
			
			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}

		return al;
	}
	//End of getVehicleInsurance method
	
	/*
	 * By Kuntal
	 * Emp Id - 594621
	 * 
	 * */
	/**Contract::getBodyInjuryInsurance : String -----> ArrayList<Object>
	 * purpose : this method will fetch data and display for a particular insurance
	 */
	
	public ArrayList<MainModel> getBodyInjuryInsurance(String uid)
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;

		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_insVehicle="select INSURANCE_ID,COVERAGE_AMOUNT,TYPE_OF_INSURANCE, " +
			"VEHICLE_ID from FQ_Buy_Insurance where USER_ID=?  and TYPE_OF_INSURANCE=?";

			preparedStatement=connection.prepareStatement(sql_insVehicle);
			

			//SETTING UP VALUES
			preparedStatement.setString(1, uid);
			preparedStatement.setString(2, "Body Injury");
			rs=preparedStatement.executeQuery();
			
			
				
				while(rs.next())
				{
					
					MainModel model=new MainModel();
					model.setInsuranceId(rs.getString(1));
					model.setCoverageAmount(rs.getDouble(2));
					model.setInsuranceType(rs.getString(3));
					model.setVehicleId(rs.getInt(4));
					
					
					al.add(model);
					
				}
			
			
			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}

		return al;
	}
//End of getBodyInjuryInsurance method
	
	/*
	 * By Kuntal
	 * Emp Id - 594621
	 * 
	 * */
	/**Contract::getBodyInjuryInsurance : String -----> ArrayList<Object>
	 * purpose : this method will fetch data and display for a particular insurance
	 */

	public ArrayList<MainModel> getLiabilityInsurance(String uid)
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;

		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			//query for fetching ON_ROAD_PRICE of vehicle
			String sql_insVehicle="select INSURANCE_ID,COVERAGE_AMOUNT,TYPE_OF_INSURANCE, " +
			"VEHICLE_ID from FQ_Buy_Insurance where USER_ID=?  and TYPE_OF_INSURANCE=?";

			preparedStatement=connection.prepareStatement(sql_insVehicle);
			

			//SETTING UP VALUES
			preparedStatement.setString(1, uid);
			preparedStatement.setString(2, "Liability");
			rs=preparedStatement.executeQuery();
			
				while(rs.next())
				{
					MainModel model=new MainModel();
					model.setInsuranceId(rs.getString(1));
					model.setCoverageAmount(rs.getDouble(2));
					model.setInsuranceType(rs.getString(3));
					model.setVehicleId(rs.getInt(4));

					al.add(model);
				}
			
			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}

		return al;
	}
	// End of getLiabilityInsurance method
	/*
	 * By Kuntal
	 * Emp Id - 594621
	 * 
	 * */
	/**contract::viewIns : String -----> Object
	 * purpose : this method display information for a particular insurance
	 */
	
	public MainModel viewIns(String insId)
	{
		
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		MainModel mo=new MainModel();
		ResultSet rs=null;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			
			String sql="select * from FQ_Buy_Insurance where INSURANCE_ID=?";

			preparedStatement=connection.prepareStatement(sql);
			

			//SETTING UP VALUES
			preparedStatement.setString(1,insId);
			
			rs=preparedStatement.executeQuery();
			
			
				while(rs.next())
				{
					
					mo.setInsuranceId(rs.getString(1));
					mo.setCoverageAmount(rs.getDouble(2));
					mo.setDuration(rs.getInt(3));
					mo.setStartDate(rs.getString(4));
					mo.setMonthlyPremium(rs.getDouble(5));
					mo.setInsuranceType(rs.getString(6));
					mo.setVehicleId(rs.getInt(9));

					
				}
			
			
			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}
		return mo;
		
		
	}
	//End of viewIns method
	/**
	 * End of Kuntal
	 * Emp Id: 594621
	 * */

	/*************************End of view Insurance********************************/

	
	
	/***************** Delete Insurances ***************************************/
	
	/**contract::delIns : String -----> boolean
	 * purpose : this method deleted all the information for a particular insurance
	 */
	public boolean delIns(String insId)
	{
		
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		
		boolean ff = false;
		try
		{
			//creating database connection
			connection=mainUtility.getConnection();
			
			String sql="delete from FQ_Buy_Insurance where INSURANCE_ID=?";

			preparedStatement=connection.prepareStatement(sql);
			
			preparedStatement.setString(1, insId);
			int a=0;
			
			a=preparedStatement.executeUpdate();
						
			if(a>0)
				ff = true;
			else
				ff=false;
			
			//closing PreparedStatement
			preparedStatement.close();

			//commiting transction

			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}
		return ff;
		
		
	}
	/*
	 *End of Delete Function 
	 * */
	/*****************************End of delete Insurances****************************/
	
	
	


	/**--------------------------------------------------------Add Bank Details--------------------------------------------------------*/	


	/**contract::insertBankDetails : MainModel Object----->boolean
	 * purpose : This method enters bank details of a particular customer
	 * @author 592399
	 */
	public String insertBankDetails(MainModel mmInsertBankDetails){
		String flag="";
		int value;
		String accountNumber = mmInsertBankDetails.getAccountNumber();
//		String accountNumber = Integer.toString(accountNumber1);
		ResultSet rs= null;
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
	
		try{	//Checking presence of bank details against a particular userId
				String sql_CheckPresence = "select * from FQ_Bank_Details where user_id=?";
				psmt = con.prepareStatement(sql_CheckPresence);
				psmt.setString(1, mmInsertBankDetails.getUserId());
				rs=psmt.executeQuery();
				if(rs.next()){
					flag = "BankDetailsPresent";
				}
				//if details are not present then only data is inserted
				else{
					try{
						String sql_InsertBankDetails = "insert into FQ_Bank_Details values(?,?,?,?)";
						psmt = con.prepareStatement(sql_InsertBankDetails);
						psmt.setString(1, mmInsertBankDetails.getBankName());
						psmt.setString(2, mmInsertBankDetails.getBranchCode());
						psmt.setString(3, accountNumber);
						psmt.setString(4,mmInsertBankDetails.getUserId());
						value = psmt.executeUpdate();
						if(value>0)
							flag = "Inserted";
						psmt.close();
					}
					catch(SQLException e){
						//if account number is already present in the database
						flag = "AccountNumberExists";
					}
				}
			}
			catch(Exception e){			e.printStackTrace();		}
			finally
			{
				mainUtility.closeConnection();
			}
		return flag;
	}
	//End of insertBankDetails method
	/**--------------------------------------------------------Update Bank Details--------------------------------------------------------*/	
	/**contract::updateBankDetails : MainModel Object----->boolean
	 * purpose : This method updates bank details of a particular customer
	 * @author 592399
	 */
	public boolean updateBankDetails(MainModel mm_UpdateBankDetails){
		Connection connection=null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		int value= 0;
		String accountNumber = mm_UpdateBankDetails.getAccountNumber();
		
		boolean flag = false;
		try{
			//Creating database connection
			connection=mainUtility.getConnection();
			//Updating details of a particular customer's bank
			String sql_updateBankDetails="update FQ_Bank_Details set BANK_NAME=?, BRANCH_CODE=?, ACCOUNT_NUMBER=? where USER_ID=?";
			preparedStatement=connection.prepareStatement(sql_updateBankDetails);
			preparedStatement.setString(1, mm_UpdateBankDetails.getBankName());
			preparedStatement.setString(2, mm_UpdateBankDetails.getBranchCode());
			preparedStatement.setString(3, accountNumber);
			preparedStatement.setString(4, mm_UpdateBankDetails.getUserId());
			value = preparedStatement.executeUpdate();
			if(value>0)
				flag = true;
		}
		catch(Exception e){
			
			e.printStackTrace();
			//if account number is already present in the database
			flag = false;
		}
		finally
		{
			mainUtility.closeConnection();
		}
		return flag;
	}

	//End of updateBankDetails method 
	/**--------------------------------------------------------View Bank Details--------------------------------------------------------*/
	/**contract::getBankDetails : String, String----->MainModel Object
	 * purpose : this method will get all details of a particular customer's bank
	 * @author 592399
	 */
	public MainModel getBankDetails(MainModel mm_ViewBankDetails){

		Connection connection = null;
		//creating references of PreparedStatement and initialize by null
		PreparedStatement preparedStatement=null;
		//creating references of ResultSet and initialize by null
		ResultSet rs=null;
		MainModel mmGetBankDetails = new MainModel();
		try{
			//Creating database connection
			connection=mainUtility.getConnection();
			//Query to fetch bank details of a particular customer
			String sql_getBankDetails="select * from FQ_Bank_Details where user_id=?";
			preparedStatement=connection.prepareStatement(sql_getBankDetails);
			preparedStatement.setString(1, mm_ViewBankDetails.getUserId());
			//storing values into ResultSet
			rs=preparedStatement.executeQuery();
			if(rs.next()){
				mmGetBankDetails.setBankName(rs.getString(1));
				mmGetBankDetails.setBranchCode(rs.getString(2));
				mmGetBankDetails.setAccountNumber(rs.getString(3));
			}

			//Closing PreparedStatement
			preparedStatement.close();
			//Commiting Connecction
			connection.commit();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			mainUtility.closeConnection();
		}
		return mmGetBankDetails;
	}

  // End of getBankDetails method


	//Add nominee to a particular insurance	

	/**contract::addNomine : Object----->int
	 * purpose : this method will add nominee to a particular insurance
	 */
	public boolean addNominee(MainModel mainModel)//594486
	{

		Connection connection=null;
		//PreparedStatement preparedStatement=null;
		int noOfRows=0;
		boolean success=false;
		try
		{
			connection=mainUtility.getConnection();
			String sql_addNominee="insert into FQ_Nominee_Details values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql_addNominee);
			preparedStatement.setString(1,mainModel.getNomineeName());
			preparedStatement.setString(2,mainModel.getNomineeDOB());
			preparedStatement.setString(3,mainModel.getRelWithNominee());
			preparedStatement.setString(4,mainModel.getUserId());
			
			preparedStatement.setString(5,mainModel.getInsuranceNumber());
			
			noOfRows=preparedStatement.executeUpdate();
			if(noOfRows>0)
			{
				success=true;
			}
			preparedStatement.close();
			connection.commit();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			mainUtility.closeConnection();
		}
		return success;
	}
	//End of addNominee method
	
	/**
	 * contract   ::viewNominee Object --->Object
	 * Purpose     ::This method will display nominee details
	 * @param mm     MainModel class object
	 * @return
	 */
	public MainModel viewNominee(MainModel mm)
	{
		MainModel model=new MainModel();
		Connection connection=null;
		ResultSet rs=null;
		
		try
		{
			//Invoking database connection
			connection = mainUtility.getConnection();
			//Query for selecting details of nominee
			String sql_addNominee="select * from FQ_Nominee_Details where USER_ID=? and " +
					"INSURANCE_NO=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql_addNominee);
			preparedStatement.setString(1,mm.getUserId());
			preparedStatement.setString(2,mm.getInsuranceId());
			rs = preparedStatement.executeQuery();
			//if details present it will set values to MainModel class object
			if(rs.next())
			{
				model.setNomineeName(rs.getString(1));
				model.setNomineeDOB(rs.getString(2));
				model.setRelWithNominee(rs.getString(3));
				model.setUserId(rs.getString(4));
				model.setInsuranceId(rs.getString(5));
			}
			else
			{
				model=null;
			}
			preparedStatement.close();
			connection.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			mainUtility.closeConnection();
		}
		return model;
	}
	//End of viewNominee method
	
	/**
	 * contract     :: updateNomineeDetails   Object--->boolean
	 * Purpose       :: This method will update nominee details
	 * @param mainModel
	 * @return
	 */
	public boolean updateNomineeDetails(MainModel mainModel)//594486
	{	
		
        Connection connection=null;
		int updatedRows = 0;
		boolean success=false;
		try
		{	
			connection=mainUtility.getConnection();
			String sql="update FQ_Nominee_Details set NAME=?, DOB=?, RELATIONSHIP=? where USER_ID=? and insurance_no=?";
			
			PreparedStatement  psmt=connection.prepareStatement(sql);
			
			psmt.setString(1,mainModel.getNomineeName());
			psmt.setString(2,mainModel.getNomineeDOB());
			psmt.setString(3,mainModel.getRelWithNominee());
			psmt.setString(4,mainModel.getUserId());
			psmt.setString(5,mainModel.getInsuranceId());
		    updatedRows = psmt.executeUpdate();
			if(updatedRows>0)
			{
				
				
				success=true;
			}
			psmt.close();
			connection.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			mainUtility.closeConnection();
		}
		return success;
	}//end of updateNomineeDetails method
	
	
	public ArrayList<MainModel> insuranceListForNomination(MainModel mm)
	{
		ArrayList<MainModel> al=new ArrayList<MainModel>();
		Connection con=mainUtility.getConnection();;
		//creating references of PreparedStatement and initialize by null
		//PreparedStatement pst=null;
		
		ResultSet rs=null;
		try
		{
			//creating database connection
			
			//query for fetching INSURANCE_ID,TYPE_OF_INSURANCE of vehicle
			String sql_insuranceList="((select INSURANCE_ID from FQ_Buy_Insurance where USER_ID=?)minus(select INSURANCE_NO from FQ_Nominee_Details where USER_ID=?))";
			PreparedStatement pt = con.prepareStatement(sql_insuranceList);
			pt.setString(1,mm.getUserId());
			pt.setString(2,mm.getUserId());
			
			//SETTING UP VALUES
			rs=pt.executeQuery();
			//if(rs.next())
			//{
				while(rs.next())
				{
					MainModel model=new MainModel();
					model.setInsuranceId(rs.getString(1));
					//model.setInsuranceType(rs.getString(2));
					al.add(model);
				}
		//	}
			//else
			//{
			//	al=null;
		//	}
			//closing PreparedStatement
			pt.close();
			
			//commiting transction
			
			con.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			mainUtility.closeConnection();
		}
	   
	   return al;
	}//end of insuranceList method
	
}//end of MainDao class
      
