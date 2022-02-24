package annotations.tc;

import com.fujitsu.vdmj.tc.annotations.TCAnnotation;
import com.fujitsu.vdmj.tc.definitions.TCClassDefinition;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.expressions.TCExpression;
import com.fujitsu.vdmj.tc.expressions.TCExpressionList;
import com.fujitsu.vdmj.tc.expressions.TCVariableExpression;
import com.fujitsu.vdmj.tc.lex.TCIdentifierToken;
import com.fujitsu.vdmj.tc.modules.TCModule;
import com.fujitsu.vdmj.tc.statements.TCStatement;
import com.fujitsu.vdmj.typechecker.Environment;
import com.fujitsu.vdmj.typechecker.NameScope;

public class TCDemoAnnotation extends TCAnnotation
{
	private static final long serialVersionUID = 1L;

	public TCDemoAnnotation(TCIdentifierToken name, TCExpressionList args)
	{
		super(name, args);
	}

	@Override
	public void tcBefore(TCDefinition def, Environment env, NameScope scope)
	{
		name.report(6006, "Rikke");
	}

	@Override
	public void tcBefore(TCModule module)
	{
		name.report(6006, "Rikke module");
	}

	@Override
	public void tcBefore(TCClassDefinition clazz)
	{
		name.report(6006, "Rikke class");
	}

	@Override
	public void tcBefore(TCExpression exp, Environment env, NameScope scope)
	{
		check(env, scope);
	}

	@Override
	public void tcBefore(TCStatement stmt, Environment env, NameScope scope)
	{
		check(env, scope);
	}
	
	private void check(Environment env, NameScope scope)
	{
		for (TCExpression arg: args)
		{
			if (!(arg instanceof TCVariableExpression))
			{
				arg.report(6007, "Rikke - identifier");
			}
			else
			{
				arg.typeCheck(env, null, scope, null);	// Just checks scope
			}
		}
	}
}
