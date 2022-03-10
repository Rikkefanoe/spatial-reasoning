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
			if(clazz.definitions.get(i).kind() == "explicit operation"){
				geometryRelations.add(clazz.definitions.get(i));
			}
			if(clazz.definitions.get(i).kind() == "instance variable"){
				geometryInstances.add(clazz.definitions.get(i));
			}
		}

		int nInstances = scenarioList.size();
		VDMGeometry[] vdmGeometries = new VDMGeometry[nInstances];

		List<String> arrangedTypes = arrangeTypes(geometryTypes);
		System.out.println(arrangedTypes);

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		for(int i = 0; i<scenarioList.size(); i++){
			boolean status = true;
			if(!checkType(scenarioList.get(i), arrangedTypes, vdmGeometries)){
				status = false;
			}
			if(status){
				String[] instanceProps = scenarioList.get(i).split("\\s+(?![^\\()]*\\))");
				vdmGeometries[i] = new VDMGeometry();
				vdmGeometries[i].setName(instanceProps[0]);
				vdmGeometries[i].setType(instanceProps[1]);
			}
			if(!status){
				System.out.println("Type check failed.");
			}
		}

		System.out.println("--- VDMGeometry instances ---");
		for(int i = 0; i<scenarioList.size(); i++){
			if(vdmGeometries[i] != null){
				System.out.println(vdmGeometries[i].toString());
			}
		}

	}
	private List<String> arrangeTypes(List l)
	{
		List<String> arrangedTypes = new ArrayList<>();

		for(int i=0; i<l.size(); i++){
		// static public point2D = compose point2D of x:rat, y:rat end
		//				circle = compose circle of center: (unresolved point2D), radius:nat
		String [] s1 = l.get(i).toString().substring(14,l.get(i).toString().length()-4).replace(",","").split("\\s+(?![^\\()]*\\))"); //https://stackoverflow.com/questions/12884573/split-string-by-all-spaces-except-those-in-brackets

		// remove 1,2,3,4 from array
		StringBuilder sb = new StringBuilder(s1[0]);
		for(int n =s1.length-1; n>4; n--){
				sb.append(" ");
				sb.append(s1[n]);
			}

			arrangedTypes.add(sb.toString());
		}
			// System.out.println(arrangedTypes);

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

	private boolean checkType(String instance, List<String> validTypes, VDMGeometry[] vdmGeometries)
	{
		System.out.println("Instance: "+instance+" validTypes: "+validTypes);
		boolean res = false;
		String[] instanceProps = instance.split("\\s+(?![^\\()]*\\))");
		String instanceName = instanceProps[0];
		String instanceType = instanceProps[1];
		for(int i = 0; i<validTypes.size(); i++){
			String[] typeProps = validTypes.get(i).split("\\s+(?![^\\()]*\\))");
			String typeName = typeProps[0];
			if(instanceType.equals(typeName)){
				res = true;
			}
			if(res){
				// Check number of arguments
				int nArgsProvided = instanceProps.length-2;
				int nArgsRequired = typeProps.length-1;
				if(nArgsProvided != nArgsRequired){
					System.out.println("Instance " + instanceName + " has " + nArgsProvided + 
									   " arguments. Expected " + nArgsRequired);
					res = false;
				}
			}
			if(res){
				// Check argument types
				for(int j=0; j<(typeProps.length-1); j++){
					String expectedType = typeProps[j+1].split(":")[1];
					String providedArg = instanceProps[j+2];
					if(expectedType.equals("rat")){
						try {
							double rat = Double.parseDouble(providedArg);
						} catch (NumberFormatException nfe) {
							System.out.println("Provided argument: "+ providedArg +" is not a rational number");
							res = false;
						}
					}
					if(!res){
						for(int k = 0; k < vdmGeometries.length; k++){
							if (vdmGeometries[k].getName().equals(providedArg)){
								// More to be handled here!
								res = true;
							}
						}
					}
				}
			}
		}
		return res;
	}


}