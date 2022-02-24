package annotations.ast;

import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;

public class ASTSpatialAnnotation extends ASTAnnotation
{
	private static final long serialVersionUID = 1L;

	public ASTSpatialAnnotation(LexIdentifierToken name)
	{
		super(name);
	}
}
