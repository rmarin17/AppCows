package industries.marin.procows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import industries.marin.procows.Models.Alarma;
import industries.marin.procows.adapters.AlarmAdapter;
import industries.marin.procows.db.AlarmaDao;

public class Alarms extends AppCompatActivity {

    List<Alarma> data;
    AlarmAdapter adapter;
    ListView list;

    AlarmaDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);

        list = (ListView) findViewById(R.id.list);
        data = new ArrayList<>();
        adapter = new AlarmAdapter(this, data);

        list.setAdapter(adapter);

        dao = new AlarmaDao(this);
        //data.add(mensaje);


        loaddata();

    }

    public void loaddata() {
       /* Calendar calendario = Calendar.getInstance();
        int hora, min,dia,mes,ano;
        String fecha_sistema,hora_sistema, t;

        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mes = calendario.get(Calendar.MONTH)+1;
        ano = calendario.get(Calendar.YEAR);
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        fecha_sistema = dia+"/"+mes+"/"+ano;
        hora_sistema = hora+":"+min;*/


        List<Alarma> list = dao.getAll();
        for (Alarma a : list){
            data.add(a);
        }
        adapter.notifyDataSetChanged();
    }
}
