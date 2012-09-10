package com.theladders.bankkata.model.transaction;

import java.util.ArrayList;

public class Transactions extends ArrayList<Transaction>
{
  private static final long serialVersionUID = 2751005588944472121L;


  public void accept(TransactionVisitor visitor)
  {
    for (Transaction transaction : this)
    {
      transaction.accept(visitor);
    }
  }
}
