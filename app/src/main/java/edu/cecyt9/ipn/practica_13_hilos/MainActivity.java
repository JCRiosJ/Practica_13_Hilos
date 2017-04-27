package edu.cecyt9.ipn.practica_13_hilos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;


public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida;
    private TextView salida2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        salida2 = (TextView) findViewById(R.id.salida2);
    }

    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida.append(n +"! = ");
        //  FIBONACCI
        MiThread hilo = new MiThread(n);
        hilo.run();

        MiThread2 hilo2 = new MiThread2(n);
        hilo2.run();
    }

    public int factorial(int n) {
        int res=1;
        for (int i=1; i<=n; i++){
            res*=i;
            SystemClock.sleep(1000);
        }

        return res;

    }

    class MiThread extends Thread {
        private int n, res;

        public MiThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            res = factorial(n);
            salida.append(res + "\n");
        }
//        @Override
//        public void run() {
//            res = factorial(n);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    salida.append(res + "\n");
//                }
//            });
//        }
    }

    class MiThread2 extends Thread {
        private int n, res = 0;

        public MiThread2(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            int ant = 0;
            int ant2 = 0;
            String resS = "0,";

            for (int i = 1; i<n; i++)
            {
                if(i%2==0)
                {
                    //PAR
                    if(i==2)
                    {
                        res = 1;
                    }
                    else
                    {
                        res = ant+ant2;
                    }
                    ant = res;

                }
                else
                {
                    //IMPAR
                    if(i==1)
                    {
                        res = 1;
                    }
                    else
                    {
                        res = ant+ant2;
                    }
                    ant2 = res;
                }
                if(i == n-1)
                {
                    resS = resS+" "+res+".";
                }
                else
                {
                    resS = resS+" "+res+", ";
                }
            }
//            System.out.println(resS);
            salida2.append(resS + "\n");
        }
    }

//    public void calcularOperacion(View view) {
//        int n = Integer.parseInt(entrada.getText().toString());
//        salida.append(n + "! = ");
//        MiThread thread = new MiThread(n);
//        thread.start();
//    }


//    ejemplo AsyncTask
//    class MiTarea extends AsyncTask<Integer, Void, Integer> {
//
//        @Override
//
//        protected Integer doInBackground(Integer... n) {
//
//            return factorial(n[0]);
//
//        }
//
//        @Override
//
//        protected void onPostExecute(Integer res) {
//
//            salida.append(res + "\n");
//
//        }
//
//    }

//        public void calcularOperacion(View view) {
//        int n = Integer.parseInt(entrada.getText().toString());
//        salida.append(n + "! = ");
//        MiTarea tarea = new MiTarea();
//        tarea.execute(n);
//
//        }

    //    ejemplo AsyncTask whit progressdialog
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(false);
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0]; i++) {
//
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//    }

    //    ejemplo AsyncTask whit progressdialog cancel
//    class MiTarea extends AsyncTask<Integer, Integer, Integer> {
//
//        private ProgressDialog progreso;
//
//        @Override protected void onPreExecute() {
//
//            progreso = new ProgressDialog(MainActivity.this);
//
//            progreso.setProgressStyle(ProgressDialog.
//                    STYLE_HORIZONTAL);
//
//            progreso.setMessage("Calculando...");
//
//            progreso.setCancelable(true);
//
//            progreso.setOnCancelListener(new OnCancelListener() {
//
//                @Override
//                public void onCancel(DialogInterface dialog) {
//
//                    MiTarea.this.cancel(true);
//
//                }
//
//            });
//
//            progreso.setMax(100);
//
//            progreso.setProgress(0);
//
//            progreso.show();
//
//        }
//
//        @Override protected Integer doInBackground(Integer... n) {
//
//            int res = 1;
//
//            for (int i = 1; i <= n[0] && !isCancelled(); i++) {
//                res *= i;
//
//                SystemClock.sleep(1000);
//
//                publishProgress(i*100 / n[0]);
//
//            }
//
//            return res;
//
//        }
//
//        @Override protected void onProgressUpdate(Integer... porc) {
//
//            progreso.setProgress(porc[0]);
//
//        }
//
//        @Override protected void onPostExecute(Integer res) {
//
//            progreso.dismiss();
//
//            salida.append(res + "\n");
//
//        }
//
//        @Override protected void onCancelled() {
//
//            salida.append("cancelado\n");
//
//        }
//
//    }

}
