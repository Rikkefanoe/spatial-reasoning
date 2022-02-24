package annotations.in;

import com.fujitsu.vdmj.in.annotations.INAnnotation;
import com.fujitsu.vdmj.in.expressions.INExpression;
import com.fujitsu.vdmj.in.expressions.INExpressionList;
import com.fujitsu.vdmj.in.statements.INStatement;
import com.fujitsu.vdmj.messages.Console;
import com.fujitsu.vdmj.runtime.Context;
import com.fujitsu.vdmj.tc.lex.TCIdentifierToken;
import com.fujitsu.vdmj.values.Value;

public class INDemoAnnotation extends INAnnotation
{
	private static final long serialVersionUID = 1L;

	public INDemoAnnotation(TCIdentifierToken name, INExpressionList args)
	{
		super(name, args);
	}
	
	@Override
	public void inBefore(INStatement stmt, Context ctxt)
	{
		doDemo(ctxt);
	}
	
	@Override
	public void inBefore(INExpression exp, Context ctxt)
	{
		doDemo(ctxt);
	}
	
	private void doDemo(Context ctxt)
	{
		if (args.isEmpty())
		{
			System.err.println("Demo -> Rikke : " + name.getLocation());
		}
		else
		{
			for (INExpression arg: args)
			{
				Value v = arg.eval(ctxt);
				Console.err.println("Demo -> Rikke : " + name.getLocation() + ", " + arg + " = " + v);
			}
		}
	}
}
