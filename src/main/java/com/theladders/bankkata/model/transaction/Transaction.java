package com.theladders.bankkata.model.transaction;

import java.util.Date;

import com.theladders.bankkata.date.DateUtil;
import com.theladders.bankkata.model.Amount;

public abstract class Transaction
{
  protected Amount amount;
  protected Date   date;


  protected Transaction(Amount amount,
                        Date date)
  {
    this.amount = amount;
    this.date = date;
  }


  abstract void accept(TransactionVisitor visitor);


  abstract public void applyTo(@SuppressWarnings("hiding") Amount amount);


  @Override
  public String toString()
  {
    return amount + " on " + printableDate();
  }


  public String printableDate()
  {
    return DateUtil.formatDate(date);
  }
}
