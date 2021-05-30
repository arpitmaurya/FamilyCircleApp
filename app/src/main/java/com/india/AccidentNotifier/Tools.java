package com.india.AccidentNotifier;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.john.waveview.WaveView;

public class Tools
{

    private Handler progressBarHandler = new Handler();
    static Context context;
    static Animation animslide ;
    static Animation animFadeIn ;

    public static Context getContext() {
        return context;
    }



    public Tools(Context context){
        this.context = context;
    }


        public void setProgress(final ProgressBar pgr , final int progress,final int time){
    new Thread(new Runnable() {
        int progressBarStatus = 0;


        public void run() {

            while (progressBarStatus < progress+1) {
                // performing operation
                progressBarStatus++;
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Updating the progress bar
                progressBarHandler.post(new Runnable() {
                    public void run() {
                        pgr.setProgress(progressBarStatus);

                    }
                });
            }

        }


    }).start();}

    public void setProgress(final ProgressBar pgr , final int progress, final WaveView waveView,final int time){
        new Thread(new Runnable() {
            int progressBarStatus = 0;


            public void run() {

                while (progressBarStatus < progress+1) {
                    // performing operation
                    progressBarStatus++;
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            waveView.setProgress(progressBarStatus);
                            pgr.setProgress(progressBarStatus);

                        }
                    });
                }

            }


        }).start();}



    public void setProgress(final ProgressBar pgr , final int progress, final WaveView waveView, final int time, final int lastProgstamp){
        new Thread(new Runnable() {
            int progressBarStatus = lastProgstamp;


            public void run() {

                while (progressBarStatus < progress+1) {
                    // performing operation
                    progressBarStatus++;
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            waveView.setProgress(progressBarStatus);
                            pgr.setProgress(progressBarStatus);

                        }
                    });
                }

            }


        }).start();}


   /* public void setProgress(final ProgressBar pgr , final int progress, final WaveView waveView, final int time, final int lastProgstamp , int profileprog){
        new Thread(new Runnable() {
            int progressBarStatusWave = lastProgstamp;



            public void run() {

                while (progressBarStatusWave < progress+1) {
                    // performing operation
                    progressBarStatusWave++;
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            waveView.setProgress(progressBarStatus);
                            pgr.setProgress(progressBarStatus);

                        }
                    });
                }

            }


        }).start();}
*/

    public void setProgress(final ProgressBar pgr , final int progress, final int time, final int lastProgstamp){
        new Thread(new Runnable() {
            int progressBarStatus = lastProgstamp;


            public void run() {

                while (progressBarStatus < progress+1) {
                    // performing operation
                    progressBarStatus++;
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            pgr.setProgress(progressBarStatus);

                        }
                    });
                }

            }


        }).start();}



   public static void setGradient(TextView textView){
            Shader shader = new LinearGradient(0f, 20f, 0f, textView.getTextSize(), Color.parseColor("#2D62C9"),  Color.parseColor("#3E7DF8"), Shader.TileMode.CLAMP);
            textView.getPaint().setShader(shader);
        }

    public static void setAnimation(int i){


      switch (i) {
          case 1:
              animslide = AnimationUtils.loadAnimation(Tools.getContext(),
                      R.anim.animlefttoright);
              break;

          case 2:
              animFadeIn = AnimationUtils.loadAnimation(Tools.getContext(),
                      R.anim.fadeinanim);
              break;


      }
    }

    static String inviteCodeGenerator(int n )
    {


        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n+1);

        for (int i = 0; i < n+1; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb

            if(i ==3){
           sb.append("-");
            }
            else{
             sb.append(AlphaNumericString
                    .charAt(index));
        }}

        return sb.toString();
    }


}
