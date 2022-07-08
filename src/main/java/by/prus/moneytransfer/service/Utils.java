package by.prus.moneytransfer.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean isPhone(String line){
        try{
            if (line.length()>=11&& line.length()<=13){
                Long phoneNo = Long.parseLong(line);
                return  true;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return false;
    }

    public static boolean isEmail(String line){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(line);
        return matcher.find();
    }

    public static boolean isDate(String line){
        try{
            getLocalDate(line);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    public static LocalDate getLocalDate (String dateLine){
        return LocalDate.parse(dateLine, formatter);
    }


}
