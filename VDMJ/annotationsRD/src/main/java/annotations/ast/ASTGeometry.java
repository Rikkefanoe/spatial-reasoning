package annotations.ast;

import com.fujitsu.vdmj.ast.annotations.ASTAnnotation;
import com.fujitsu.vdmj.ast.lex.LexIdentifierToken;

public class ASTGeometryAnnotation extends ASTAnnotation
{
	private static final long serialVersionUID = 1L;

	public ASTGeometryAnnotation(LexIdentifierToken name)
	{
		super(name);
	}
}
