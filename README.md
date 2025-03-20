
Customer Service Application

1.	APIs
  •	Create Customer
    POST - /customer-service/api/v1/customer
    Request body 
    {
	    “firstName”:”String”,
	    “lastName”:”String”,
	    “dateOfBirth”:”String but in LocalDate format”
    }

    Response 200:
    {
	    “customerId”:”String”
    }

    Response 4XX
    {
	    “errorCode”;”String”
	    “errorMessage”:”String”
    }

    •	Get Customer
      GET - /customer-service/api/v1/customer/{customerId}

	

2.	Validations
•	Validation added for firstName,LastName with min char as 3 and max chars as 50
•	Validation added for dateOfBirth field for past date


3.	Enhancements
•	Global Exception handler added.
•	Unit test cases added.
•	Logs added.

4. To run the project import it on Intellij and run the CustomerServiceApplication.java file
