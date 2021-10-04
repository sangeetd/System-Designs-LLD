package com.sangeetdas.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getCurrentDate(){
        Date currDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(currDate).toString();
    }

}
