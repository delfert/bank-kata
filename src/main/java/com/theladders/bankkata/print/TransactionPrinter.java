package com.theladders.bankkata.print;

import java.math.BigDecimal;

import com.theladders.bankkata.model.Amount;
import com.theladders.bankkata.model.transaction.Deposit;
import com.theladders.bankkata.model.transaction.TransactionVisitor;
import com.theladders.bankkata.model.transaction.Withdrawal;

public abstract class TransactionPrinter implements TransactionVisitor
{
  protected Amount       balance      = new Amount(BigDecimal.ZERO);
  protected PrintHistory printHistory = new PrintHistory();


  @Override
  public void visit(Deposit deposit)
  {
    deposit.applyTo(balance);
    String output = createStatementLine(deposit);
    printAndRecord(output);
  }


  @Override
  public void visit(Withdrawal withdrawal)
  {
    withdrawal.applyTo(balance);
    String output = createStatementLine(withdrawal);
    printAndRecord(output);
  }


  public boolean hasPrinted(String string)
  {
    return printHistory.contains(string);
  }


  public boolean hasNotPrinted(String string)
  {
    return !hasPrinted(string);
  }


  abstract protected String createStatementLine(Deposit deposit);


  abstract protected String createStatementLine(Withdrawal withdrawal);


  private void printAndRecord(String output)
  {
    if (output != null)
    {
      System.out.println(output);
      printHistory.add(output);
    }
  }
}
