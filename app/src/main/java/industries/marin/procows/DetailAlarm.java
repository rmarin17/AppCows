package industries.marin.procows;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewTreeObserver;

import industries.marin.procows.databinding.ActivityDetailAlarmBinding;
import industries.marin.procows.models.Alarma;
import industries.marin.procows.util.L;

public class DetailAlarm extends AppCompatActivity implements  ViewTreeObserver.OnGlobalLayoutListener, View.OnClickListener{

    public static final String EXTRA_POS = "pos";
    public static final int DARKEN = 20;

    ActivityDetailAlarmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_alarm);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.del);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        int pos =  getIntent().getExtras().getInt(EXTRA_POS);
        Alarma alar = L.data.get(pos);

        binding.setAlar(alar);

        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGlobalLayout() {

        BitmapDrawable drawable = (BitmapDrawable) binding.img.getDrawable();
        Palette p =  Palette.from(drawable.getBitmap()).generate();

        int colorDefault = ContextCompat.getColor(this, R.color.colorPrimary);
        binding.collapsingBar.setContentScrimColor(p.getLightVibrantColor(colorDefault));

    }


    private int getStatusColor(int color){
        int r, b, g;
        r = getDarkColor(Color.red(color));
        b = getDarkColor(Color.blue(color));
        g = getDarkColor(Color.green(color));
        return Color.rgb(r,g,b);
    }

    private int getDarkColor(int color){
        int c = color - DARKEN;
        if(c < 0)
            c = 0;
        return c;
    }
}
