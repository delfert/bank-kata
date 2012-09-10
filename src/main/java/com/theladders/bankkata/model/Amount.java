package com.theladders.bankkata.model;

import java.math.BigDecimal;

public class Amount
{
  private BigDecimal value;


  public Amount(String value)
  {
    this(new BigDecimal(value));
  }


  public Amount(BigDecimal value)
  {
    this.value = value;
    validateValue();
  }


  private void validateValue()
  {
    if (this.value.compareTo(BigDecimal.ZERO) < 0)
    {
      throw new IllegalArgumentException("value cannot be negative");
    }
  }


  public void add(Amount amount)
  {
    value = value.add(amount.value);
  }


  public void subtract(Amount amount)
  {
    value = value.subtract(amount.value);
  }


  public boolean lessThan(Amount amount)
  {
    return value.compareTo(amount.value) < 0;
  }


  public boolean equals(Amount amount)
  {
    return value.compareTo(amount.value) == 0;
  }


  @Override
  public String toString()
  {
    return "$" + value.toString();
  }
}
