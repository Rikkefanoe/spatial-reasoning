package annotations.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;
import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.syntax.ClassReader;
import com.fujitsu.vdmj.ast.definitions.ASTClassDefinition;
import com.fujitsu.vdmj.ast.expressions.ASTExpression;



public class ASTVDMSpatialAnnotation extends ASTAnnotation
{
	private static final long serialVersionUID = 1L;

	public ASTVDMSpatialAnnotation(LexIdentifierToken name)
	{
		super(name);

	}

	@Override
	public void astAfter(ClassReader reader, ASTClassDefinition clazz)
	{
		VDMGeometry p1 = new VDMGeometry();
		RealValue p1x = new RealValue();
		RealValue p1y = new RealValue();

		p1.setName("p1");
		p1.setType("Point2d");
		p1x.v = 0;
		p1y.v = 0;
		p1.addAttribute("x", p1x);
		p1.addAttribute("y", p1y);

		VDMGeometry p2 = new VDMGeometry();
		RealValue p2x = new RealValue();
		RealValue p2y = new RealValue();

		p2.setName("p2");
		p2.setType("Point2d");
		p2x.v = 5;
		p2y.v = 0;
		p2.addAttribute("x", p2x);
		p2.addAttribute("y", p2y);

		System.out.println("--- VDMGeometry instances ---");
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		

		// read in relation and test

		// for now hardcoded

	   EvalExpression evalExpression = new EvalExpression();
	   evalExpression.root = new EvalExpression.Node("<");
	   evalExpression.root.left = new EvalExpression.Node(p1x.toString());
	   evalExpression.root.right = new EvalExpression.Node(p2x.toString());

	   Boolean eval = EvalExpression.evalTreeBool(evalExpression.root);
	   System.out.println(eval);





	}


	/*********************************************************************************** */

	// @Override
	// public void astAfter(ClassReader reader, ASTClassDefinition clazz)
	// {
	// 	// System.out.println(args);

		
	// 	// read file .sp
	// 	// String filename = "scenario1.sp";
	// 	String f = args.toString();
	// 	String filename = f.replaceAll("[()]", "");;
	// 	List<String> scenarioList = new ArrayList<>();

	// 	try {
    //         scenarioList = readfile(filename);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

	// 	// System.out.println(scenarioList);

	// 	// get all elements in class
	// 	// available in clazz.definitions

	// 	// get all geometry types
	// 	List<ASTDefinition> geometryTypes = new Vector<ASTDefinition>();
	// 	// get all geometry relations
	// 	List<ASTDefinition> geometryRelations = new Vector<ASTDefinition>();
	// 	// get all geometry instances
	// 	List<ASTDefinition> geometryInstances = new Vector<ASTDefinition>();

	// 	for(int i =0; i<clazz.definitions.size(); i++){
	// 		if(clazz.definitions.get(i).kind() == "type"){
	// 			geometryTypes.add(clazz.definitions.get(i));
	// 		}
	// 		if(clazz.definitions.get(i).kind() == "explicit operation"){
	// 			geometryRelations.add(clazz.definitions.get(i));
	// 		}
	// 		if(clazz.definitions.get(i).kind() == "instance variable"){
	// 			geometryInstances.add(clazz.definitions.get(i));
	// 		}
	// 	}
	// 	// System.out.println(	geometryRelations.get(0));
	// 	/*
	// 	leftOf: ((unresolved point2D) * (unresolved point2D) ==> bool)
    //     leftOf(p1, p2) ==
	// 	return (((p1.x) < (p2.x)))
 	// 	*/




	// 	int nInstances = scenarioList.size();
	// 	VDMGeometry[] vdmGeometries = new VDMGeometry[nInstances];

	// 	// System.out.println("geometryTypes");
	// 	// System.out.println(geometryTypes);
	// 	// System.out.println("geometryRelations");
	// 	// System.out.println(geometryRelations);
	// 	// System.out.println("geometryInstances");
	// 	// System.out.println(geometryInstances);

	// 	List<String> arrangedTypes = arrangeTypes(geometryTypes);
	// 	// System.out.println(arrangedTypes);

	// 	for(int i = 0; i<scenarioList.size(); i++){
	// 		boolean status = false;
	// 		for(int j = 0; j<geometryTypes.size(); j++){
	// 			if(checkType(scenarioList.get(i), arrangedTypes.get(j)))
	// 			{
	// 				status = true;
	// 			}
	// 			if(status){
	// 				String[] instanceProps = scenarioList.get(i).split("\\s+(?![^\\()]*\\))");
	// 				vdmGeometries[i] = new VDMGeometry();
	// 				vdmGeometries[i].setName(instanceProps[0]);
	// 				vdmGeometries[i].setType(instanceProps[1]);
	// 			}
	// 		}
	// 		if(!status){
	// 			System.out.println("Type check failed.");
	// 		}
	// 	}
		

	// 	System.out.println("--- VDMGeometry instances ---");
	// 	for(int i = 0; i<scenarioList.size(); i++){
	// 		if(vdmGeometries[i] != null){
	// 			System.out.println(vdmGeometries[i].toString());
	// 		}
	// 	}



	// 	String s1= geometryRelations.get(0).toString();
	// 	int line1 = s1.indexOf("\n");
	// 	int line2 = s1.indexOf("\n",line1+1);

	// 	String s2 = s1.substring(0,line1);
	// 	String s3 = s1.substring(line1+1,line2);
	// 	String s4 = s1.substring(line2+1);
		
	//    // System.out.println(s2);
	//    // System.out.println(s3);
	//    // System.out.println(s4);

	//    //  System.out.println(a.substring(0,a.indexOf("\n")));

	//    // get line 3 and split into right, left and sign
	//    int space1 = s4.indexOf(" ")+1;
	//    int space2 = s4.indexOf(" ", space1+1);
	//    int space3 = s4.indexOf(" ", space2);

	//    String s4_1 = s4.substring(space1); // (((p1.x) < (p2.x)))
	// //    System.out.println(space1 + " " + space2 + " " + space3);
	//    String sign_str = s4_1.substring(space2, space3);
	// //    System.out.println(sign_str);



	//    int gtSignIdx = s4_1.indexOf("<");
	//    String s4_lhs = s4_1.substring(0,gtSignIdx).trim();
	//    String s4_rhs = s4_1.substring(gtSignIdx+1).trim();
	// //    System.out.println(s4_lhs);

	//    s4_lhs = removeParanthesis(s4_lhs);
	//    s4_rhs = removeParanthesis(s4_rhs);

	// //    System.out.println(s4_lhs); // (p1.x)
	// //    System.out.println(s4_rhs); // (p2.x)


	//    EvalExpression evalExpression = new EvalExpression();
	//    evalExpression.root = new EvalExpression.Node("<");
	//    evalExpression.root.left = new EvalExpression.Node(s4_lhs);
	//    evalExpression.root.left = new EvalExpression.Node(s4_rhs);

	//    // Boolean eval = EvalExpression.evalTreeBool(evalExpression.root);
	//    // System.out.println(eval);


	



	// }
	// private List<String> arrangeTypes(List l)
	// {
	// 	List<String> arrangedTypes = new ArrayList<>();

	// 	for(int i=0; i<l.size(); i++){
	// 	// static public point2D = compose point2D of x:rat, y:rat end
	// 	//				circle = compose circle of center: (unresolved point2D), radius:nat
	// 	String [] s1 = l.get(i).toString().substring(14,l.get(i).toString().length()-4).replace(",","").split("\\s+(?![^\\()]*\\))"); //https://stackoverflow.com/questions/12884573/split-string-by-all-spaces-except-those-in-brackets

	// 	// remove 1,2,3,4 from array
	// 	StringBuilder sb = new StringBuilder(s1[0]);
	// 	for(int n =s1.length-1; n>4; n--){
	// 			sb.append(" ");
	// 			sb.append(s1[n]);
	// 		}

	// 		arrangedTypes.add(sb.toString());
	// 	}
	// 		// System.out.println(arrangedTypes);

	// 	return arrangedTypes;

	// }
	// // https://mkyong.com/java/java-how-to-read-a-file-into-a-list/
	// private static List readfile(String fileName) throws IOException {

    //     List<String> result = new ArrayList<>();
    //     BufferedReader br = null;

    //     try {

    //         br = new BufferedReader(new FileReader(fileName));

    //         String line;
    //         while ((line = br.readLine()) != null) {
	// 			if(!(line.equals(""))){
    //             result.add(line);
	// 			}
    //         }

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } finally {
    //         if (br != null) {
    //             br.close();
    //         }
    //     }

    //     return result;
    // }

	// private boolean checkType(String instance, String type)
	// {
	// 	boolean res = false;
	// 	String[] instanceProps = instance.split("\\s+(?![^\\()]*\\))");
	// 	String[] typeProps = type.split("\\s+(?![^\\()]*\\))");
	// 	String instanceName = instanceProps[0];
	// 	String instanceType = instanceProps[1];
	// 	String typeName = typeProps[0];
	// 	if(instanceType.equals(typeName)){
	// 		res = true;
	// 	}
	// 	if(res){
	// 		// Check number of arguments
	// 		int nArgsProvided = instanceProps.length-2;
	// 		int nArgsRequired = typeProps.length-1;
	// 		if(nArgsProvided != nArgsRequired){
	// 			System.out.println("Instance " + instanceName + " has " + nArgsProvided + 
	// 							   " arguments. Expected " + nArgsRequired);
	// 			res = false;
	// 		} else {
	// 			// Check argument types
	// 			for(int i=0; i<nArgsRequired; i++){
	// 				String expectedType = typeProps[i+1].split(":")[1];
	// 				String providedArg = instanceProps[i+2];
	// 				if(expectedType.equals("rat")){
	// 					try {
	// 						double rat = Double.parseDouble(providedArg);
	// 					} catch (NumberFormatException nfe) {
	// 						System.out.println("Provided argument: "+ providedArg +" is not a rational number");
	// 						res = false;
	// 					}
	// 				}
	// 			}
	// 		}
	// 	}
	// 	return res;
	// }



	// private String removeParanthesis(String s){
	// 	long countParanthesisStart = s.chars().filter(ch -> ch=='(').count();
	// 	long countParanthesisEnd = s.chars().filter(ch -> ch==')').count();

	// 	if(countParanthesisStart>countParanthesisEnd){ // lhs
	// 		for(int i = 0; i<(countParanthesisStart-countParanthesisEnd); i ++){
	// 			s = s.substring(s.indexOf("(")+1); 
	// 		}
	// 	}else{
	// 		for(int i = 0; i<(countParanthesisEnd-countParanthesisStart); i ++){ //(x.2))
	// 			s = s.substring(0,s.lastIndexOf(")")); 
	// 		}
	// 	}

	// 	try {
	// 		s = s.substring(1,s.length()-1);
		
	// 	}finally{

	// 	}

	// 	return s;
	// }
}