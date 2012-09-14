package com.theladders.bankkata.model.transaction;

import java.util.Date;

import com.theladders.bankkata.model.Amount;

public class Withdrawal extends Transaction
{
  public Withdrawal(Amount amount,
                    Date date)
  {
    super(amount, date);
  }


  @Override
  void accept(TransactionVisitor visitor)
  {
    visitor.visit(this);
  }


  @Override
  public void applyTo(Amount amount)
  {
    amount.subtract(this.amount);
  }
}
