package industries.marin.procows;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import industries.marin.procows.db.AlarmaDao;
import industries.marin.procows.models.Alarma;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    LinearLayout btnfecha, btnala,fechacelo;
    EditText fec,nom,tor;
    TextView celo,parto;
    AlarmaDao dao;
    String nombre,fechapc,hora,fechap, fechah, toro;
    Button guardar;
    private  int dia,mes,ano,diac,mesc,anoc,hor,min,anop,diap,mesp,diasel,messel,anosel;

    AlarmManager alarmManager;
    private static final int ALARM_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnfecha = (LinearLayout) findViewById(R.id.btnfecha);
        fechacelo = (LinearLayout) findViewById(R.id.fechacelo);
        btnala = (LinearLayout) findViewById(R.id.btnAla);
        fec = (EditText)findViewById(R.id.fecha);
        nom = (EditText)findViewById(R.id.nombre);
        tor = (EditText)findViewById(R.id.toro);
        celo = (TextView) findViewById(R.id.celo);
        parto = (TextView) findViewById(R.id.parto);

        guardar = (Button) findViewById(R.id.guardar);

//region Configurar Fecha
       /* fec.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                          @Override
                                          public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                              boolean procesado = false;

                                              if (actionId == EditorInfo.IME_ACTION_SEND) {
                                                  Calendar ca = Calendar.getInstance();
                                                  ano = ca.get(Calendar.YEAR);
                                                  mes = ca.get(Calendar.MONTH);
                                                  dia = ca.get(Calendar.DAY_OF_MONTH);
                                                  hor = ca.get(Calendar.HOUR_OF_DAY);
                                                  min = ca.get(Calendar.MINUTE);



                                                  dao = new AlarmaDao(Principal.this);

                                                  DatePickerDialog datePickerDialog1 = new DatePickerDialog(Principal.this, new DatePickerDialog.OnDateSetListener() {
                                                      @Override
                                                      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                          fec.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                                          Calendar sel = Calendar.getInstance();
                                                          Calendar part = Calendar.getInstance();
                                                          sel.set(year, monthOfYear, dayOfMonth);
                                                          part.set(year, monthOfYear, dayOfMonth);
                                                          part.add(Calendar.DATE, 283);
                                                          sel.add(Calendar.DATE, 21);
                                                          anoc = sel.get(Calendar.YEAR);
                                                          mesc = sel.get(Calendar.MONTH);
                                                          diac = sel.get(Calendar.DAY_OF_MONTH);
                                                          anop = part.get(Calendar.YEAR);
                                                          mesp = part.get(Calendar.MONTH);
                                                          diap = part.get(Calendar.DAY_OF_MONTH);
                                                          celo.setText(diac + "/" + (mesc + 1) + "/" + anoc);
                                                          parto.setText(diap + "/" + (mesp + 1) + "/" + anop);
                                                          fecha = diac + "/" + (mesc + 1) + "/" + anoc;
                                                          fechap = diap + "/" + (mesp + 1) + "/" + anop;
                                                          hora = hor+":"+min;
                                                      }
                                                  }, ano, mes, dia);
                                                  datePickerDialog1.show();

                                                  // Mostrar mensaje
                                                  //Toast.makeText(Principal.this,
                                                  //        "Buscar:" + v.getText().toString(), Toast.LENGTH_LONG).show();

                                                  // Ocultar teclado virtual
                                                  InputMethodManager imm =
                                                          (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                                  imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                                                  procesado = true;
                                              }
                                              return procesado;
                                          }
                                      });*/
        //endregion

        guardar.setOnClickListener(this);
        btnfecha.setOnClickListener(this);
        btnala.setOnClickListener(this);
        fechacelo.setOnClickListener(this);

        //servicio();

    }

    /*public void servicio() {
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(Principal.this, MyAlarmReceiver.class);

        PendingIntent pIntent = PendingIntent.getBroadcast(this,  ALARM_REQUEST_CODE , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis(); //first run of alarm is immediate // aranca la palicacion
        int intervalMillis = 5 * 1000; //3 segundos

        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
    }*/

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAla:
                Intent btnala = new Intent(Principal.this, Alarms.class);
                startActivity(btnala);
                break;
            case R.id.guardar:

                if(nom.getText().toString().length() > 0 && fec.getText().toString().length() > 0 && tor.getText().toString().length() > 0) {

                    nombre = nom.getText().toString();
                    toro = tor.getText().toString();
                    Alarma a = new Alarma();
                    a.setNombre(nombre);
                    a.setProcelo(fechapc);
                    a.setParto(fechap);
                    a.setHora("9:10");
                    a.setFechaince(fechah);
                    a.setToro(toro);
                    dao.insert(a);

                    celo.setText("");
                    parto.setText("");
                    nom.setText("");
                    fec.setText("");
                    tor.setText("");

                    // region alarma
                    /*AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent myIntent;
                    PendingIntent pendingIntent;

                    myIntent = new Intent(Principal.this,MyAlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);

                    manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,pendingIntent);*/
                    //endregion
                    AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

                    Intent intent = new Intent(Principal.this, MyAlarmReceiver.class);

                    PendingIntent pIntent = PendingIntent.getBroadcast(this,  ALARM_REQUEST_CODE , intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    long firstMillis = System.currentTimeMillis(); //first run of alarm is immediate // aranca la palicacion
                    int intervalMillis = 5 * 1000; //3 segundos

                    alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);


                    Toast.makeText(this, "Alarma Programada", Toast.LENGTH_LONG).show();
                }

                else {

                    Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_LONG).show();

                }
                break;

            case R.id.btnfecha:
                Calendar c = Calendar.getInstance();
                ano = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);
                hor = c.get(Calendar.HOUR_OF_DAY);
                min = c.get(Calendar.MINUTE);



                dao = new AlarmaDao(this);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fec.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        Calendar sel = Calendar.getInstance();
                        Calendar part = Calendar.getInstance();
                        Calendar hoy = Calendar.getInstance();
                        hoy.set(year, monthOfYear, dayOfMonth);
                        sel.set(year, monthOfYear, dayOfMonth);
                        part.set(year, monthOfYear, dayOfMonth);
                        part.add(Calendar.DATE, 283);
                        sel.add(Calendar.DATE, 21);
                        anosel = sel.get(Calendar.YEAR);
                        messel = sel.get(Calendar.MONTH);
                        diasel = sel.get(Calendar.DAY_OF_MONTH);
                        anoc = sel.get(Calendar.YEAR);
                        mesc = sel.get(Calendar.MONTH);
                        diac = sel.get(Calendar.DAY_OF_MONTH);
                        anop = part.get(Calendar.YEAR);
                        mesp = part.get(Calendar.MONTH);
                        diap = part.get(Calendar.DAY_OF_MONTH);
                        celo.setText(diac + "/" + (mesc + 1) + "/" + anoc);
                        parto.setText(diap + "/" + (mesp + 1) + "/" + anop);
                        fechapc = diac + "/" + (mesc + 1) + "/" + anoc;
                        fechap = diap + "/" + (mesp + 1) + "/" + anop;
                        fechah = diasel +"/"+ (messel+1)+"/"+ anosel;
                        hora = hor+":"+min;
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
                break;
        }
    }


}
