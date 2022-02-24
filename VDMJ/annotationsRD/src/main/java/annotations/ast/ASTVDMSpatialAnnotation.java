package annotations.ast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;


import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
// import com.fujitsu.vdmj.syntax.DefinitionReader;
import com.fujitsu.vdmj.syntax.ClassReader;
import com.fujitsu.vdmj.ast.definitions.ASTClassDefinition;



public class ASTVDMSpatialAnnotation extends ASTAnnotation
{

	private static final long serialVersionUID = 1L;

	public ASTVDMSpatialAnnotation(LexIdentifierToken name)
	{
		super(name);
		// System.out.println("ast vdmsp");


	}

	@Override
	public void astAfter(ClassReader reader, ASTClassDefinition clazz)
	{

		// System.out.println("ast after");
		// System.out.println("\n"+clazz.toString());

		for(int i=0; i<clazz.definitions.size(); i++){
			if(clazz.definitions.get(i).kind().toString() == "type"){
				System.out.println("");
				System.out.println("Geometry : ");
				System.out.println(clazz.definitions.get(i).toString());
				System.out.println("");

			}
			if(clazz.definitions.get(i).kind().toString() == "explicit operation"){
				System.out.println("");
				System.out.println("Relation : ");
				System.out.println(clazz.definitions.get(i).toString());
				System.out.println("");
			}
		}


	}
	// public void astBefore(DefinitionReader reader)
	// {
	// 	System.out.printf("astBefore ");
	// }
}