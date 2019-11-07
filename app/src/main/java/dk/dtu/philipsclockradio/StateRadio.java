package dk.dtu.philipsclockradio;

public class StateRadio extends StateAdapter {

    private static int fmTuneValue = 0;
    private static int amTuneValue;
    //    private int fmTuneValue = 0;
    //    private int amTuneValue = 0;


    // 0 og 50 er ikke kanaler men ender nu ! :D !
    //Disse kanaler er nogen som vi skal forstille os der er virkelige
    int[] fmChannels = {0, 1, 2, 5, 8, 12, 17, 23, 33, 36, 40, 50};
    int[] amChannels = {0, 2, 3, 7, 9, 11, 14, 23, 30, 33, 39, 50};

    //Gemte kanaler... som ikke bliver brugt til noget i opgaven???
    private static int[] gemteFmKanaler = new int[5];
    private static int[] gemteAmKanaler = new int[5];


    public static boolean playingFM = true;



    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.toggleRadioPlaying(true);
        context.ui.turnOnLED(4);
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.toggleRadioPlaying(false);
        context.ui.turnOffLED(4);
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        //en frekvens ned
        if (playingFM) {
            fmTuneValue--;
            if (fmTuneValue < 0) {
                fmTuneValue = 50;
            }

            context.ui.updateFMAMtext(playingFM, String.valueOf(fmTuneValue));

        } else {
            amTuneValue--;
            if (amTuneValue < 0) {
                amTuneValue = 50;
            }


            context.ui.updateFMAMtext(playingFM, String.valueOf(amTuneValue));
        }
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        //en frekvens op
        if (playingFM) {
            fmTuneValue++;
            if (fmTuneValue > 50) {
                fmTuneValue = 0;
            }


            context.ui.updateFMAMtext(playingFM, String.valueOf(fmTuneValue));

        } else {
            amTuneValue++;
            if(amTuneValue > 50) {
                amTuneValue = 0;
            }


            context.ui.updateFMAMtext(playingFM, String.valueOf(amTuneValue));
        }


    }

    @Override
    public void onClick_Preset(ContextClockradio context) {

    }

    @Override
    public void onClick_Power(ContextClockradio context) {
        playingFM = !playingFM;
        context.ui.toggleFMAMplaying(playingFM);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {

    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

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

    @Override
    public void onLongClick_Hour(ContextClockradio context) {
        nearestChannel(false);

        if (playingFM) {
            context.ui.updateFMAMtext(playingFM, String.valueOf(fmTuneValue));
        } else {
            context.ui.updateFMAMtext(playingFM, String.valueOf(amTuneValue));
        }
    }

    @Override
    public void onLongClick_Min(ContextClockradio context) {
        nearestChannel(true);

        if (playingFM) {
            context.ui.updateFMAMtext(playingFM, String.valueOf(fmTuneValue));
        } else {
            context.ui.updateFMAMtext(playingFM, String.valueOf(amTuneValue));
        }
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {

        context.setState(new StateSave());

    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        onExitState(context);
        context.setState(new StateStandby(context.getTime()));
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



    public void nearestChannel(boolean higherChannel) {


            if (playingFM) {
                //FM kanaler

                if (higherChannel) {
                    // højere kanal
                    for (int i = 0; fmChannels.length > i; i++) {
                        if (fmChannels[i] > fmTuneValue) {
                            fmTuneValue = fmChannels[i];
                            break;
                        }
                    }

                    //Hvis den når 50 som er end kanal
                    if (fmChannels[(fmChannels.length-1)] == fmTuneValue) {
                        fmTuneValue = fmChannels[1];
                    }


                } else {
                    //lavere kanal
                    for (int i = (fmChannels.length-1); 0 <= i; i--) {
                        if (fmChannels[i] < fmTuneValue) {
                            fmTuneValue = fmChannels[i];
                            break;
                        }
                    }

                    //Hvis den når 0 som er minimums kanal
                    if (fmChannels[0] == fmTuneValue) {
                        fmTuneValue = fmChannels[(fmChannels.length-2)];
                    }

                }

            } else {
                // AM kanaler
                if (higherChannel) {
                    //højere kanal
                    for (int i = 0; amChannels.length > i; i++) {
                        if (amChannels[i] > amTuneValue) {
                            amTuneValue = amChannels[i];
                            break;
                        }
                    }

                    //Hvis den når 50 som er end kanal
                    if (amChannels[(amChannels.length-1)] == amTuneValue) {
                        amTuneValue = amChannels[1];
                    }

                } else {
                    //lavere kanal
                    for (int i = (amChannels.length-1); 0 <= i; i--) {
                        if (amChannels[i] < amTuneValue) {
                            amTuneValue = amChannels[i];
                            break;
                        }
                    }

                    //Hvis den når 0 som er minimums kanal
                    if (amChannels[0] == amTuneValue) {
                        amTuneValue = amChannels[(amChannels.length-2)];
                    }
                }
            }
        }



        //getter and setter
    public static int getAmTuneValue() {
        return amTuneValue;
    }

    public static int getFmTuneValue() {
        return fmTuneValue;
    }

    public static int[] getGemteFmKanaler() {
        return gemteFmKanaler;
    }

    public static void setGemteFmKanaler(int[] gemteFmKanaler) {
        StateRadio.gemteFmKanaler = gemteFmKanaler;
    }

    public static int[] getGemteAmKanaler() {
        return gemteAmKanaler;
    }

    public static void setGemteAmKanaler(int[] gemteAmKanaler) {
        StateRadio.gemteAmKanaler = gemteAmKanaler;
    }
}



