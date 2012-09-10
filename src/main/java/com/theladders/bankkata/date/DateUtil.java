package com.theladders.bankkata.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("M-d-yy 'at' h:mm:ss a");


  public static String formatDate(Date date)
  {
    return dateFormat.format(date);
  }
}
