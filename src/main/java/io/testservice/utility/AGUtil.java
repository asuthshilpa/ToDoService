package io.testservice.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class AGUtil {
	private List<Character> openList = new ArrayList<>(Arrays.asList('{','(','['));
	private List<Character> closeList = new ArrayList<>(Arrays.asList('}',')',']'));
	Stack<Character> stack = new Stack<>();
	public boolean isBalanced(String str)
	{
		
		char[] chararray = str.toCharArray();
		for (char ch : chararray)
		{
			if(openList.contains(ch))
			{
				stack.push(ch);
			}
			else if(closeList.contains(ch) && !stack.isEmpty())
			{
				char elem = stack.pop();
				if(openList.indexOf(elem)!=closeList.indexOf(ch))
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		
		if(!stack.isEmpty())
			return false;
		
		return true;		
		
	}
	
	
}


