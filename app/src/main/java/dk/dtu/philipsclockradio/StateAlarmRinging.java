package dk.dtu.philipsclockradio;

public class StateAlarmRinging extends StateAdapter{

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();

        context.ui.setDisplayText("alarm");
        System.out.println("Den ringer");

        //her ku du jo ha fået den til at spille nogen form for lyd hvis man ønskede... Der ud over ku man også lave en  setting hvor man valgt om det sku være radio eller alarm

    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffTextBlink();
    }



    @Override
    public void onClick_AL1(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {

        context.setSnooze(true);
        context.setState(new StateStandby(context.getTime()));

    }



}
