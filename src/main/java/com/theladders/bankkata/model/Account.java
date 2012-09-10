package com.theladders.bankkata.model;

import java.math.BigDecimal;
import java.util.Date;

import com.theladders.bankkata.exception.InsufficientFundsException;
import com.theladders.bankkata.model.transaction.Deposit;
import com.theladders.bankkata.model.transaction.TransactionVisitor;
import com.theladders.bankkata.model.transaction.Transactions;
import com.theladders.bankkata.model.transaction.Withdrawal;

public class Account
{
  private Amount       balance      = new Amount(BigDecimal.ZERO);
  private Transactions transactions = new Transactions();


  public boolean balanceEquals(Amount amount)
  {
    return balance.equals(amount);
  }


  public void deposit(Amount amount)
  {
    deposit(amount, new Date());
  }


  public void deposit(Amount amount, Date date)
  {
    balance.add(amount);
    transactions.add(new Deposit(amount, date));
  }


  public void withdraw(Amount amount) throws InsufficientFundsException
  {
    withdraw(amount, new Date());
  }
  
  
  public void withdraw(Amount amount, Date date) throws InsufficientFundsException
  {
    if (balance.lessThan(amount))
    {
      throw new InsufficientFundsException("cannot withdraw more than account balance");
    }

    balance.subtract(amount);
    transactions.add(new Withdrawal(amount, date));
  }


  public void transferTo(Account account,
                         Amount amount) throws InsufficientFundsException
  {
    withdraw(amount);
    account.deposit(amount);
  }


  public void visitTransactions(TransactionVisitor visitor)
  {
    transactions.accept(visitor);
  }
}
