
# A spatial programming toolkit based on formal modelling using VDM
##  R&D Project - Aarhus University

This project provides a prototype of a toolkit to evaluate spatial relations. 
The tool is based on the annotations using the AST from VDMJ (https://github.com/nickbattle/vdmj) and uses the Z3 solver(https://github.com/Z3Prover/z3) to perform the evaluation. 
The spatial relations are defined in VDM++ (https://www.overturetool.org/download/examples/VDM++/) and uses a separate file to define scenarios i.e. does 2 line segments intersect?

### The repository is structured with 3 folders: 
>**Demo**
- Includes a definition file, scenario file, z3 jar, vdmj annotation jar and vdmj that are used to execute a small demo to evaluate the equality of two numbers for proof of concept. 
 - To run the demo, change directory to the Demo folder and run: *java -cp ".;vdmj-4.4.5.jar;annotationsRD-4.4.5-SNAPSHOT.jar;com.microsoft.z3.jar" com.fujitsu.vdmj.VDMJ  -annotations -vdmpp .\spatialDefinitions.vdmpp*
 
>Development
- Includes subfolders:

  - expressions (contains a z3 visitor)

  - spatialPureVDM (very early definitions in vdm - only used as an early starter)

  - Testproj1 ( initial tests of how to define custom annotations from vdmj)

  - vdmproj1 (early prototype source)
>VDMJ
- includes the folder: *anntationsRD* which is the actual source files containing the annotation '@VDMSpatial(...)', the z3 visitor and the z3 evaluation. 
- To use this project for further development the complete folder *annotationsRD* should be copied into the VDMJ source code where it can be compiled using 'mvn clean install' from within the folder. 

