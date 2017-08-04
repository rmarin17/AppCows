package industries.marin.procows.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import industries.marin.procows.models.Alarma;

/**
 * Created by RicardoM on 28/03/2017.
 */

public class AlarmaDao {

    static final String TABLE = "alarma";
    static final String C_NAME = "nombre";
    static final String C_DATE = "fecha";
    static final String C_TIME = "hora";
    static final String C_PARTO = "parto";

    SQLiteDatabase db;

    public AlarmaDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert (Alarma alarma){
        ContentValues cV = new ContentValues();
        cV.put(C_NAME, alarma.getNombre());
        cV.put(C_DATE, alarma.getFecha());
        cV.put(C_TIME, alarma.getHora());
        cV.put(C_PARTO, alarma.getParto());
        long id = db.insert(TABLE,null,cV);
        alarma.setId(id);

    }

    public void update (Alarma alarma){

        ContentValues cV = new ContentValues();
        cV.put(C_NAME, alarma.getNombre());
        cV.put(C_DATE, alarma.getFecha());
        cV.put(C_TIME, alarma.getHora());
        cV.put(C_PARTO, alarma.getParto());
        long id = db.update(TABLE,cV,"_id = ?",new String[]{alarma.getId()+" "});
    }

    public void delete (long id){
        db.delete(TABLE,"_id = "+id, null);
    }

    public  Alarma getByid (long id){

        Cursor c = db.rawQuery("SELECT * FROM alarma WHERE _id="+id,null);
        return cursorToMensaje(c);

    }

    public List<Alarma> getAll (){

        Cursor c = db.rawQuery("SELECT * FROM alarma",null);
        return cursorToList(c);
    }

    public List<Alarma> getByDate (String fecha_sistema, String hora_sistema){

        //Cursor c = db.rawQuery("SELECT * FROM alarma WHERE fecha LIKE '%"+fecha_sistema+"%'",null);   ",null);//
        Cursor c = db.rawQuery("SELECT * FROM alarma WHERE fecha='"+fecha_sistema+"' AND hora= '"+hora_sistema+"'", null);
        return cursorToList(c);
    }

    private Alarma cursorToMensaje (Cursor c){

        Alarma alarma = null;

        if (c.moveToNext()){
            alarma = new Alarma();
            alarma.setId(c.getLong(0));
            alarma.setNombre(c.getString(1));
            alarma.setFecha(c.getString(2));
            alarma.setHora(c.getString(3));
            alarma.setParto(c.getString(4));
        }
        return alarma;
    }

    private List<Alarma> cursorToList (Cursor c){

        List<Alarma> data = new ArrayList<>();

        for (int i= 0; i< c.getCount();i++){
            Alarma a = cursorToMensaje(c);
            data.add(a);
        }

        return data;
    }

}
