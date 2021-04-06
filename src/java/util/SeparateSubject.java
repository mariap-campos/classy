/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Maria Paula
 */
public class SeparateSubject {
    public static String[] splitSubjects(String materias) {
        
        String[] subjectsArray = materias.split(";");

    return subjectsArray;
        
    } 
}
