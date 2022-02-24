package annotations.ast;

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
		System.out.println("ast vdmsp");

	}

	@Override
	public void astAfter(ClassReader reader, ASTClassDefinition clazz)
	{
		// Nothing by default
		System.out.println("ast after");
		System.out.println(clazz.toString());
		

	}
	// public void astBefore(DefinitionReader reader)
	// {
	// 	System.out.printf("astBefore ");
	// }
}