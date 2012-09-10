package com.theladders.bankkata.print;

public class PrintHistory
{
  private StringBuffer stringBuffer = new StringBuffer();


  public void add(String string)
  {
    stringBuffer.append(string);
  }


  public boolean contains(String string)
  {
    return toString().contains(string);
  }


  @Override
  public String toString()
  {
    return stringBuffer.toString();
  }
}
