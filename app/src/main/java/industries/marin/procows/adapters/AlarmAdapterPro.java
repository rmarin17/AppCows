package industries.marin.procows.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import industries.marin.procows.R;
import industries.marin.procows.databinding.TemplateAlarmBinding;
import industries.marin.procows.util.L;

/**
 * Created by RicardoM on 04/08/2017.
 */

public class AlarmAdapterPro extends RecyclerView.Adapter<AlarmAdapterPro.AlarmViewHolder> {


    public interface OnAlarmListener{
        void onAlarm(View v);
    }

    LayoutInflater inflater;
    OnAlarmListener onAlarmListener;

    public AlarmAdapterPro(LayoutInflater inflater, OnAlarmListener onAlarmListener) {
        this.onAlarmListener = onAlarmListener;
        this.inflater = inflater;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = inflater.inflate(R.layout.template_alarm, parent, false);
        AlarmViewHolder holder = new AlarmViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.binding.setAlar(L.data.get(position));
        holder.binding.setHandler(this);
    }

    @Override
    public int getItemCount() {
        return L.data.size();
    }

    public void onClickAlarm(View v){
        onAlarmListener.onAlarm(v);
    }

    //Retorna el tipo de view o Celda
    /*@Override
    public int getItemViewType(int position) { return 0;}*/

    public static class AlarmViewHolder extends RecyclerView.ViewHolder{

        TemplateAlarmBinding binding;

        public AlarmViewHolder(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);

        }
    }
}
