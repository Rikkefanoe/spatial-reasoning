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

public class INRelationAnnotation extends INAnnotation
{
	private static final long serialVersionUID = 1L;


	public INRelationAnnotation(TCIdentifierToken name, INExpressionList args)
	{
		super(name, args);
	}

	@Override
	public void inBefore(INStatement stmt, Context ctxt)
	{
		doRelation(ctxt);
	}

	private void doRelation(Context ctxt)
	{
		// Object[] values = new Value[args.size() - 1];
		
		// for (int p=1; p < args.size(); p++)
		// {
		// 	values[p-1] = args.get(p).eval(ctxt);
		// }
		
		// INStringLiteralExpression fmt = (INStringLiteralExpression)args.get(0);
		// Console.out.printf(fmt.value.value, values);

	}

}
