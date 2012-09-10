package com.theladders.bankkata.exception;

public class InsufficientFundsException extends Exception
{
  private static final long serialVersionUID = -8448732609354289902L;


  public InsufficientFundsException(String string)
  {
    super(string);
  }
}
