package dk.dtu.philipsclockradio;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StateAlarm extends StateAdapter{

    Date nyTime = new Date();
    Date saveAlarm;


    StateAlarm(){}

    @Override
    public void onEnterState(ContextClockradio context) {
        nyTime = (Date) context.getTime().clone();

        context.ui.turnOnTextBlink();
        context.updateDisplayTime();

    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        //Gets current timestamp (Date)
        nyTime.setTime(nyTime.getTime() + 3600000);


        DateFormat dateFormat = new SimpleDateFormat("hh:mm");

        String dateString = dateFormat.format(nyTime);

        context.ui.setDisplayText(dateString);


    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        //Gets current timestamp (Date)
        nyTime.setTime(nyTime.getTime() + 60000);
        context.ui.setDisplayText(String.valueOf(nyTime.getTime()));
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        saveAlarm = nyTime;
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        saveAlarm = nyTime;
        context.setState(new StateStandby(context.getTime()));
    }




}
