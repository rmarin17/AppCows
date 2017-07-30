package industries.marin.procows.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import industries.marin.procows.Models.Alarma;
import industries.marin.procows.R;

/**
 * Created by RicardoM on 20/06/2017.
 */

public class AlarmAdapter extends BaseAdapter {


    Context context;
    List<Alarma> data;

    public AlarmAdapter(Context context, List<Alarma> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        if (v == null)
            v = View.inflate(context, R.layout.item_alarm, null);

        Alarma a = data.get(position);

        TextView nombre = (TextView) v.findViewById(R.id.nombre);
        TextView fecha = (TextView) v.findViewById(R.id.fecha);
        //TextView hora = (TextView) v.findViewById(R.id.hora);
        TextView parto = (TextView) v.findViewById(R.id.parto);

        nombre.setText(a.getNombre());
        fecha.setText(a.getFecha());
        //hora.setText(a.getHora());
        parto.setText(a.getParto());

        return v;
    }
}
