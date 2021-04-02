/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Maria Paula
 */
public class ConverteDate {
    public static Date getDate(String data) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = df.parse(data);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());           

    return sqlDate;
        
    } 
}
