package com.theladders.bankkata.print;

import com.theladders.bankkata.model.transaction.Withdrawal;

public class DepositsPrinter extends AllTransactionsPrinter
{

  @Override
  protected String createStatementLine(Withdrawal withdrawal)
  {
    return null;
  }

}
