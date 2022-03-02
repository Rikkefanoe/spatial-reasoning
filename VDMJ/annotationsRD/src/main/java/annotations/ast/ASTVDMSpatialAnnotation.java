package annotations.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;


import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
// import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ClassReader;
import com.fujitsu.vdmj.ast.definitions.ASTClassDefinition;



public class ASTVDMSpatialAnnotation extends ASTAnnotation
{
	private testclass t = new testclass();
	private static final long serialVersionUID = 1L;

	public ASTVDMSpatialAnnotation(LexIdentifierToken name)
	{
		super(name);

	}

	@Override
	public void astAfter(ClassReader reader, ASTClassDefinition clazz)
	{
		// Nothing by default
		// System.out.println("\n"+clazz.toString());
		// for(int i=0; i<clazz.definitions.size(); i++){
		// 	System.out.println(clazz.definitions.get(i).kind().toString());
		// 	}



		/******************* HARD CODED EXAMPLE ********************/
		// VDMGeometry p1 = new VDMGeometry();

		// p1.setName("p1"); 
		// p1.setType("point2d");
		// RealValue p1x = new RealValue();
		// p1x.v = 0;
		// RealValue p1y = new RealValue();
		// p1y.v = 0;

		// p1.addAttribute("x", p1x);

		// p1.addAttribute("y", p1y);

		// System.out.println(p1.toString());


		/******************* READ FROM VDM FILE SIMPLE ********************/
		
		// read in file VDM
		// tokenize file input
		// use input to test


		List<String> geometries = new Vector<String>();
		for(int i=0; i<clazz.definitions.size(); i++){
			if(clazz.definitions.get(i).kind().toString() == "instance variable"){
				// G1.addAttribute(clazz.definitions.get(i).toString(),v1);
				geometries.add(clazz.definitions.get(i).toString());
			}
		}

//p1:(unresolved point2D) := mk_point2D(2, 2) - 0.0
		for(int i=0; i<geometries.size(); i++){
			VDMGeometry G1 = new VDMGeometry();

			String [] st = geometries.get(i).split(":"); // name, type, =  value
			String [] st1 = st[2].split("="); // name, type, =, value

			G1.setName(st[0].trim()); 
			G1.setType(st[1].trim());
			RealValue v1 = new RealValue();
			v1.s = st1[1].trim();
			
			// System.out.println(G1.getName() + G1.getType() + v1.s);

			G1.addAttribute("value", v1);	

			System.out.println(G1.toString());

		}


		// name , type, attr1, attr2 






		// for(int i=0; i<clazz.definitions.size(); i++){
		// 	if(clazz.definitions.get(i).kind().toString() == "type"){
		// 		System.out.println("");
		// 		System.out.println("Geometry : ");
		// 		System.out.println(clazz.definitions.get(i).toString());
		// 		System.out.println("");

		// 	}
		// 	if(clazz.definitions.get(i).kind().toString() == "explicit operation"){
		// 		System.out.println("");
		// 		System.out.println("Relation : ");
		// 		System.out.println(clazz.definitions.get(i).toString());
		// 		System.out.println("");
		// 	}
		// }

	}
	
}