package dk.dtu.philipsclockradio;
import android.os.CountDownTimer;

public class StateSleep extends StateAdapter {

    int sleepClick;

    CountDownTimer hej;




    @Override
    public void onEnterState(final ContextClockradio context) {

        //checker antal click på sleep
        if (sleepClick < 0 || sleepClick > 6) {sleepClick = 1;}

        //tænder LED lys
        context.ui.turnOnLED(3);

        //Sætter timer igang
        hej = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                context.setState(new StateStandby(context.getTime()));
            }
        }.start();

        //clicker på knappen for at sætte det igang
        onClick_Sleep(context);

    }

    @Override
    public void onExitState(ContextClockradio context) {

        context.ui.turnOffLED(3);


    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {

        sleepClick++;

        hej.cancel();

        if (sleepClick == 7){
            sleepClick = 1;
        }

        context.ui.setDisplayText(switcing(sleepClick));


        hej.start();



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

