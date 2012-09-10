package com.theladders.bankkata.print;

import com.theladders.bankkata.model.transaction.Deposit;
import com.theladders.bankkata.model.transaction.Withdrawal;

public class TransactionTimesPrinter extends TransactionPrinter
{

  @Override
  protected String createStatementLine(Deposit deposit)
  {
    return deposit.printableDate();    
  }

  @Override
  protected String createStatementLine(Withdrawal withdrawal)
  {
    return withdrawal.printableDate();    
  }

}
