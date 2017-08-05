package industries.marin.procows;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import industries.marin.procows.adapters.AlarmAdapterPro;
import industries.marin.procows.databinding.ActivityAlarmsBinding;
import industries.marin.procows.db.AlarmaDao;
import industries.marin.procows.models.Alarma;
import industries.marin.procows.util.L;


public class Alarms extends AppCompatActivity implements AlarmAdapterPro.OnAlarmListener {

    ActivityAlarmsBinding binding;

    AlarmAdapterPro adapter;

    AlarmaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarms);


        L.data = new ArrayList<>();
        adapter = new AlarmAdapterPro(getLayoutInflater(), this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        dao = new AlarmaDao(this);

        loadData();
    }


    public void loadData() {
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

        if(list.size() > 0 ) {
            for (Alarma a : list) {
                L.data.add(a);
            }
            adapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(this, R.string.empy, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onAlarm(View v) {
        int pos =  binding.recycler.getChildAdapterPosition(v);

        Intent intent = new Intent(this, DetailAlarm.class);
        intent.putExtra(DetailAlarm.EXTRA_POS, pos);

        startActivity(intent);
    }





//regionbad
/*public class Alarms extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{


    List<Alarma> data;
    AlarmAdapter adapter;
    ListView list;

    ActivityAlarmsBinding binding;
    ViewDataBinding binding2;

    AlarmaDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding2.setVariable(industries.marin.procows.BR.onItemClick, this);

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

/*
        List<Alarma> list = dao.getAll();
        for (Alarma a : list){
            data.add(a);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }*/
//endregion


}
