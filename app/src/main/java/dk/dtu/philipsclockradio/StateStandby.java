package dk.dtu.philipsclockradio;

import android.content.Context;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;

    StateStandby(Date time){
        mTime = time;
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            try {
                long currentTime = mTime.getTime();
                mTime.setTime(currentTime + 60000);
                mContext.setTime(mTime);

            } finally {
                mHandler.postDelayed(mSetTime, 60000);

                if (mContext.getAlarm() != null) {
                    if (mContext.isAlarmActive() && mContext.getTime().getHours() == mContext.getAlarm().getHours() && mContext.getTime().getMinutes() == mContext.getAlarm().getMinutes()) {
                        AlarmRinging(mContext);
                        mContext.setAlarm(null);
                    }
                }
                if (mContext.isSnooze()) {
                    mContext.setSnoozelaps(mContext.getSnoozelaps() + 1);

                    if (mContext.getSnoozelaps() == 9) {
                        AlarmRinging(mContext);
                    }
                }
            }
        }
    };

    void startClock() {
        mContext.isClockRunning = true;
        mSetTime.run();
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());

    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        context.setState(new StateRadio());

    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleep());
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.setState(new StateAlarm());

    }

    @Override
    public void onClick_AL2(ContextClockradio context) {

        context.setAlarmActive(!context.isAlarmActive());
        if (context.isAlarmActive()){
            context.ui.turnOnLED(5);
            System.out.println("activ alarm");
        } else {
            context.ui.turnOffLED(5);
            System.out.println("inactiv alarm");
        }

    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {


    }

    public void AlarmRinging(ContextClockradio context){

        context.setState(new StateAlarmRinging());


    }


}
