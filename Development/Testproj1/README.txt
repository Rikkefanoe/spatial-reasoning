To generate new annotations use the vdmj repostitory from Nick Battle:
	https://github.com/nickbattle/vdmj 
	using the command : mvn clean install -> probably inside the new annotations folder depending on how much is needed to be rebuild. 


To run the files in this folder use the following command in the terminal:

java -cp ".;vdmj-4.4.5-SNAPSHOT-220209.jar;annotationsRD-4.4.5.jar;annotations-4.4.5.jar" com.fujitsu.vdmj.VDMJ -i -annotations -vdmpp .\demoClass.vdmpp .\Test1.vdmpp .\test3.vdmpp
