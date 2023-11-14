package tn.esprit.mobile;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.mobile.Entities.CommandeModel;

public class DBHelper extends SQLiteOpenHelper {
    public static final String  DBName="stockins.db";
    private static final String  TABLE_NAME="Commande";
    private static final String COLUMN_ID="codecommande";
    private static final String COLUMN_TITLE="commandetitle";

    private static final String COLUMN_NAME="Qte";
    private  static final String COLUMN_ADRESSE="Adresse";

    private  static final String COLUMN_TOTAL="Total";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the users table
        sqLiteDatabase.execSQL("CREATE TABLE users (username TEXT PRIMARY KEY, password TEXT, nom TEXT, prenom TEXT)");
        Log.d("DBHelper", "onCreate: Creating tables");

        // Create the Commande table
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_NAME + " INTEGER," +
                COLUMN_ADRESSE + " TEXT," +
                COLUMN_TOTAL + " FLOAT);";
        sqLiteDatabase.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }
    public boolean insertData(String username,String password, String nom, String prenom)
    {
        SQLiteDatabase myDB=  this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("nom",nom);
        contentValues.put("prenom",prenom);
        long result= myDB.insert("users",null,contentValues);
        if(result==-1)return false;
        else
            return true;

    }

    public boolean checkUsername(String username)
    {
        SQLiteDatabase myDB =this.getReadableDatabase();
        Cursor cursor =myDB.rawQuery("select * from users where username= ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
            else return false;
    }
    public boolean checkCredentials(String username, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
    public boolean insertDataCommande(String codecommande,String
            commandetitle,String qte,String adresse,String total)
    {
        SQLiteDatabase myDB=  this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("codecommande",codecommande);
        contentValues.put("commandetitle",commandetitle);
        contentValues.put("Qte",qte);
        contentValues.put("Adresse",adresse);
        contentValues.put("Total",total);
        long result= myDB.insert("Commande",null,contentValues);
        if(result==-1)return false;
        else
            return true;

    }
    public List<CommandeModel> getAllCommandes() {
        List<CommandeModel> commandesList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
        int columnIndexTitle = cursor.getColumnIndex(COLUMN_TITLE);
        int columnIndexQte = cursor.getColumnIndex(COLUMN_NAME);
        int columnIndexAdresse = cursor.getColumnIndex(COLUMN_ADRESSE);
        int columnIndexTotal = cursor.getColumnIndex(COLUMN_TOTAL);

        while (cursor.moveToNext()) {
            CommandeModel commande = new CommandeModel();

            if (columnIndexId != -1) {
                commande.setCodeCommande(cursor.getString(columnIndexId));
            }

            if (columnIndexTitle != -1) {
                commande.setCommandeTitle(cursor.getString(columnIndexTitle));
            }

            if (columnIndexQte != -1) {
                commande.setQte(cursor.getString(columnIndexQte));
            }

            if (columnIndexAdresse != -1) {
                commande.setAdresse(cursor.getString(columnIndexAdresse));
            }

            if (columnIndexTotal != -1) {
                commande.setTotal(cursor.getString(columnIndexTotal));
            }

            commandesList.add(commande);
        }

        cursor.close();
        return commandesList;
    }

}