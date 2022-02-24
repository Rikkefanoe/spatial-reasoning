package annotations.ast;

import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;


import com.fujitsu.vdmj.ast.definitions.ASTDefinition;
import com.fujitsu.vdmj.syntax.DefinitionReader;


public class ASTRelationAnnotation extends ASTAnnotation
{
	private static final long serialVersionUID = 1L;

	public ASTRelationAnnotation(LexIdentifierToken name)
	{
		super(name);
	}


}
