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

public class TCVDMSpatialAnnotation extends TCAnnotation
{
	private static final long serialVersionUID = 1L;

	public TCVDMSpatialAnnotation (TCIdentifierToken name, TCExpressionList args)
	{
		super(name, args);
	}

	@Override
	public void tcBefore(TCDefinition def, Environment env, NameScope scope)
	{
		//checkArgs1(env,scope,def);
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
		//checkArgs(env, scope);
	}

	@Override
	public void tcBefore(TCStatement stmt, Environment env, NameScope scope)
	{
		//checkArgs(env, scope);
	}

	@Override
	public void tcAfter(TCClassDefinition m)
	{
		System.out.println("constructing");
	}

	// private void checkArgs(Environment env, NameScope scope)
	// {

	// }
}