/*******************************************************************************
 *
 *	Copyright (c) 2018 Nick Battle.
 *
 *	Author: Nick Battle
 *
 *	This file is part of VDMJ.
 *
 *	VDMJ is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	VDMJ is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with VDMJ.  If not, see <http://www.gnu.org/licenses/>.
 *	SPDX-License-Identifier: GPL-3.0-or-later
 *
 ******************************************************************************/

package annotations.tc;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.fujitsu.vdmj.ast.lex.LexStringToken;
import com.fujitsu.vdmj.tc.annotations.TCAnnotation;
import com.fujitsu.vdmj.tc.definitions.TCClassDefinition;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.expressions.TCExpression;
import com.fujitsu.vdmj.tc.expressions.TCExpressionList;
import com.fujitsu.vdmj.tc.expressions.TCStringLiteralExpression;
import com.fujitsu.vdmj.tc.lex.TCIdentifierToken;
import com.fujitsu.vdmj.tc.modules.TCModule;
import com.fujitsu.vdmj.tc.statements.TCStatement;
import com.fujitsu.vdmj.typechecker.Environment;
import com.fujitsu.vdmj.typechecker.NameScope;

public class TCSpatialAnnotation extends TCAnnotation
{
	private static final long serialVersionUID = 1L;

	public TCSpatialAnnotation (TCIdentifierToken name, TCExpressionList args)
	{
		super(name, args);
	}

	@Override
	public void tcBefore(TCDefinition def, Environment env, NameScope scope)
	{
		checkArgs1(env,scope,def);
		// name.report(6009, "@Spatial only applies to statements and expressions");
	}

	@Override
	public void tcBefore(TCModule module)
	{
		name.report(6009, "@Spatial only applies to statements and expressions");
	}

	@Override
	public void tcBefore(TCClassDefinition clazz)
	{
		name.report(6009, "@Spatial only applies to statements and expressions");
	}

	@Override
	public void tcBefore(TCExpression exp, Environment env, NameScope scope)
	{
		checkArgs(env, scope);
	}

	@Override
	public void tcBefore(TCStatement stmt, Environment env, NameScope scope)
	{
		checkArgs(env, scope);
	}

	private void checkArgs(Environment env, NameScope scope)
	{
		if (args.isEmpty())
		{
			name.report(6008, "@Spatial must start with a string argument");
		}
		else
		{
			if (args.get(0) instanceof TCStringLiteralExpression)
			{
				for (TCExpression arg: args)
				{
					arg.typeCheck(env, null, scope, null);	// Just checks scope
				}
				
				TCStringLiteralExpression str = (TCStringLiteralExpression)args.get(0);
				String format = str.value.value;
				
				try
				{
					// Try to format with string arguments to check they are all %s (up to 20)
					Object[] args = new String[20];
					Arrays.fill(args, "A string");
					String.format(format, args);
				}
				catch (IllegalArgumentException e)
				{
					name.report(6008, "@Spatial must use %[arg$][width]s conversions");
				}
			}
			else
			{
				name.report(6008, "@Spatial must start with a string argument");
			}
		}
	}

	private void checkArgs1(Environment env, NameScope scope, TCDefinition def)
	{
		if (!args.isEmpty())
		{
			name.report(6008, "@Spatial must start with a string argument");
		}
		else
		{
				String[] definition = def.toString().split(" ");
				List<String> objParts = new ArrayList<>();
				for(int i =7; i<definition.length-1; i++){ // don't care about 'end'
							objParts.add(definition[i]);
				}
				StringBuilder strb = new StringBuilder(); 
				strb.append(def.name.toString());
				strb.append(" :: ");

				for(String i : objParts){
					strb.append(i);
					strb.append(" ");
				}
				String stri=strb.toString();
				// System.out.printf(stri); // format = circle :: center:point2D, radius:nat1
				
			
		}
	}
}
