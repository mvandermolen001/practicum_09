# Practicum thema 09
06-11-2023
Java 17.0.8


## Description
This repository contains the files for a tool that predicts penguin sex based on several physical characteristics.  


## Installation
Before using this script, make sure to understand the command line arguments. These are as follows:

-f,--FILEPATH -arg-		Requests the path to a batch file, this file has
				to be an arff. This file must include the following attributes: Species, culmen depth,
                		culmen length, bodymass, delta N15, delta N13, sex <br />
 -h,--help			Requests help  <br />
 -s,--SINGLECASE -arg-		Requests the information needed to classify a
                		single case. Fill in your values and separate
                		them with a ',' and use "" around the arguments.Please make sure the argument
                		uses the following order: Species, culmen depth,
                		culmen length, bodymass, delta N15, delta N13, sex.
                		As an example: "Gentoo","34","45","9000","22","44","MALE"  <br />
 -w,--write			If option given, the result will be written to a
                		file. If provided, this will write singlecases to the file, predicted_instance.csv. Bulk data will be written to: predicted_data.csv.  

	

## Usage



## Support
For any reported issues or help: m.van.der.molen@st.hanze.nl

