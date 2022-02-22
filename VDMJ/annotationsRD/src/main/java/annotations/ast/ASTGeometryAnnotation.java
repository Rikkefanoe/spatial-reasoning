package annotations.ast;

import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;


import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.syntax.DefinitionReader;


public class ASTGeometryAnnotation extends ASTAnnotation
{
	private static final long serialVersionUID = 1L;

	public ASTGeometryAnnotation(LexIdentifierToken name)
	{
		super(name);
	}

	@Override
	public void astBefore(DefinitionReader reader)
	{
		System.out.printf("astBefore ");
	}
}
