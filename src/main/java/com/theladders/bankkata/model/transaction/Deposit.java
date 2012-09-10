package com.theladders.bankkata.model.transaction;

import java.util.Date;

import com.theladders.bankkata.model.Amount;

public class Deposit extends Transaction
{
  public Deposit(Amount amount,
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
  public void applyTo(@SuppressWarnings("hiding") Amount amount)
  {
    amount.add(this.amount);
  }
}
