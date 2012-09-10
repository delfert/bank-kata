package com.theladders.bankkata.print;

import com.theladders.bankkata.model.transaction.Deposit;

public class WithdrawalsPrinter extends AllTransactionsPrinter
{

  @Override
  protected String createStatementLine(Deposit deposit)
  {
    return null;
  }

}
