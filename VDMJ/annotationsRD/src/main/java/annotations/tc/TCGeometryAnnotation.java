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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

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
import com.fujitsu.vdmj.messages.Console;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.values.Value;



public class TCGeometryAnnotation extends TCAnnotation
{
	private static final long serialVersionUID = 1L;

	public static final List<TCDefinition> geometryInstances = new Vector<TCDefinition>();
	
	public TCGeometryAnnotation (TCIdentifierToken name, TCExpressionList args)
	{
		super(name, args);
	}

	@Override
	public void tcBefore(TCDefinition def, Environment env, NameScope scope)
	{
		checkArgs(env, scope,def);
		Console.out.println(def.toString()); // prints "static private circle = compose circle of center:point2D, radius:nat1 end"
		Console.out.println(def.name.toString()); // prints "circle"
		Console.out.println(def.nameScope.toString()); // prints "TYPENAME"
		Console.out.println(def.location.toString()); // prints "in 'spatial_demo1' (.\spatial_demo1.vdmpp) at line 5:1"
		Console.out.println("comments" + def.comments.toString()); // [/*@Geometry() */]
		Console.out.println("annotations" + def.annotations.toString()); //[@Geometry]
		Console.out.println("getDefinitions" + def.getDefinitions().toString()); //static private point2D = compose point2D of x:rat, y:rat end
		Console.out.println("getCallMap" + def.getCallMap().toString()); 
		Console.out.println("getFreeVariables" + def.getFreeVariables().toString()); 
		Console.out.println("getVariableNames" + def.getVariableNames().toString()); //point2D
		Console.out.println("getType" + def.getType().toString()); // point2D

	}


	private void checkArgs(Environment env, NameScope scope, TCDefinition def)
	{
		if (!args.isEmpty())
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
					name.report(6008, "@Printf must use %[arg$][width]s conversions");
				}
			}
			else
			{
				name.report(6008, "@Printf must start with a string argument");
			}
		}
		else
		{
			// 		name.report(0,geometryInstances.get(0).toString());
			// doClose();
			Console.out.println(def.toString());

			printGeo(def);			
		}
	}

	private void printGeo(TCDefinition def)
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
		Console.out.println(stri);

	}
}