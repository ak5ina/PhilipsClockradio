package dk.dtu.philipsclockradio;
import java.util.Timer;

public class StateSleep extends StateAdapter {

    int sleepClick;
    Timer t = new java.util.Timer();




    @Override
    public void onEnterState(final ContextClockradio context) {

        if (sleepClick < 0 || sleepClick > 6) {sleepClick = 1;}

        onClick_Sleep(context);

        t.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        context.setState(new StateStandby(context.getTime()));
                        //
                        t.cancel();
                    }
                },
                7500
        );


    }

    @Override
    public void onExitState(ContextClockradio context) {


    }

    @Override
    public void onClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onClick_Min(ContextClockradio context) {

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onClick_Power(ContextClockradio context) {

    }


    @Override
    public void onClick_Sleep(final ContextClockradio context) {

        sleepClick++;





        if (sleepClick == 7){
            sleepClick = 1;
        }

        context.ui.setDisplayText(switcing(sleepClick));

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

    }

    @Override
    public void onClick_AL2(ContextClockradio context) {

    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Sleep(ContextClockradio context) {

    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {

    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {

    }

    @Override
    public void onLongClick_Snooze(ContextClockradio context) {

    }

    public String switcing(int nr) {

        String nrString;

        switch (nr){

            case 1:
                nrString = "120";
                break;
            case 2:
                nrString = "90";
                break;
            case 3:
                nrString = "60";
                break;
            case 4:
                nrString = "30";
                break;
            case 5:
                nrString = "15";
                break;
            case 6:
                nrString = "OFF";
                break;
            default:
                nrString = "Error;";
        }

        return nrString;
    }

}

