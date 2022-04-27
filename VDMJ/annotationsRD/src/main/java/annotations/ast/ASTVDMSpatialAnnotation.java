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
import com.fujitsu.vdmj.syntax.ClassReader;
import com.fujitsu.vdmj.ast.definitions.ASTClassDefinition;
import com.fujitsu.vdmj.ast.definitions.ASTExplicitOperationDefinition;

import com.fujitsu.vdmj.ast.statements.ASTStatement;
import com.fujitsu.vdmj.syntax.StatementReader;

import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.syntax.DefinitionReader;

import com.fujitsu.vdmj.ast.expressions.ASTExpression;
import com.fujitsu.vdmj.syntax.ExpressionReader;

import com.fujitsu.vdmj.ast.modules.ASTModule;
import com.fujitsu.vdmj.syntax.ModuleReader;

public class ASTVDMSpatialAnnotation extends ASTAnnotation
{
	private static final long serialVersionUID = 1L;

	public ASTVDMSpatialAnnotation(LexIdentifierToken name)
	{
		super(name);

	}

	@Override
	public void astAfter(DefinitionReader reader, ASTDefinition def)
	{
		System.out.println("--------------- ASTDefinition --------------");
		System.out.println("Name: "+def.name.name+", kind: "+def.kind());
		System.out.println("toString: "+ def.toString());
		if (def.kind().equals("explicit operation")){
			System.out.println("typecast to ASTExplicitOperationDefinition");
			ASTExplicitOperationDefinition expOpDef = (ASTExplicitOperationDefinition) def;
			ASTStatement defbody = expOpDef.body;
			System.out.println("body kind: " + defbody.kind());
		}
	}

	@Override
	public void astAfter(StatementReader reader, ASTStatement stmt)
	{
		System.out.println("--------------- ASTStatement --------------");
	}

	@Override
	public void astAfter(ExpressionReader reader, ASTExpression exp)
	{
		System.out.println("--------------- ASTExpression --------------");
	}

	@Override
	public void astAfter(ModuleReader reader, ASTModule module)
	{
		System.out.println("--------------- ASTModule --------------");
	}

	@Override
	public void astAfter(ClassReader reader, ASTClassDefinition clazz)
	{
		System.out.println(args);
		
		// read file .sp
		// String filename = "scenario1.sp";
		String f = args.toString();
		String filename = f.replaceAll("[()]", "");;
		List<String> scenarioList = new ArrayList<>();

		try {
            scenarioList = readfile(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

		System.out.println(scenarioList);

		// get all elements in class
		// available in clazz.definitions

		// get all geometry types
		List<ASTDefinition> geometryTypes = new Vector<ASTDefinition>();
		// get all geometry relations
		List<ASTDefinition> geometryRelations = new Vector<ASTDefinition>();
		// get all geometry instances
		List<ASTDefinition> geometryInstances = new Vector<ASTDefinition>();

		for(int i =0; i<clazz.definitions.size(); i++){
			if(clazz.definitions.get(i).kind() == "type"){
				geometryTypes.add(clazz.definitions.get(i));
			}
			else if(clazz.definitions.get(i).kind() == "explicit operation"){
				geometryRelations.add(clazz.definitions.get(i));
			}
			else if(clazz.definitions.get(i).kind() == "instance variable"){
				geometryInstances.add(clazz.definitions.get(i));
			}
			else {
				System.out.println("Class def "+i+" = "+clazz.definitions.get(i).kind());
			}
		}

		int nInstances = scenarioList.size();
		List<VDMGeometry> vdmGeometries = new Vector<VDMGeometry>();

		List<String> arrangedTypes = arrangeTypes(geometryTypes);
		System.out.println("Arranged types: "+arrangedTypes);

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		for(int i = 0; i<scenarioList.size(); i++){
			boolean status = true;
			if(!checkType(scenarioList.get(i), arrangedTypes, vdmGeometries)){
				status = false;
			}
			if(!status){
				System.out.println("Type check failed.");
			} else{
				status = parseToInstance(scenarioList.get(i), arrangedTypes, vdmGeometries);
			}
			if(!status){
				System.out.println("Parsing failed.");
			}
		}

		System.out.println("--- VDMGeometry instances ---");
		for(int i = 0; i<vdmGeometries.size(); i++){
			if(vdmGeometries.get(i) != null){
				System.out.println(vdmGeometries.get(i).toString());
			}
		}

	}
	private List<String> arrangeTypes(List l)
	{
		List<String> arrangedTypes = new ArrayList<>();
		for(int i=0; i<l.size(); i++){
		// static public point2D = compose point2D of x:rat, y:rat end
		//				circle = compose circle of center: (unresolved point2D), radius:nat
		//https://stackoverflow.com/questions/12884573/split-string-by-all-spaces-except-those-in-brackets
		String [] s1 = l.get(i).toString().substring(14,l.get(i).toString().length()-4).replace(",","").split("\\s+(?![^\\()]*\\))");
		// remove 1,2,3,4 from array
		StringBuilder sb = new StringBuilder(s1[0]);
		for(int n = 5; n<s1.length; n++){
			sb.append(" ");
				String temp = s1[n];
				if(s1[n].contains("(") && s1[n].contains(")")){
					//Extract "prop:type" from "prop:(unresolved type)"
					String prop = temp.substring(0, temp.indexOf("("));
					String type = temp.substring(temp.indexOf("(")+1,temp.indexOf(")"));
					String[] splitTemp = type.split("\\s");
					type = splitTemp[splitTemp.length-1];
					temp = prop+type;
				}
				sb.append(temp);
			}
			arrangedTypes.add(sb.toString());
		}
		return arrangedTypes;
	}

	// https://mkyong.com/java/java-how-to-read-a-file-into-a-list/
	private static List readfile(String fileName) throws IOException {

        List<String> result = new ArrayList<>();
        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
				if(!(line.equals(""))){
                result.add(line);
				}
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return result;
    }

	/**
	 * Returns an indication of whether the instance passed the typecheck
	 * @param instance the instance to typecheck
	 * @param validTypes the valid types from the VDM specification
	 * @param vdmGeometries the previously passed types
	 */
	private boolean checkType(String instance, List<String> validTypes, List<VDMGeometry> vdmGeometries)
	{
		boolean res = false;
		String[] instanceProps = instance.split("\\s+(?![^\\()]*\\))");
		String instanceName = instanceProps[0];
		String instanceType = instanceProps[1];
		for(int i = 0; i < validTypes.size() && !res; i++){
			String[] typeProps = validTypes.get(i).split("\\s+(?![^\\()]*\\))");
			String typeName = typeProps[0];
			if(instanceType.equals(typeName)) res = true;
			if(res){ // Check number of arguments
				int nArgsProvided = instanceProps.length-2;
				int nArgsRequired = typeProps.length-1;
				if(nArgsProvided != nArgsRequired){
					System.out.println("Instance " + instanceName + " has " + nArgsProvided + 
									   " arguments. Expected " + nArgsRequired);
					res = false;
				}
			}
			if(res){ // Check argument types
				for(int j=0; j<(typeProps.length-1); j++){
					String expectedType = "";
					if(typeProps[j+1].contains(":")){
						expectedType = typeProps[j+1].split(":")[1];
					} else{
						expectedType = typeProps[j+1];
					}
					String providedArg = instanceProps[j+2];
					if(expectedType.equals("rat")){
						try { double rat = Double.parseDouble(providedArg);
						} catch (NumberFormatException nfe) {
							System.out.println("Provided argument: "+ providedArg +" is not a rational number");
							res = false;
						}
					}
					else{
						res = false;
						expectedType = expectedType.replaceAll("[()]|unresolved|\\s", ""); // Remove "(unresolved ...)"
						for(int k = 0; k < vdmGeometries.size() && !res; k++){
							VDMGeometry checkedType = vdmGeometries.get(k);
							String defName = checkedType.getName();
							String defType = checkedType.getType();
							if (defName.equals(providedArg) && defType.equals(expectedType)){
								res = true;
							}
						}
					}
					if(!res) System.out.println("Unsupported type: "+providedArg+", expectedType: "+expectedType);
				}
				if(!res) System.out.println("Supported types are: "+validTypes);
			}
		}
		return res;
	}

	private boolean parseToInstance(String instance, List<String> validTypes, List<VDMGeometry> geometries){
		String[] instanceProps = instance.split("\\s+(?![^\\()]*\\))");
		VDMGeometry newGeometry = new VDMGeometry();
		newGeometry.setName(instanceProps[0]);
		newGeometry.setType(instanceProps[1]);
		boolean res = false;
		for(int i = 2; i<instanceProps.length; i++){
			res = false;
			VDMGeometry temp = null;
			String attrKey = "";
			Value attrVal = null;
			// Find name of attribute (key)
			for(int j = 0; j < validTypes.size(); j++){
				String[] validProps = validTypes.get(j).split("\\s+(?![^\\()]*\\))");
				if(validProps[0].equals(newGeometry.getType())){
					attrKey = validProps[i-1];
					res = true;
					break;
				}
			}
			// Check if parameter references other instance
			for(int j = 0; j<geometries.size(); j++){
				if(geometries.get(j).getName().equals(instanceProps[i])){
					temp = geometries.get(j);
					break;
				}
			}
			if(temp != null){
				attrVal = temp;
			} else { // Here we assume rationals/reals are the only alternative to defined types
				RealValue realVal = new RealValue();
				realVal.v = Double.parseDouble(instanceProps[i]);
				attrVal = realVal;
			}
			newGeometry.addAttribute(attrKey, attrVal);
		}
		geometries.add(newGeometry);
		return res;
	};

}