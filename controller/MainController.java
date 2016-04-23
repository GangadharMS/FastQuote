package com.ilp.proj1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ilp.proj1.exception.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.proj1.model.MainModel;
import com.ilp.proj1.service.MainService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public MainController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub

		doProcess(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doProcess(HttpServletRequest request, HttpServletResponse response)
	 */
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub

		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		/*
		String userId=(String)session.getAttribute("userID");//by debabrata
		 */

		//session.setAttribute("uid", "111"); /*******By Kuntal********/



		/**********************************************************************************************************************/
		/*--------------------------------------------------------------------------------------------------------------------*/
		/*****************************submodule one****************************************************************************/
		/**
		 * Customer Registration and Log in
		 * @author 592274
		 * @author 678232
		 * 
		 */




		/**********************************************************************************************************************/
		/*--------------------------------------------------------------------------------------------------------------------*/
		/*****************************submodule one****************************************************************************/

		/**
		 * Customer Registration and Log in
		 * @author 592274
		 * @author 678232
		 * 
		 */


		/**
		 * @author Debashis-592274
		 * START CODE
		 * SUBMODULE 1.1
		 */
		/*
		 * 
		 * 
		 */

		

		/*
		 * LOG IN CONTROLLER
		 * 
		 */




		if(action.equalsIgnoreCase("login"))
		{
			/*
			 * 
			 * TAKING INPUT FROM UI
			 */

			String userid=request.getParameter("userid");
			String password=request.getParameter("pwd");
			String message;

			//CREATING OBJECT OF MEDEL CLASS

			MainModel lim=new MainModel(userid, password);

			//SENDING OBJECT TO SERVICE CLASS


			MainService lis=new MainService();
			try{
				message=lis.getLogInDetails(lim);
			}
			catch(Mainexception e)
			{
				message=e.getmessage();
				session.setAttribute("message", message);
				response.sendRedirect("jsp/login.jsp");
			}


			//IF SERVICE RETURNS FIRST MESSAGE CONTROLLER WILL OPEN CHANGE PASSWORD PAGE


			if(message.equals("1"))
			{
				int count=1;
				String n=lim.getUserId();
				
				session.setAttribute("userid",n);
				lis.update(count,n);

				
				response.sendRedirect("jsp/changePwdUI.jsp");
			}





			//IF SERVICE RETURNS NOT FIRST MESSAGE THE CONTROLLER WILL OPEN DASHBORD PAGE


			if(message.equals("2"))
			{
				String n=lim.getUserId();
				
				session.setAttribute("userid",n);/*********************************************session********************************/
				String name=lis.getUser(userid);
				session.setAttribute("username",name);
				

				response.sendRedirect("jsp/home.jsp");
			}

			//IF WRONG USERNAME OF PASSWORD
			if(message.equals("3") || message.equals("4"))
			{

			
				message="Enter correct User Id/Password";
				session.setAttribute("message", message);
				response.sendRedirect("jsp/login.jsp");
			
				
				/*
				request.setAttribute("message",message);
				RequestDispatcher rd = request.getRequestDispatcher("jsp/loginUI.jsp");  
				rd.forward(request, response);
				*/
			}
			
			
			//IF USER INACTIVE
			if(message.equalsIgnoreCase("User inactive"))
			{
				message="Your account is deactivated. Please activate your account.";
				session.setAttribute("message", message);
				response.sendRedirect("jsp/login.jsp");
				
			}
			
			
			//IF USER ATTEMPT MORE THAN 3 TIMES
			/*if(message.equalsIgnoreCase("6"))
			{
				message="MORE THAN 3 TIMES";
				session.setAttribute("message", message);
				response.sendRedirect("jsp/index.jsp");
				
			}*/


		}
		
		
		
		
		
		
		/*
		 * CHANGE PASSWORD CONTROLLER 
		 * 
		 */
		
		
		
		if(action.equalsIgnoreCase("chngPwd"))
		{
			// TAKING VALUES FROM UI


			boolean valid=false;
			String userid=(String) session.getAttribute("userid");
			
			//String userid="123";
			String oldpwd=request.getParameter("oldPassword");
			String newpwd=request.getParameter("newPassword");
			String confirmpwd=request.getParameter("confirmPassword");
			MainService ms=new MainService();

			if(!oldpwd.equals("") && !newpwd.equals("") && !confirmpwd.equals(""))
			{
				
				//DEFINING REGULAR EXPRESSION
				
				String pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16})";
				String text = newpwd ;
				Pattern p = Pattern.compile(pattern);
				Matcher m = p.matcher(text);
				
				//IF REGULAR EXPRESSION MATCHES
				if(m.matches())
				{


					//CREATING OBJECT OF MODEL CLASS FOR OLD PASSWORD


					MainModel op=new MainModel(userid, oldpwd);




					//CREATING OBJECT OF MODEL CLASS FOR NEW PASSWORD


					MainModel np=new MainModel(userid, newpwd);
					MainService gop =new MainService();

					valid=gop.checkOldPassword(op);





					//IF OLD PASSWORD IS CORRECT 

					if(valid)
					{
						if(np.getPassword().equals(op.getPassword()))
						{
							String message="Do not enter previously used password";
							session.setAttribute("message1", message);
							response.sendRedirect("jsp/changePwdUI.jsp");
						}
						if(!np.getPassword().equals(op.getPassword()))
						{
							if(gop.setNewPassword(np))
							{
								//SUCESS MESSAGE

								
								String update="Password updated.";
								session.setAttribute("update", update);
								response.sendRedirect("jsp/changePwdUI.jsp");
							}
							else
							{
								String update="Password not updated. Please try again.";
								session.setAttribute("update", update);
								response.sendRedirect("jsp/changePwdUI.jsp");
							}
						}

					}

					//IF OLD PASSWORD IS WRONG


					else
					{
						String message="Enter correct old password";
						session.setAttribute("message1", message);
						response.sendRedirect("jsp/changePwdUI.jsp");
					}
				}
				
				
				
				//IF REGULAR EXPRESSION DOES NOT MATCH
				else
				{
					String message="Password should follow all requirments";
					session.setAttribute("message1", message);
					response.sendRedirect("jsp/changePwdUI.jsp");
				}


			}
			
			
			
			
			//IF ALL FIELDS ARE EMPTY
			else
			{
				String message="Enter all field";
				session.setAttribute("message1", message);
				response.sendRedirect("jsp/changePwdUI.jsp");
			}

		}
		
		
		
		

		/*
		 * 
		 * FORGOT PASSWORD CONTROLLER
		 * 
		 */
		if (action.equalsIgnoreCase("forgot"))
		{
			ArrayList<MainModel> ar=new ArrayList<MainModel>();
			String question=null;

			
			
			//GETTING THE VALUES FROM UI
			String userid=request.getParameter("username");
			String dob=request.getParameter("date");
			MainService ms=new MainService();
			
			
			try
			{

				//GETTING THE ARRAYLIST FROM SERVICE CLASS
				
				ar=ms.getQuestion(userid, dob);
				session.setAttribute("slist", ar);
				for(MainModel x:ar)
				{
					//GETTING THE SECURITY QUESTION
					
					question=x.getSecurityQuestion();
				}
			

				//STORING IT INTO SESSION VERIABLE
				session.setAttribute("question", question);

				//REDIRECTING TO FORGOT PASSWORD 2 PAGE
				response.sendRedirect("jsp/forgotPasswordUI2.jsp");


			}
			catch(Mainexception e)
			{
				//GETTING THE ERROR MESSAGE
				String message=e.getmessage();

				//STORING IT INTO SESSION VERIABLE
				session.setAttribute("message", message);

				//REDIRECTING TO FORGOT PASSWORD UI
				response.sendRedirect("jsp/forgotPasswordUI.jsp");

			}
		}
		
		
		
		/*
		 * 
		 * CONTROLLER FOR MATCHING ANSWER
		 * 
		 */

		
		
		if(action.equalsIgnoreCase("answer"))
		{

			boolean valid=false;
			String userid=null;
			String password=null;


			//GETTING THE VALUE FROM UI
			String answer=request.getParameter("ans");

			//GETTING THE ARRAYLIST FROM SESSION
			ArrayList<MainModel> ar=(ArrayList<MainModel>) session.getAttribute("slist");
			MainService ms=new MainService();
			try
			{

				// IF ASNWER MATCHES
				valid=ms.matchAnswer(ar,answer);
				if(valid)
				{

					//GETTING THE USERID

					for(MainModel x:ar)
					{
						userid=x.getUserId();
					}


					//GETTING THE PASSWORD
					password=ms.getPassword(userid);

					//DISPLAYING THE PASSWORD
					
					session.setAttribute("password", password);
					response.sendRedirect("jsp/forgotPasswordUI2.jsp");

					//DESTROING THE SESSION VERIABLE


				}
			}
			
			
			catch(Mainexception e)
			{

				//GETTING THE ERROR MESSAGE
				String message=e.getmessage();

				//STORING IT INTO SESSION
				session.setAttribute("message", message);

				//REDIRECTING TO FORGOT PASSWORD PAGE 2
				response.sendRedirect("jsp/forgotPasswordUI2.jsp");
			}
		}

		/**
		 * @author 592274
		 * END CODE
		 * SUBMODULE 1
		 */
		
		
		
		// USER REGISTRATION CONTROLLER
		if(action.equalsIgnoreCase("UserRegistration"))
		{
			String sq,sa;
			String lname;
			String fname=request.getParameter("n1");
			String lname1=request.getParameter("n2");
			if(lname1.equals("Last Name"))
				lname=" ";
			else
				lname=lname1;
			String email=request.getParameter("id");
			String gender=request.getParameter("gender");
			String dob=request.getParameter("dob");
			String add1=request.getParameter("ad1");
			String add2=request.getParameter("ad2");
			String add3=request.getParameter("ad3");
			String zip1=request.getParameter("zip");
			String ag=request.getParameter("af");
			String st=request.getParameter("status");
			String s=request.getParameter("ssn");
			String s1=request.getParameter("ssn1");
			String s2=request.getParameter("ssn2");
			String occu=request.getParameter("oc");
			if(occu.equals("Occupation"))
				occu="Unemployed";
			double ani=Double.parseDouble(request.getParameter("ai"));
			String edu=request.getParameter("education");
			String sq1=request.getParameter("sec");
			if(sq1.equals("Custom question"))
			{
				String sq2=request.getParameter("sec1");
				sa=request.getParameter("ss2");
				sq=sq2;
			}
			else
			{
				sa=request.getParameter("s1");
				sq=sq1;
			}
			String cn=request.getParameter("cn");
			MainService rs=new MainService();
			String key=rs.getKey();
			String pwd=rs.createPwd(fname,lname);
			//String pwd1=rs.encrypt(pwd, key);
			//String pwd2=rs.decrypt(pwd1,key);
			String uid=rs.getUsername(fname,dob,key);
			String addr=add1+";"+add2+";"+add3+";"+zip1;
			String ssn=s+";"+s1+";"+s2;
			String name=fname+";"+lname;
			try{
				MainModel rm=new MainModel(uid,name,email,cn,gender,dob,addr,ag,st,ssn,occu,ani,edu,sq,sa);
				MainModel rm1=new MainModel(uid,pwd,"Active",0,key);
				
				
				rs.insertdata(rm);
				ArrayList<MainModel> a1=rs.UserLogin(rm1);
				for(MainModel am1:a1)
				{
					//System.out.println(am1.getPassword());	
				}
				MainModel sh=new MainModel(uid,pwd);
				session.setAttribute("msgd",sh);
				response.sendRedirect("jsp/regsuccess.jsp");
			}
			catch(Mainexception e)
			{
				String msg1=e.getmessage();
				session.setAttribute("msgd", msg1);
				response.sendRedirect("jsp/regfailure.jsp");
			}
		}
		
		
		//UPDATE CONTROLLER

		if(action.equalsIgnoreCase("update"))
		{
			try
			{
				MainService ob1=new MainService();
				String uid=(String)session.getAttribute("userid");
				//System.out.println(uid);
				ArrayList<MainModel> arr1=ob1.getdata(uid);

				session.setAttribute("res", arr1);
				response.sendRedirect("jsp/User_Registration_Update.jsp");
			}
			catch(Mainexception e)
			{
				String msg1=e.getmessage();
				session.setAttribute("errorMessage", msg1);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		if(action.equalsIgnoreCase("update11"))
		{
			String uid=(String)session.getAttribute("userid");
			response.setContentType("text/html");
			String email=request.getParameter("id");
			String add1=request.getParameter("ad1");
			String add2=request.getParameter("ad2");
			String add3=request.getParameter("ad3");
			String zip1=request.getParameter("zip");
			String st=request.getParameter("status");
			String occu=request.getParameter("oc");
			double ani=Double.parseDouble(request.getParameter("ai"));
			String edu=request.getParameter("education");
			String cn=request.getParameter("cn");
			String addr=add1+";"+add2+";"+add3+";"+zip1;
			try{
				MainModel rm=new MainModel(email,cn,addr,st,occu,ani,edu);
				MainService rs=new MainService();
				boolean a=rs.updatedata(rm,uid);
				if (a==true)
				{
					response.sendRedirect("jsp/updatesuccess.jsp");

				}
			}

			catch(Mainexception e)
			{
				String msg1=e.getmessage();
				session.setAttribute("errorMessage",msg1);
				response.sendRedirect("jsp/errorPage.jsp");

			}
		}



		if(action.equalsIgnoreCase("customerdelete"))
		{
			response.setContentType("text/html");
			String password=request.getParameter("cp");
			MainService rs=new MainService();
			  
			String uid=(String)session.getAttribute("userid");
			try{
				ArrayList<MainModel> ar3=rs.deletepassword(uid,password);			
				//String msg1="Your account is deactivated";
				session.setAttribute("msgd1",ar3);
				response.sendRedirect("jsp/intermidiatedelete.jsp");

			}
			catch(Mainexception e)
			{
				String msg2=e.getmessage();
				session.setAttribute("errorMessage",msg2);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}
  
		
		 if(action.equalsIgnoreCase("finaldelete"))
				 {
			 MainService rs=new MainService();
			 String uid=(String)session.getAttribute("userid");
			 try
			 {
			 rs.finaldelete1(uid);
			String msg1="Your account is deactivated";
			session.setAttribute("msgd1",msg1);
			response.sendRedirect("jsp/successdeactivate.jsp");
			 }
			 catch(Mainexception e)
				{
					String msg2=e.getmessage();
					session.setAttribute("errorMessage",msg2);
					response.sendRedirect("jsp/errorPage.jsp");
				}
				 }



		if(action.equalsIgnoreCase("activateaccount"))
		{
			response.setContentType("text/html");
			String userid=request.getParameter("ui");
			String password=request.getParameter("pa");
			try
			{
				MainModel obj=new MainModel(userid,password);
				MainService rs=new MainService();
				rs.actacc(obj);
				String msg1="Your account is activated";
				session.setAttribute("msgd",msg1);
				response.sendRedirect("jsp/activatesuccess.jsp");
			}
			catch(Mainexception e)
			{
				String msg2=e.getmessage();
				session.setAttribute("errorMessage",msg2);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}




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
		 * @author 678271 @ Amrutha
		 * @author 677398 @ Rakesh
		 * 
		 */	
		/**
		 * Registered New Vehicle          
		 */
		if(action.equalsIgnoreCase("vehicleRegistration"))
		{
			String s1=request.getParameter("p1");
			String s2=request.getParameter("p2");
			String s3=request.getParameter("p3");
			int s4=Integer.parseInt(request.getParameter("p4"));
			String s5=request.getParameter("p5");
			double s6=Double.parseDouble(request.getParameter("p6") );
			String s7=request.getParameter("p7");

			String userId=(String)session.getAttribute("userid");
			String img="img";

			MainModel mm=new MainModel();
			mm.setVehicleNumber(s1);
			mm.setMaker(s2);
			mm.setModel(s3);
			mm.setManufacturingYear(s4);
			mm.setType(s5);
			mm.setRoadPrice(s6);
			mm.setPrimaryUseOfVehicle(s7);
			mm.setImageOfNumberPlate(img);
			mm.setUserId(userId);
			try
			{
				MainModel model=new MainService().insertdataVehicle(mm);
				session.setAttribute("vehicleDetails",model);
				response.sendRedirect("jsp/Vehsuccreg.jsp");
			}
			catch(ServerProblemException spe)
			{
				String errorMsg=spe.errorMessage();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");

			}
			catch(Mainexception exc)
			{
				String errorMsg=exc.vehicleNumberException();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		/**
		 * search vehicle
		 */
		if(action.equalsIgnoreCase("searchVehicle"))
		{
			int vid = Integer.parseInt(request.getParameter("nam"));
			MainModel abc = new MainModel();
			abc.setVehicleId(vid);
			session.setAttribute("vehicleid", vid);
			MainService vs=new MainService();
			MainModel mm = new MainModel();

			try
			{
				mm = vs.detailsVehicle(abc);

				session.setAttribute("vehicleDetails",mm);
				response.sendRedirect("jsp/ViewDetails.jsp");

			}
			catch(ServerProblemException spe)
			{
				String errorMsg=spe.errorMessage();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}//end of if(action.equalsIgnoreCase("searchVehicle"))	

		/**
		 * @see update vehicle
		 */
		if(action.equalsIgnoreCase("updateVehicle"))
		{
			String s1=request.getParameter("p1");
			String s2=request.getParameter("p2");
			String s3=request.getParameter("p3");
			int s4=Integer.parseInt(request.getParameter("p4"));
			String s5=request.getParameter("p5");
			long s6=(long) Double.parseDouble(request.getParameter("p6") );
			String s7=request.getParameter("p7");

			String uid= (String)session.getAttribute("userid");
			String s8=uid;
			MainService es=new MainService();
			String img="img";

			//int l2=Integer.valueOf((String) session.getAttribute("vehicleid"));
			Integer v=(Integer) session.getAttribute("vehicleid");
			int l2=v.intValue();
			//System.out.println(l2);

			MainModel e=new MainModel();
			e.setVehicleId(l2);
			e.setVehicleNumber(s1);
			e.setMaker(s2);
			e.setModel(s3);
			e.setManufacturingYear(s4);
			e.setType(s5);
			e.setRoadPrice(s6);
			e.setPrimaryUseOfVehicle(s7);

			String msg=null;
			boolean b=false;
			MainService ms=new MainService();
			try{
				b=ms.updateVehicle(e);
				if(b)
				{
					msg="Your details are updated successfully.";
					session.setAttribute("msg", msg);
					response.sendRedirect("jsp/VehUpdateSucc.jsp");
				}
			}
			catch(Mainexception d)
			{
				msg=d.getmessage();
				session.setAttribute("errorMessage", msg);
				response.sendRedirect("jsp/errorPage.jsp");

			}




		}

		/**
		 * delete
		 */
		if(action.equalsIgnoreCase("deleteVehicle"))
		{
			int s1=Integer.parseInt(request.getParameter("vid"));
			String userId = (String)session.getAttribute("userid");
			MainModel e=new MainModel();
			e.setVehicleId(s1);
			e.setUserId(userId);
			MainService es=new MainService();
			String msg1=null;
			boolean b=false;
			try
			{
				b=es.getDeleteVehicle(e);
				if(b)
				{
					msg1="Your details are deleted successfully.";
					session.setAttribute("msg1", msg1);
					response.sendRedirect("jsp/VehDelView.jsp");	
				}
			}
			catch(InsuranceException exc)
			{
				String errorMsg = exc.insuranceActive();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
			catch(Mainexception d1)
			{
				msg1=d1.getmessage();
				session.setAttribute("errorMessage", msg1);
				response.sendRedirect("jsp/errorPage.jsp");

			}


		}

		/**
		 * get vehicle Id for a particular user
		 */
		if(action.equalsIgnoreCase("choosevehicleid"))
		{
			MainModel mm=new MainModel();

			String uid = (String)session.getAttribute("userid");
			mm.setUserId(uid);
			String msg3=null;
			MainService es=new MainService();
			try
			{
				ArrayList<Integer> ar=es.gtVehicleId(mm);
				session.setAttribute("vehid",ar);
				//int[] a=(int[])ar.toArray([ar.size()]);
				response.sendRedirect("jsp/VehSearch.jsp");
			}
			catch(Mainexception e)
			{
				msg3=e.getmessage();
				session.setAttribute("errorMessage", msg3);
				response.sendRedirect("jsp/errorPage.jsp");
			}




		}


		/******************************************************************************************************************/
		/*-------------------------------------Buy Insurance-------------------------------------------------------------*/
		/**
		 * Buy Insurance 
		 * @author 594486
		 * @author 592399
		 * @author 594621
		 */



		/**
		 * 	Body Injury Insurances	
		 */



		if(action.equalsIgnoreCase("bodyInjury"))
		{
			MainModel mm=new MainModel();
			String userId = (String)session.getAttribute("userid");
			mm.setUserId(userId);
			mm.setInsuranceType("Body Injury");
			boolean temp;
			try
			{
				temp=new MainService().isBodyInsuranceExists(mm);
				if(!temp)
					response.sendRedirect("jsp/BuyBodyInjuryInsurance.jsp");
			}catch(Mainexception e)
			{
				String errorMsg=e.bodyInsurancefailure();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}

		}

		/**
		 * 	Redirect to Vehicle Damage insurance Page	
		 */



		if(action.equalsIgnoreCase("vehicleDamage"))
		{

			String userId=(String)session.getAttribute("userid");
			//System.out.println(userId +" Hello");
			MainModel mainModel=new MainModel();
			mainModel.setUserId(userId);
			mainModel.setInsuranceType("Vehicle Damage");
			try
			{
				ArrayList<MainModel> al=new ArrayList<MainModel>();
				al=new MainService().registredVehicleList(mainModel);
				session.setAttribute("velicleList",al);
				//System.out.println(al);
				response.sendRedirect("jsp/BuyVehicleDamageInsurance.jsp");
			}catch(Mainexception e)
			{
				String errorMsg=e.vehicleListException();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}
		/**
		 * 	Redirect to liability insurance Page	
		 */
		if(action.equalsIgnoreCase("liabilityInsurance"))
		{

			String userId=(String)session.getAttribute("userid");
			MainModel mainModel=new MainModel();
			mainModel.setUserId(userId);
			mainModel.setInsuranceType("Liability");
			try
			{
				ArrayList<MainModel> al=new ArrayList<MainModel>();
				al=new MainService().registredVehicleList(mainModel);
				session.setAttribute("velicleList",al);
				response.sendRedirect("jsp/BuyLiabilityCoverageInsurance.jsp");
			}catch(Mainexception e)
			{
				String errorMsg=e.vehicleListException();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		/**
		 * Buy Vehicle Insurances       
		 */


		

		/*
		 * For Vehicle Damage
		 * */
		
		if(action.equalsIgnoreCase("buyVehicleDamage"))
		{
			int month = 0;
			//CONVERTING DURATION OF INSURANCE INTO MONTHS
			int year=Integer.parseInt(request.getParameter("year"));
			String mnth = request.getParameter("month");
			if(mnth.equals("0"))				
				month = 0;
			else
				month = Integer.parseInt(mnth);
			int duration=year*12+month;
			String userId=(String)session.getAttribute("userid");
			//CREATING MODEL CLASS OBJECT

			MainModel mainModel=new MainModel();

			//INITIALIZE MODEL CLASS VARIABLE

			mainModel.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
			mainModel.setCoverageAmount(Double.parseDouble(request.getParameter("coverageAmount")));
			mainModel.setDuration(duration);
			mainModel.setUserId(userId);
			//mainModel.setVehicleId(123456);
			mainModel.setInsuranceType("Vehicle Damage");
			try
			{
				MainModel model= new MainModel();
				MainService ms = new MainService();
				model = ms.buyVehicleInsurance(mainModel);
				session.setAttribute("InsurancePurchase",model);
				response.sendRedirect("jsp/afterPurchaseInsurance.jsp");
			}
			catch(InvalidCoverageAmountException e)
			{
				String errorMsg=e.showErrorMessage();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
			catch(ServerProblemException spe)
			{
				String errorMsg=spe.errorMessage();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}//end of if


		/*
		 * For Body Injury
		 * */
		
		if(action.equalsIgnoreCase("buyBodyInjury"))
		{
			int month = 0;
			String mnth = "";
			//CONVERTING DURATION OF INSURANCE INTO MONTHS
			int year=Integer.parseInt(request.getParameter("year"));
			mnth=request.getParameter("month");
			if(mnth.equals("0"))				
				month = 0;
			else
				month = Integer.parseInt(mnth);
			int duration=year*12+month;
			String userId=(String)session.getAttribute("userid");
			//CREATING MODEL CLASS OBJECT
			MainModel mainModel=new MainModel();

			//INITIALIZE MODEL CLASS VARIABLE


			mainModel.setCoverageAmount(Double.parseDouble(request.getParameter("coverageAmount")));
			mainModel.setDuration(duration);
			mainModel.setUserId(userId);
			mainModel.setVehicleId(0);
			mainModel.setInsuranceType("Body Injury");
			try
			{
				MainModel model=new MainService().buyBodyInjuryInsurance(mainModel);
				session.setAttribute("InsurancePurchase",model);
				response.sendRedirect("jsp/afterPurchaseInsurance.jsp");
			}
			catch(InvalidCoverageAmountException e)
			{
				String errorMsg=e.errorMsgOfBodyInsurance();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
			catch(ServerProblemException spe)
			{
				String errorMsg=spe.errorMessage();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}//end of if


		/*
		 * For Liability Coverage
		 * */
		
		if(action.equalsIgnoreCase("buyLiabilityCoverage"))
		{
			int month = 0;
			//CONVERTING DURATION OF INSURANCE INTO MONTHS
			int year=Integer.parseInt(request.getParameter("year"));
			String mnth = request.getParameter("month");
			if(mnth.equals("0"))				
				month = 0;
			else
				month = Integer.parseInt(mnth);
			int duration=year*12+month;
			String userId=(String)session.getAttribute("userid");

			//userId = "Rajendra89@FQC";


			//CREATING MODEL CLASS OBJECT

			MainModel mainModel=new MainModel();

			//INITIALIZE MODEL CLASS VARIABLE

			mainModel.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
			mainModel.setCoverageAmount(Double.parseDouble(request.getParameter("coverageAmount")));
			mainModel.setDuration(duration);
			mainModel.setUserId(userId);
			//mainModel.setVehicleId(12345);/********************************************************/
			mainModel.setInsuranceType("Liability");
			try
			{
				MainModel model=new MainService().buyBodyInjuryInsurance(mainModel);
				session.setAttribute("InsurancePurchase",model);
				response.sendRedirect("jsp/afterPurchaseInsurance.jsp");
			}
			catch(InvalidCoverageAmountException e)
			{
				String errorMsg=e.errorMsgOfLiabilityInsurance();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
			catch(ServerProblemException spe)
			{
				String errorMsg=spe.errorMessage();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}//end of if



		/**********************************View Insurance***********************************/		
		/*
		 * 
		 * 
		 * */

		/*
		 * Code For View Insurance
		 * By 594621
		 * by Kuntal
		 * */
		
		
		/*
		 * For Vehicle Insurances
		 * */
		if(action.equalsIgnoreCase("view_vehicle_insurance"))
		{
			String userId=(String)session.getAttribute("userid");
			//System.out.println(userId);
			ArrayList<MainModel> al = new ArrayList<MainModel>();
			MainService ms = new MainService();
			//String uid= (String)session.getAttribute(userId);
			//System.out.println(userId+" "+uid);
			al = ms.viewVehicleInsurance(userId);
			session.setAttribute("viewvehInsAl", al);
			response.sendRedirect("jsp/viewvehicleinsurance.jsp");

		}

		/*
		 * For Body Injury Insurances
		 * */
		if(action.equalsIgnoreCase("view_body_injury_insurance"))
		{
			String userId=(String)session.getAttribute("userid");
			ArrayList<MainModel> al = new ArrayList<MainModel>();
			MainService ms = new MainService();
			//String uid= (String)session.getAttribute(userId);
			//System.out.println(userId);
			al = ms.viewBodyInjuryInsurance(userId);
			session.setAttribute("viewbodyinjInsAl", al);
			response.sendRedirect("jsp/viewbodyinjuryinsurance.jsp");
		}
		/*
		 * For Liability Insurances
		 * */
		if(action.equalsIgnoreCase("view_liability_insurance"))
		{
			String userId=(String)session.getAttribute("userid");
			ArrayList<MainModel> al = new ArrayList<MainModel>();
			MainService ms = new MainService();
			//String uid= (String)session.getAttribute(userId);


			al = ms.viewLiabilityInsurance(userId);
			session.setAttribute("viewliaInsAl", al);
			response.sendRedirect("jsp/viewliabilityinsurance.jsp");
		}

		/***********************View All insurance data***********************/
		/**
		 * by Kuntal 
		 * Emp ID: 594621
		 * To view Insurance data.
		 */
		
		
		
		
		if(action.equalsIgnoreCase("view"))
		{
			String flag = request.getParameter("flag");
			MainService mss = new MainService();
			MainModel mmo = new MainModel();
			mmo = mss.viewDetailsOfIns(flag);
			session.setAttribute("objj", mmo);
			response.sendRedirect("jsp/viewInsDetails.jsp?action="+flag);



		}
		
		
		
		/****************************Delete Insurance**********************************/
		
		

		/*
		 * Code For Delete Insurance
		 * By 594621
		 * by Kuntal
		 * */
		
		
		/*
		 * For Vehicle Insurances
		 * */
		if(action.equalsIgnoreCase("delete_vehicle_insurance"))
		{
			String userId=(String)session.getAttribute("userid");
			//System.out.println(userId);
			ArrayList<MainModel> al = new ArrayList<MainModel>();
			MainService ms = new MainService();
			//String uid= (String)session.getAttribute(userId);
			//System.out.println(userId+" "+uid);
			al = ms.viewVehicleInsurance(userId);
			session.setAttribute("viewvehInsAl", al);
			response.sendRedirect("jsp/deletevehicleinsurance.jsp");

		}

		/*
		 * For Body Injury Insurances
		 * */
		if(action.equalsIgnoreCase("delete_body_injury_insurance"))
		{
			String userId=(String)session.getAttribute("userid");
			ArrayList<MainModel> al = new ArrayList<MainModel>();
			MainService ms = new MainService();
			//String uid= (String)session.getAttribute(userId);
			System.out.println(userId);
			al = ms.viewBodyInjuryInsurance(userId);
			session.setAttribute("viewbodyinjInsAl", al);
			response.sendRedirect("jsp/deletebodyinjuryinsurance.jsp");
		}
		/*
		 * For Liability Insurances
		 * */
		if(action.equalsIgnoreCase("delete_liability_insurance"))
		{
			String userId=(String)session.getAttribute("userid");
			ArrayList<MainModel> al = new ArrayList<MainModel>();
			MainService ms = new MainService();
			//String uid= (String)session.getAttribute(userId);


			al = ms.viewLiabilityInsurance(userId);
			session.setAttribute("viewliaInsAl", al);
			response.sendRedirect("jsp/deleteliabilityinsurance.jsp");
		}


		if(action.equalsIgnoreCase("delete"))
		{
			String flag = request.getParameter("flag");
			MainService mss = new MainService();
			boolean a = false;
			a = mss.delDetailsOfIns(flag);
			if(a)
			{
				response.sendRedirect("jsp/delInsSuccess.jsp?action="+flag);
			}
			else
			{
				
			}


		}


		/***************************Add Nominee To Insurance ***************************************/		

		//add nominee to a insurance
		if(action.equalsIgnoreCase("referToAddNomineePage"))
		{
			String userId=(String)session.getAttribute("userid");
			MainModel mm=new MainModel();
			mm.setUserId(userId);
			try
			{
				ArrayList<MainModel> al=new MainService().searchInsurancesForNominee(mm);
				session.setAttribute("insuranceList",al);
				response.sendRedirect("jsp/addNominee.jsp");
			}
			catch(Mainexception e)
			{
				String errorMsg=e.insuranceNotRegistered();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}
		//
		if(action.equalsIgnoreCase("addNomineeDetails"))
		{
			//Getting values from user innterface
			String nomineeName=request.getParameter("nomineeName");
			String insuranceId=request.getParameter("insuranceId");
			String dateOfBirth=request.getParameter("dateOfBirth");
			String relationShip=request.getParameter("relationShip");
			String userId=(String)session.getAttribute("userid");
			MainModel mainModel=new MainModel();

			//INITIALIZE MODEL CLASS VARIABLE

			mainModel.setNomineeName(nomineeName);
			mainModel.setInsuranceNumber(insuranceId);
			mainModel.setNomineeDOB(dateOfBirth);
			mainModel.setRelWithNominee(relationShip);
			mainModel.setUserId(userId);
			try
			{
				boolean addNomineeSuccess=new MainService().addNomineeBSL(mainModel);
				if(addNomineeSuccess)
					response.sendRedirect("jsp/addNomineeSuccess.jsp");
			}

			catch(ServerProblemException ser)
			{
				String errMsg=ser.errorMessage();
				session.setAttribute("errorMessage",errMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		if(action.equalsIgnoreCase("initiateToViewNomineeDetails"))
		{
			MainModel mm = new MainModel();
			String userId=(String)session.getAttribute("userid");
			mm.setInsuranceId(request.getParameter("insuranceId"));
			mm.setUserId(userId);
			MainModel model;
			try
			{
				model = new MainService().viewNominee(mm);
				session.setAttribute("nomineeDetails",model);
				response.sendRedirect("jsp/detailsOfNominee.jsp");
			}catch(Mainexception e)
			{
				String errorMsg=e.nomineeNotRegisteredException();
				session.setAttribute("errorMessage",errorMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		if(action.equalsIgnoreCase("editNominee"))
		{

			MainModel mm = new MainModel();
			String nomineeName=request.getParameter("nomineeName");
			String insuranceId=request.getParameter("insuranceId");
			String dateOfBirth=request.getParameter("dateOfBirth");
			String relationShip=request.getParameter("relationShip");
			String userId=request.getParameter("userId");
			mm.setNomineeName(nomineeName);
			mm.setNomineeDOB(dateOfBirth);
			mm.setRelWithNominee(relationShip);
			mm.setUserId(userId);
			mm.setInsuranceId(insuranceId);
			try
			{	
				boolean isUpdate=new MainService().updateNomineeDetailsBSL(mm);
				if(isUpdate)
				{
					response.sendRedirect("jsp/nomineeUpdateSuccess.jsp");
				}
			}
			catch(ServerProblemException spe)
			{
				String errMsg=spe.errorMessage();
				session.setAttribute("errorMessage",errMsg);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}


		/***------------------------all processes of bank details START tag by 592399-------------------------------***/

		/*----------------To Refer to Create Bank Details Page----------------------*/
		if(action.equalsIgnoreCase("addBankDetailsPage")){
			
			//Creating constructor of MainService
			MainService ms_InsertBankDetails = new MainService();
			String check = "";
			String blank = "";
			//Getting value of a particular userid from session
			String userId = (String)session.getAttribute("userid");
			//System.out.println(userId+"contrl");
			//Receiving data from User Interface
			String bankName = request.getParameter("bankName");
			String branchCode = request.getParameter("branchCode");
			String accountNumber = request.getParameter("accountNumber");
			//Creating constructor of MainModel and forwarding received data 
			MainModel mm_InsertBankdetails = new MainModel(userId, bankName, branchCode, accountNumber, blank);
			//Receiving data in String from MainDao through MainService
			check = ms_InsertBankDetails.getInsertBankDetails(mm_InsertBankdetails);
			try{
				//If some value is already present against a particular user throws BankAlreadyRegisteredException
				if(check.equals("BankDetailsPresent")){
					throw new Mainexception();
				}
				else{
					response.sendRedirect("jsp/createBankDetails.jsp");
				}
			}
			//catch BankAlreadyRegisteredException
			catch(Mainexception e){
				String message = e.BankAlreadyRegisteredException();
				//Make the value received into a session attribute
				session.setAttribute("errorMessage", message);
				response.sendRedirect("jsp/errorPage.jsp");
			}
			
			
			
			
			
			
			
			
//			String userId = (String)session.getAttribute("userid");
			
		}

		/*-------------To Refer to View Bank Details Page after Receiving Details from MainDao through MainService--------------------*/
		if(action.equalsIgnoreCase("viewBankDetailsPage")){
			//Creating constructor of MainService
			MainService ms_ViewBankDetails = new MainService();
			//Creating constructor of MainModel
			MainModel mm_ReceivedBankDetails = new MainModel();
			mm_ReceivedBankDetails = null;
			String blank="";
			boolean check = false;
			//Getting value of a particular userid from session
			String userId = (String)session.getAttribute("userid");
			//Receiving data from User Interface
			String bankName = request.getParameter("bankName");
			String branchCode = request.getParameter("branchCode");
			String accountNumber = request.getParameter("accountNumber");
			//Creating constructor of MainModel and forwarding received data 
			MainModel mm_ViewBankdetails = new MainModel(userId, bankName, branchCode, accountNumber, blank);
			//Receiving in MainModel constructor from MainDao through MainService
			mm_ReceivedBankDetails = ms_ViewBankDetails.getViewBankDetails(mm_ViewBankdetails);
			try{
				//If some value is present against a particular user
				if(mm_ReceivedBankDetails.getAccountNumber()!=null){
					//Make the value received into a session attribute
					session.setAttribute("bankDetails", mm_ReceivedBankDetails);
					response.sendRedirect("jsp/viewBankDetails.jsp");
				}
				//If no value is present against a particular user throws NoBankRegistered exception
				else
					throw new Mainexception();
			}
			//Catch NoBankRegistered exception
			catch(Mainexception e){
				String message = e.NoBankRegistered();
				//Make the value received into a session attribute
				session.setAttribute("errorMessage", message);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		/*-----------To Refer to Update Bank Details Page after Receiving Details from MainDao through MainService----------------*/
		if(action.equalsIgnoreCase("updateBankDetailsPage")){
			//Creating constructor of MainService
			MainService ms_ViewBankDetails = new MainService();
			//Creating constructor of MainModel
			MainModel mm_ReceivedBankDetails = new MainModel();
			mm_ReceivedBankDetails = null;
			String blank="";
			boolean check = false;
			//Getting value of a particular userid from session
			String userId = (String)session.getAttribute("userid");
			//Receiving data from User Interface
			String bankName = request.getParameter("bankName");
			String branchCode = request.getParameter("branchCode");
			String accountNumber = request.getParameter("accountNumber");
			//Creating constructor of MainModel and forwarding received data 
			MainModel mm_ViewBankdetails = new MainModel(userId, bankName, branchCode, accountNumber, blank);
			//Receiving data in MainModel constructor from MainDao through MainService
			mm_ReceivedBankDetails = ms_ViewBankDetails.getViewBankDetails(mm_ViewBankdetails);
			try{
				//If no value is present against a particular user
				if(mm_ReceivedBankDetails.getAccountNumber()!=null){
					//Make the value received into a session attribute
					session.setAttribute("bankDetails", mm_ReceivedBankDetails);
					response.sendRedirect("jsp/updateBankDetails.jsp");
				}
				//If no value is not present against a particular user throws NoBankRegistered exception
				else
					throw new Mainexception();
			}
			//Catch NoBankRegistered exception
			catch(Mainexception e){
				String message = e.NoBankRegistered();
				//Make the value received into a session attribute
				session.setAttribute("errorMessage", message);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}


		/*--------------------------------To Insert Bank Details in Database---------------------------------------------*/
		if(action.equalsIgnoreCase("insertBankDetails")){
			//Creating constructor of MainService
			MainService ms_InsertBankDetails = new MainService();
			String blank="";
			String check = "";
			//Getting value of a particular userid from session
			String userId = (String)session.getAttribute("userid");
			//System.out.println(userId+"contrl");
			//Receiving data from User Interface
			String bankName = request.getParameter("bankName");
			String branchCode = request.getParameter("branchCode");
			String accountNumber = request.getParameter("accountNumber");
			//Creating constructor of MainModel and forwarding received data 
			MainModel mm_InsertBankdetails = new MainModel(userId, bankName, branchCode, accountNumber, blank);
			//Receiving data in String from MainDao through MainService
			check = ms_InsertBankDetails.getInsertBankDetails(mm_InsertBankdetails);
			//If some value is successfully inserted against a particular user
			if(check.equals("Inserted")){
				//Forwarding to Bank Addition Confirmation Page
				response.sendRedirect("jsp/confirmCreateBankDetails.jsp");
			}
			try{
				//If some value is already present against a particular user throws BankAlreadyRegisteredException
				if(check.equals("BankDetailsPresent")){
					throw new Mainexception();
				}
			}
			//catch BankAlreadyRegisteredException
			catch(Mainexception e){
				String message = e.BankAlreadyRegisteredException();
				//Make the value received into a session attribute
				session.setAttribute("errorMessage", message);
				response.sendRedirect("jsp/errorPage.jsp");
			}
			try{
				//If Account Number is already present against a previous user throws AccountNumberAlreadyExists
				if(check.equals("AccountNumberExists")){
					throw new Mainexception();
				}
			}
			//catch AccountNumberAlreadyExists
			catch(Mainexception e){
				String message = e.AccountNumberAlreadyExists();
				//Make the value received into a session attribute
				session.setAttribute("errorMessage", message);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}
		/*----------------To Update Bank Details  in Database----------------------*/
		if(action.equalsIgnoreCase("updateBankDetails"))
		{
			//Creating constructor of MainService
			MainService ms_UpdateBankDetails = new MainService();
			String blank="";
			boolean check = false;
			//Getting value of a particular userid from session
			String userId = (String)session.getAttribute("userid");
			//Receiving data from User Interface
			String bankName = request.getParameter("bankName");
			String branchCode = request.getParameter("branchCode");
			String accountNumber = request.getParameter("accountNumber");
			//Creating constructor of MainModel and forwarding received data 
			MainModel mm_UpdateBankdetails = new MainModel(userId, bankName, branchCode, accountNumber, blank);
			//Receiving data in boolean from MainDao through MainService
			check = ms_UpdateBankDetails.getUpdateBankDetails(mm_UpdateBankdetails);
			try{
				//If updated successfully against a particular user
				if(check)
				{
					//Forwarding to Bank Updation Confirmation Page
					response.sendRedirect("jsp/confirmUpdateBankDetails.jsp");
				}
				//If Account Number is already present against a previous user throws AccountNumberAlreadyExists
				else{
					throw new Mainexception();
				}
			}
			//catch AccountNumberAlreadyExists
			catch(Mainexception e){
				String message = e.AccountNumberAlreadyExists();
				//Make the value received into a session attribute
				session.setAttribute("errorMessage", message);
				response.sendRedirect("jsp/errorPage.jsp");
			}
		}

		/***-----------------------------------all processes of bank details END tag by 592399------------------------------------------***/	


	}	
}

