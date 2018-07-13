/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class getDate {

    Date date = new Date();

    public String getCurrentDate() throws ParseException {
        DateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        return ft.format(date);
    }

    public Date convertDate(String input) throws ParseException {
        DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.parse(input);
    }

    public String convertString(Date input) {
        DateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        return ft.format(input);
    }
}
