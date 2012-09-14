package com.theladders.bankkata;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import com.theladders.bankkata.date.DateUtil;
import com.theladders.bankkata.exception.InsufficientFundsException;
import com.theladders.bankkata.model.Account;
import com.theladders.bankkata.model.Amount;
import com.theladders.bankkata.print.AllTransactionsPrinter;
import com.theladders.bankkata.print.DepositsPrinter;
import com.theladders.bankkata.print.TransactionPrinter;
import com.theladders.bankkata.print.TransactionTimesPrinter;
import com.theladders.bankkata.print.WithdrawalsPrinter;

public class BankKataTest
{
  @Before
  public void delineateOutput()
  {
    System.out.println();
  }


  @Test
  public void bankShouldAcceptDeposits()
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));

    assertTrue(account.balanceEquals(new Amount("100.23")));
  }


  @Test
  public void bankShouldAcceptDepositsForDifferentCustomers()
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));

    assertTrue(account.balanceEquals(new Amount("100.23")));

    Account otherAccount = new Account();
    otherAccount.deposit(new Amount("30.23"));

    assertTrue(otherAccount.balanceEquals(new Amount("30.23")));
  }


  @Test
  public void bankShouldAllowMultipleDeposits()
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));
    account.deposit(new Amount("54.01"));

    assertTrue(account.balanceEquals(new Amount("154.24")));
  }


  @Test
  public void bankShouldAcceptWithdrawalsForDifferentCustomers() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));
    account.withdraw(new Amount("100.23"));

    assertTrue(account.balanceEquals(new Amount("0")));

    Account otherAccount = new Account();
    otherAccount.deposit(new Amount("30.23"));
    otherAccount.withdraw(new Amount("1"));

    assertTrue(otherAccount.balanceEquals(new Amount("29.23")));
  }


  @Test(expected = InsufficientFundsException.class)
  public void bankShouldRejectWithdrawalsWithoutEnoughFunds() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));
    account.withdraw(new Amount("100.24"));
  }


  @Test
  public void bankShouldAllowTransfersBetweenCustomers() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));

    Account otherAccount = new Account();

    account.transferTo(otherAccount, new Amount("100.23"));

    assertTrue(account.balanceEquals(new Amount("0")));
    assertTrue(otherAccount.balanceEquals(new Amount("100.23")));
  }


  @Test(expected = InsufficientFundsException.class)
  public void bankShouldRejectTransfersWithoutEnoughFunds() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));

    Account otherAccount = new Account();

    account.transferTo(otherAccount, new Amount("100.24"));
  }


  @Test
  public void bankShouldPrintTransactionsWithAmountAndBalance() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));
    account.withdraw(new Amount("60"));
    account.deposit(new Amount("54.01"));

    TransactionPrinter printer = new AllTransactionsPrinter();
    account.printStatementUsing(printer);

    assertTrue(printer.hasPrinted("100.23"));
    assertTrue(printer.hasPrinted("60"));
    assertTrue(printer.hasPrinted("40.23"));
    assertTrue(printer.hasPrinted("54.01"));
    assertTrue(printer.hasPrinted("94.24"));
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyDeposits() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));
    account.withdraw(new Amount("60"));
    account.deposit(new Amount("54.01"));

    TransactionPrinter printer = new DepositsPrinter();
    account.printStatementUsing(printer);

    assertTrue(printer.hasPrinted("100.23"));
    assertTrue(printer.hasNotPrinted("60"));
    assertTrue(printer.hasPrinted("54.01"));
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyWithdrawals() throws InsufficientFundsException
  {
    Account account = new Account();
    account.deposit(new Amount("100.23"));
    account.withdraw(new Amount("60"));
    account.deposit(new Amount("54.01"));

    TransactionPrinter printer = new WithdrawalsPrinter();
    account.printStatementUsing(printer);

    assertTrue(printer.hasNotPrinted("100.23"));
    assertTrue(printer.hasPrinted("60"));
    assertTrue(printer.hasNotPrinted("54.01"));
  }


  @Test
  public void bankShouldBeAbleToPrintOnlyTransactionTimes() throws InsufficientFundsException
  {
    Date then = new GregorianCalendar(2000, 4, 18).getTime();
    Date now = new Date();

    Account account = new Account();
    account.deposit(new Amount("100.23"), then);
    account.withdraw(new Amount("60"), now);

    TransactionPrinter printer = new TransactionTimesPrinter();
    account.printStatementUsing(printer);

    assertTrue(printer.hasNotPrinted("100.23"));
    assertTrue(printer.hasNotPrinted("60"));

    assertTrue(printer.hasPrinted(DateUtil.formatDate(then)));
    assertTrue(printer.hasPrinted(DateUtil.formatDate(now)));
  }
}
