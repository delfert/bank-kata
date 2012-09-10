package com.theladders.bankkata.model.transaction;

public interface TransactionVisitor
{
  void visit(Deposit deposit);
  void visit(Withdrawal withdrawal);
}
