package com.theladders.bankkata.print;

import com.theladders.bankkata.model.transaction.Deposit;
import com.theladders.bankkata.model.transaction.Withdrawal;

public class AllTransactionsPrinter extends TransactionPrinter
{
  
  @Override
  protected String createStatementLine(Deposit deposit)
  {
    return "Deposit: " + deposit.toString() + " [" + balance + "]";
  }


  @Override
  protected String createStatementLine(Withdrawal withdrawal)
  {
    return "Withdrawal: " + withdrawal.toString() + " [" + balance + "]";
  }

}
