package com.example.dr;

public class MyRegistration {
    String names;
    //String regiStrationId;
    String mobile_Number;
    String event_name;

    public MyRegistration(){

    }


    public MyRegistration(String event_name, String mobile_Number, String names) {
        // this.regiStrationId = regiStrationId;
        this.mobile_Number = mobile_Number;
        this.names = names;
        this.event_name = event_name;
    }

    public String getNames(){
        return names;
    }

    public String getMobile_Number() {
        return mobile_Number;
    }

    public String getEvent_name() {
        return event_name;
    }

}
