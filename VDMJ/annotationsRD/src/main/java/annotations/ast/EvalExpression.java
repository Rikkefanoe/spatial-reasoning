package annotations.ast;

public class EvalExpression{
	// inspired from : https://www.geeksforgeeks.org/evaluation-of-expression-tree/

Node root;
    
    
public static class Node
{
	String data;
	Node left, right;

	Node(String d)
	{
		data = d;
		left = null;
		right = null;
	}
}

// evaluates numeric expressions and returns a double
public static double evalTreeDouble(Node root)
{
	double retVal = 0;
	// Empty tree
	if (root == null)
		return retVal;

	// Leaf node i.e, an integer
	if (root.left == null && root.right == null)
		return Double.parseDouble(root.data);

	// Evaluate left subtree
	double leftEval = evalTreeDouble(root.left);

	// Evaluate right subtree
	double rightEval = evalTreeDouble(root.right);

	// Check which operator to apply
	if (root.data.equals("+"))
		return leftEval + rightEval;

	if (root.data.equals("-"))
		return leftEval - rightEval;

	if (root.data.equals("*"))
		return leftEval * rightEval;
  
  	if (root.data.equals("/"))
		return leftEval / rightEval;

	return retVal;
}

// evaluates boolean expressions
public static boolean evalTreeBool(Node root)
{
	// Empty tree
	if (root == null)
		return false;
	

	// Evaluate left subtree
	double leftEval = evalTreeDouble(root.left);

	// Evaluate right subtree
	double rightEval = evalTreeDouble(root.right);

	// Check which operator to apply
	if (root.data.equals("<"))
		return (leftEval < rightEval) ? true : false;

	if (root.data.equals(">"))
		return (leftEval > rightEval) ? true : false;

	if (root.data.equals("and"))
		return (leftEval == rightEval ) ? true : false;

	return false;
}

}

