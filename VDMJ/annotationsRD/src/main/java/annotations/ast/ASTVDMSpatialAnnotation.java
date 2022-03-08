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

		boolean status = true;
		status = (clazz.name.name.equals("VDMGeometry"));
		if(!status){
			System.out.println("VDMGeometry class not defined! Found: " + clazz.name.name);
		}else{
			for(int i = 0; i<clazz.definitions.size(); i++){
				String defName = clazz.definitions.get(i).name.name;
				if(!(defName.equals("Point2D") || defName.equals("Circle"))){
					System.out.println("Unsupported type: " + defName);
					status = false;
				}
			}
			if(!status){
				System.out.println("Supported types are: Point2D, Circle");
			}
		}


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
		// System.out.println("geometryTypes");
		// System.out.println(geometryTypes);
		// System.out.println("geometryRelations");
		// System.out.println(geometryRelations);
		// System.out.println("geometryInstances");
		// System.out.println(geometryInstances);

		List<String> arrangedTypes = arrangeTypes(geometryTypes);
		System.out.println(arrangedTypes);

		int nInstances = geometryInstances.size();
		VDMGeometry[] vdmGeometries = new VDMGeometry[nInstances];
		
		for(int i = 0; i<nInstances; i++){
		// p1:(unresolved point2D) := mk_point2D(2, 2)
		// c1:(unresolved circle) := mk_circle(mk_point2D(2, 2), 1)
			String [] st = geometryInstances.get(i).toString().trim().split(":"); 
			int nAttr = st.length-2; 
			vdmGeometries[i] = new VDMGeometry();
			vdmGeometries[i].setName(st[0]);
			vdmGeometries[i].setType(st[1]);
			// System.out.println(nInstances + " " + nAttr); 
			RealValue[] val = new RealValue[nAttr]; // so far only 1 as string
			val[0]= new RealValue();
			val[0].s = st[2].substring(st[2].lastIndexOf("=") + 1);
			vdmGeometries[i].addAttribute("value", val[0]);

		}

		for(int i = 0; i<nInstances; i++){
			System.out.println(vdmGeometries[i].toString());
			}

		// get all geometry instances
			// sort instance into
				// name
				// type
				// value
			//type check instances


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


}