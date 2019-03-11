package io.testservice.model;

public class BalanceTestResult {
	
	String input;
	boolean isBalanced;
	public BalanceTestResult(String input, boolean balanced)
	{
		this.input = input;
		this.isBalanced = balanced;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public boolean isBalanced() {
		return isBalanced;
	}
	public void setBalanced(boolean isBalanced) {
		this.isBalanced = isBalanced;
	}

}
