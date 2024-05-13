package ca.cn.scio.application.exception;

public enum ErrorCode {
	SCIO001("System error occurred. Please contact System Administrator"),
	SCIO002("Missing Mandatory field(s) or invalid data"),
	SCIO003("Business exception"),
	SCIO004("Missing Mandatory Header!"), 
	SCIO005("Firestore connection error"),
	SCIO006("Invalid Endpoint") ,

	SCIOAPP001("Terminal Not Found") ,
	SCIOAPP002("Customer Not Found") ,
	SCIOAPP003("Location Not Found") ,
	SCIOAPP004("Invalid CustomerNumber") ,
	SCIOAPP005("Invalid Terminal Id") ,
	SCIOAPP006("Invalid Customer Name") ,
	SCIOAPP007("Invalid PartyName or Address"),
	SCIOAPP008("Location present without Location Sequence Number") ,
	SCIOAPP009("Unable to find location for provided customer and location number ") ,

	SCIOAPP0010("Please provide comments with min 3 to max 255 character") ,
	SCIOAPP0011("Please provide valid comments") ,
	SCIOAPP0012("Invalid Phone Number") ,
	SCIOAPP0013("Invalid Business Phone Ext Number") ,
	SCIOAPP0014("Email should be of min 5 and max 30 character") ,
	SCIOAPP0015("Invalid Email") ,
	SCIOAPP0016("Invalid FirstName") ,
	SCIOAPP0017("Invalid LastName") ,

	SCIOREF001("Bad Request"),
	SCIOREF002("Terminal Not Found"),
	SCIOREF003("Json Parsing Exception"),
	SCIOREF004("Json Mapping Exception"),
	SCIOREF005("Equipment Types Not Found"),
	SCIOREF006("IO File Exception"),
	SCIOREF007("Reference Types Not Found"),

	SCIOCUST001("Customer Not Found"),
	SCIOCUST002("Json Processing Exception"),

	SCIOCUSTVE001("Invalid Name Parameter") ,
	SCIOCUSTVE002("Invalid Address Parameter") ,

	SCIOCAN001("Order Cannot be Processed");


	private final String description;

	ErrorCode(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static ErrorCode valueOfErrorDescription(String description) {
		for (ErrorCode errorCode : values()) {
			if (errorCode.description.equals(description)) {
				return errorCode;
			}
		}
		return null;
	}
}
