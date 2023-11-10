# Practicum thema 09

06-11-2023  
Java 17.0.8  


# Description

This repository contains the files for a tool that predicts penguin sex based on several physical characteristics. It also contrains r files, these files detail the steps taken to produce the tool.  


# Installation

Before using this script, make sure to understand the command line arguments. These are as follows:

* -f,--FILEPATH ARGUMENT	| Requests the path to a batch file, this file has
				to be an .arff. This file must include the following attributes: Species 
				(Gentoo, Adelie, Chinstrap), culmen depth, culmen length, bodymass, delta N15,
				delta N13, sex (Male, Female)
 * -h,--help			| Requests help 
 * -s,--SINGLECASE ARGUMENT	| Requests the information needed to classify a
                		single case. Fill in your values and separate
                		them with a ',' and use "" around the arguments.Please make sure the argument
                		uses the following order: Species, culmen depth,
                		culmen length, bodymass, delta N15, delta N13, sex.
                		As an example: "Gentoo","34","45","9000","22","44","MALE" 
 * -w,--write			| If option given, the result will be written to a
                		file. If provided, this will write singlecases to the file, predicted_instance.csv. 
				Bulk data will be written to: predicted_data.csv.  

	

# Usage

For a ready-to-use example on how to run this tool, please move to the wrapper folder and use:  
```
java -jar ./wrapper/wrapper-1.0-SNAPSHOT-all.jar -s "Gentoo","34","45","9000","22","44","MALE"
```	  

Or: 
```
java -jar ./wrapper/wrapper-1.0-SNAPSHOT-all.jar -f ./r_files/data/adjusted_penguin_data.arff		
```

# Support

For any reported issues or help: m.van.der.molen@st.hanze.nl

