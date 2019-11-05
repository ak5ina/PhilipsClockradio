package dk.dtu.philipsclockradio;

import android.content.Context;

public class StateSave extends StateAdapter{

    int kanalNummer = 0;

    @Override
    public void onEnterState(ContextClockradio context) {

        context.ui.turnOnTextBlink();

        //Hvis du gemmer i AM
        if (StateRadio.playingFM) {
            context.ui.setDisplayText("FM 1");
        } else {
            context.ui.setDisplayText("AM 1");
        }


    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();

    }

    @Override
    public void onClick_Hour(ContextClockradio context) {

    }

    @Override
    public void onClick_Min(ContextClockradio context) {

    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        // confirmer den kanal man vil gemme på
        int[] listen;

        //Hvilken liste man skal gemme på
        if (StateRadio.playingFM) {
            //Hvis det er FM
            listen = StateRadio.getGemteFmKanaler();
            listen[kanalNummer] = StateRadio.getFmTuneValue();

        } else {
            listen = StateRadio.getGemteAmKanaler();
            listen[kanalNummer] = StateRadio.getAmTuneValue();
        }

        //lukker ned igen og tilbage til radio state

        context.updateDisplayTime();
        context.setState(new StateRadio());


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
        //skifter plads den gemma forbindelsen på
        kanalNummer++;
        if (kanalNummer == 5) {
            kanalNummer = 0;
        }

        if (StateRadio.playingFM) {
            context.ui.setDisplayText("FM:" + (kanalNummer+1));
        } else {
            context.ui.setDisplayText("AM:" + (kanalNummer+1));
        }
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
