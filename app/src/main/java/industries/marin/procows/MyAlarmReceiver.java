package industries.marin.procows;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;
import java.util.List;

import industries.marin.procows.Models.Alarma;
import industries.marin.procows.db.AlarmaDao;
import industries.marin.procows.db.DataBaseHelper;

/**
 * Created by RicardoM on 23/06/2017.
 */

public class MyAlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    private NotificationManager notificationManager;
    private final int NOTIFICATION_ID = 1010;
    String nombre;

    AlarmaDao dao;

    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar calendario = Calendar.getInstance();
        int hora, min,dia,mes,ano;
        String fecha_sistema,hora_sistema;

        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema = dia+"/"+mes+"/"+ano;
        hora_sistema = ""+hora+":"+min+"";

        dao = new AlarmaDao(context);

        List<Alarma> list = dao.getByDate(fecha_sistema,hora_sistema);

       // Alarma a = null;
       // nombre = a.getNombre();

        if (list.size() > 0){
            showNotification(context,"Alarma de Celo");
        }
    }

    private void showNotification(Context contexto, String t) {
        Intent notificationIntent = new Intent(contexto, Alarms.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(contexto, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = new long[]{2000, 1000, 2000};

        NotificationCompat.Builder builder = new NotificationCompat.Builder(contexto);
        builder.setContentIntent(contentIntent)

                .setContentTitle("ProCows")
                .setContentText("Alarma de Celos")
                .setLights(Color.YELLOW, 1000, 1000)
                .setContentInfo("Info")
                .setLargeIcon(BitmapFactory.decodeResource(contexto.getResources(), R.drawable.vaca))
                .setSmallIcon(R.drawable.vaca)
                .setAutoCancel(true) //Cuando se pulsa la notificación ésta desaparece
                .setSound(defaultSound)
                .setVibrate(pattern);

        Notification notificacion = new NotificationCompat.BigTextStyle(builder)
                .bigText("Alarma de celo programada")
                .setBigContentTitle("ProCows")
                .setSummaryText("Resumen de tareas")
                .build();

        notificationManager = (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificacion);
    }
}
