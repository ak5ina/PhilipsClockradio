package dk.dtu.philipsclockradio;

import android.content.Context;

public class StateSave extends StateAdapter{
    @Override
    public void onEnterState(ContextClockradio context) {

        context.ui.turnOnTextBlink();
        StateRadio.gemteAmKanaler[0] = 2;

        //Hvis du gemmer i AM
        if (StateRadio.playingFM) {
            context.ui.setDisplayText("FM");
        } else {
            context.ui.setDisplayText("AM");
        }


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
    public void onClick_Sleep(ContextClockradio context) {

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
}
