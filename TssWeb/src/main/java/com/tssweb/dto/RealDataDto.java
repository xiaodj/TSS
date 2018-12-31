package com.tssweb.dto;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RealDataDto implements Comparable<RealDataDto>{
    private String username;
    private String intime;
    private String lc1;
    private String lc2;
    private String lc3;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getLc1() {
        return lc1;
    }

    public void setLc1(String lc1) {
        this.lc1 = lc1;
    }

    public String getLc2() {
        return lc2;
    }

    public void setLc2(String lc2) {
        this.lc2 = lc2;
    }

    public String getLc3() {
        return lc3;
    }

    public void setLc3(String lc3) {
        this.lc3 = lc3;
    }

    @Override
    public int compareTo(RealDataDto o) {
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        try {
            Date date1 = timeFormat.parse(this.intime);
            Date date2 = timeFormat.parse(o.intime);
            if (date1.getTime() >= date2.getTime())
                return 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
