package com.ilp.proj1.model;
/**
 * 
 * @author 594486
 *
 */
public class MainModel implements java.io.Serializable {
	/**
	 * @default serial version
	 */
	private static final long serialVersionUID = 1L;
	
	
/***************************************************************************************************************************/	
	/**
	 * @variable for login 
	 */
	private String password; //customer log in password
	private String status; //account status
	private int count;     //count is 0 when first account is created .when logout ststus will be 'one'
	private String key;
	
	
	
	/**
	 * @variable for user details
	 */
	
	private String userName; //customer name
	private String gender;
	private String dateOfBirth;
	private String address;
	private String licensedFirst; 
	private String licenseStatus;
 	private String customerSSN;         
	private String occupation;
	private double annualIncome; 
	private String higherEducation;
	private String phoneNumber;
	private String emailId;
	private String customerImage;
	private String securityQuestion;
	private String securityQusAnswer;
	
	/*
	 * @variable vehicle
	 **/
	private int vehicleId;
	private String vehicleNumber;
	private String maker;
	private String model;
	private int manufacturingYear;
	private String type;//type of vehicle like Coupe,sedan etc..
	private double roadPrice;  //car price less than 1 cr.
	private String imageOfNumberPlate;
	private String primaryUseOfVehicle;
	
	/*
	 * @variable buy insurance
	 */
	
	private String insuranceId;
	private double coverageAmount;
	private int duration;
	private String startDate;
	private double monthlyPremium;
	private String premiumPaymentMode; //premium payment mode for insurance
	private String insuranceType;
	private String statusOfInsurance;
	
   /**
	 * @variable bank details
	 */
	private String bankName;
	private String branchCode;
	private String accountNumber;
	
	/**
	 * @variable for add nominee 
	 */
	private String nomineeName;
	private String nominAddress;
	private int nomineeSSN;  
	private String nomineeDOB;
	private String relWithNominee; //relation ship witn nominee with customer
	private String userId;         //customer log in id
	private String insuranceNumber;//insurance number particular to a nominee
	
/*******************************************Constructor declearation***************************************************************************/	
	 
	/**
	 * @594486
	 * @default constructor
	 */
	public MainModel() 
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * @author 592274
	 */
	
	/**
	 * @param username
	 * @param password
	 * @param status
	 * @param count
	 */
	public MainModel(String userId, String password, String status, int count,String key) 
	{
		
		this.userId = userId;
		this.password = password;
		this.status = status;
		this.count = count;
		this.key=key;
	}
	/**
	 * @param username
	 * @param password
	 */
	public MainModel(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	
	
	public MainModel(String securityQuestion, String securityQusAnswer, String userId, String dateOfBirth) {
		this.userId = userId;
		this.securityQuestion=securityQuestion;
		this.securityQusAnswer=securityQusAnswer;
		this.dateOfBirth=dateOfBirth;
	}

	
	/**
	 * @author  678232
	 * UserRegistration Constructor
	 * 
	 */
	public MainModel(String uid,String name,String email,String cn,
			String gender,String dob,String addr,String ag,
			String st,String ssn,String occu,double ani,String edu,String sq,String sa)
	{
		this.userId=uid;
		this.userName=name;
		this.emailId=email;
		this.phoneNumber=cn;
		this.gender=gender;
		this.dateOfBirth=dob;
		this.address=addr;
		this.licensedFirst=ag;
		this.licenseStatus=st;
		this.customerSSN=ssn;
		this.occupation=occu;
		this.annualIncome=ani;
		this.higherEducation=edu;
		this.securityQuestion=sq;
		this.securityQusAnswer=sa;
	}
	
	
	
	public MainModel(String email,String cn,String addr,
			String st,String occu,double ani,String edu)
	{
		this.emailId=email;
		this.phoneNumber=cn;
		this.address=addr;
		this.licenseStatus=st;
		this.occupation=occu;
		this.annualIncome=ani;
		this.higherEducation=edu;
		
	}
	/**
	 * @author  592399
	 * BankDetails Constructor
	 */
	public MainModel(String userId, String bankName, String branchCode, String accountNumber, String blank){
		this.userId = userId;
		this.bankName = bankName;
		this.branchCode = branchCode;
		this.accountNumber = accountNumber;
	}
	
	/**
	 * @author 678271
	 */
	public MainModel(int vehicleId,String maker,String model,int manufacturingYear,String type,double roadPrice,String primaryUseOfVehicle)
	{
		this.vehicleNumber=vehicleNumber;
		this.maker=maker;
		this.model=model;
		this.manufacturingYear=manufacturingYear;
		this.type=type;
		this.roadPrice=roadPrice;
		this.primaryUseOfVehicle=primaryUseOfVehicle;
		this.imageOfNumberPlate=imageOfNumberPlate;
		this.userId=userId;
		this.vehicleId=vehicleId;
		
	}
	
	
/**********************************************Setter and getter declearation************************************/
	/**
	 * @return the insuranceType
	 */
	public String getInsuranceType() {
		return insuranceType;
	}

	/**
	 * @param insuranceType the insuranceType to set
	 */
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getNominAddress() {
		return nominAddress;
	}
	public void setNominAddress(String nominAddress) {
		this.nominAddress = nominAddress;
	}
	public int getNomineeSSN() {
		return nomineeSSN;
	}
	public void setNomineeSSN(int nomineeSSN) {
		this.nomineeSSN = nomineeSSN;
	}
	public String getNomineeDOB() {
		return nomineeDOB;
	}
	public void setNomineeDOB(String nomineeDOB) {
		this.nomineeDOB = nomineeDOB;
	}
	public String getRelWithNominee() {
		return relWithNominee;
	}
	public void setRelWithNominee(String relWithNominee) {
		this.relWithNominee = relWithNominee;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the licensedFirst
	 */
	public String getLicensedFirst() {
		return licensedFirst;
	}

	/**
	 * @param licensedFirst the licensedFirst to set
	 */
	public void setLicensedFirst(String licensedFirst) {
		this.licensedFirst = licensedFirst;
	}

	/**
	 * @return the licenseStatus
	 */
	public String getLicenseStatus() {
		return licenseStatus;
	}

	/**
	 * @param licenseStatus the licenseStatus to set
	 */
	public void setLicenseStatus(String licenseStatus) {
		this.licenseStatus = licenseStatus;
	}

	/**
	 * @return the customerSSN
	 */
	public String getCustomerSSN() {
		return customerSSN;
	}

	/**
	 * @param customerSSN the customerSSN to set
	 */
	public void setCustomerSSN(String customerSSN) {
		this.customerSSN = customerSSN;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the annualIncome
	 */
	public double getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @return the higherEducation
	 */
	public String getHigherEducation() {
		return higherEducation;
	}

	/**
	 * @param higherEducation the higherEducation to set
	 */
	public void setHigherEducation(String higherEducation) {
		this.higherEducation = higherEducation;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the customerImage
	 */
	public String getCustomerImage() {
		return customerImage;
	}

	/**
	 * @param customerImage the customerImage to set
	 */
	public void setCustomerImage(String customerImage) {
		this.customerImage = customerImage;
	}

	/**
	 * @return the securityQuestion
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	/**
	 * @param securityQuestion the securityQuestion to set
	 */
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	/**
	 * @return the securityQusAnswer
	 */
	public String getSecurityQusAnswer() {
		return securityQusAnswer;
	}

	/**
	 * @param securityQusAnswer the securityQusAnswer to set
	 */
	public void setSecurityQusAnswer(String securityQusAnswer) {
		this.securityQusAnswer = securityQusAnswer;
	}

	/**
	 * @return the vehicleId
	 */
	public int getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the vehicleNumber
	 */
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber the vehicleNumber to set
	 */
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the maker
	 */
	public String getMaker() {
		return maker;
	}

	/**
	 * @param maker the maker to set
	 */
	public void setMaker(String maker) {
		this.maker = maker;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the manufacturingYear
	 */
	public int getManufacturingYear() {
		return manufacturingYear;
	}

	/**
	 * @param manufacturingYear the manufacturingYear to set
	 */
	public void setManufacturingYear(int manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	/**
	 * @return the roadPrice
	 */
	public double getRoadPrice() {
		return roadPrice;
	}

	/**
	 * @param roadPrice the roadPrice to set
	 */
	public void setRoadPrice(double roadPrice) {
		this.roadPrice = roadPrice;
	}

	/**
	 * @return the imageOfNumberPlate
	 */
	public String getImageOfNumberPlate() {
		return imageOfNumberPlate;
	}

	/**
	 * @param imageOfNumberPlate the imageOfNumberPlate to set
	 */
	public void setImageOfNumberPlate(String imageOfNumberPlate) {
		this.imageOfNumberPlate = imageOfNumberPlate;
	}

	/**
	 * @return the primaryUseOfVehicle
	 */
	public String getPrimaryUseOfVehicle() {
		return primaryUseOfVehicle;
	}

	/**
	 * @param primaryUseOfVehicle the primaryUseOfVehicle to set
	 */
	public void setPrimaryUseOfVehicle(String primaryUseOfVehicle) {
		this.primaryUseOfVehicle = primaryUseOfVehicle;
	}

	/**
	 * @return the insuranceId
	 */
	public String getInsuranceId() {
		return insuranceId;
	}

	/**
	 * @param insuranceId the insuranceId to set
	 */
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	/**
	 * @return the coverageAmount
	 */
	public double getCoverageAmount() {
		return coverageAmount;
	}

	/**
	 * @param coverageAmount the coverageAmount to set
	 */
	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the monthlyPremium
	 */
	public double getMonthlyPremium() {
		return monthlyPremium;
	}

	/**
	 * @param monthlyPremium the monthlyPremium to set
	 */
	public void setMonthlyPremium(double monthlyPremium) {
		this.monthlyPremium = monthlyPremium;
	}

	/**
	 * @return the premiumPaymentMode
	 */
	public String getPremiumPaymentMode() {
		return premiumPaymentMode;
	}

	/**
	 * @param premiumPaymentMode the premiumPaymentMode to set
	 */
	public void setPremiumPaymentMode(String premiumPaymentMode) {
		this.premiumPaymentMode = premiumPaymentMode;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the statusOfInsurance
	 */
	public String getStatusOfInsurance() {
		return statusOfInsurance;
	}

	/**
	 * @param statusOfInsurance the statusOfInsurance to set
	 */
	public void setStatusOfInsurance(String statusOfInsurance) {
		this.statusOfInsurance = statusOfInsurance;
	}

}
