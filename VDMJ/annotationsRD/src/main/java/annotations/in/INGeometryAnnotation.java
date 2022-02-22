package annotations.in;

import java.util.List;
import java.util.Vector;

import com.fujitsu.vdmj.in.annotations.INAnnotation;
import com.fujitsu.vdmj.in.expressions.INExpression;
import com.fujitsu.vdmj.in.expressions.INExpressionList;
import com.fujitsu.vdmj.in.expressions.INStringLiteralExpression;
import com.fujitsu.vdmj.in.statements.INStatement;
import com.fujitsu.vdmj.messages.Console;
import com.fujitsu.vdmj.runtime.Context;
import com.fujitsu.vdmj.tc.lex.TCIdentifierToken;
import com.fujitsu.vdmj.values.Value;

public class INGeometryAnnotation extends INAnnotation
{
	private static final long serialVersionUID = 1L;


	public INGeometryAnnotation(TCIdentifierToken name, INExpressionList args)
	{
		super(name, args);
	}

	@Override
	public void inBefore(INStatement stmt, Context ctxt)
	{
		doGeometry(ctxt);
	}

	private void doGeometry(Context ctxt)
	{


	}
}
