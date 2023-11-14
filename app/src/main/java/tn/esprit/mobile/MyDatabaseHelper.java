package tn.esprit.mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "stock.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_NAME = "promotion";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_PRODUCTID="product_id";
    private static final String COLUMN_STARTDATE="start_date";
    private static final String COLUMN_ENDDATE="end_date";
    private static final String COLUMN_DISCOUNT="discount";


    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_PRODUCT_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_QUANTITY = "quantity";
    private static final String COLUMN_PRODUCT_PRICE = "price";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String productTableQuery =
                "CREATE TABLE " + TABLE_PRODUCTS +
                        " (" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PRODUCT_NAME + " TEXT, " +
                        COLUMN_PRODUCT_QUANTITY + " INTEGER, " +
                        COLUMN_PRODUCT_PRICE + " FlOAT);";
        sqLiteDatabase.execSQL(productTableQuery);
        insertProduct(sqLiteDatabase, "Produit 1", 10, 20.0);
        insertProduct(sqLiteDatabase, "Produit 2", 5, 15.0);
        insertProduct(sqLiteDatabase, "Produit de cosmetique", 5, 150.0);
        insertProduct(sqLiteDatabase, "Produit4", 5, 205.0);
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PRODUCTID + " INTEGER, " +
                        COLUMN_STARTDATE + " DATE, " +
                        COLUMN_ENDDATE + " DATE, " +
                        COLUMN_DISCOUNT + " FLOAT, " +
                        "FOREIGN KEY (" + COLUMN_PRODUCTID + ") REFERENCES " + TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_ID + "));";
        sqLiteDatabase.execSQL(query);
    }
    private void insertProduct(SQLiteDatabase db, String name, int quantity, double price) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(COLUMN_PRODUCT_PRICE, price);
        db.insert(TABLE_PRODUCTS, null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
       db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCTS);
        onCreate(db);
    }
    void addpromotion(Product product, Date start_date,Date end_date,float discount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = dateFormat.format(start_date);
        String endDateString = dateFormat.format(end_date);
        cv.put(COLUMN_PRODUCTID, product.getId());
        cv.put(COLUMN_STARTDATE, start_date.toString());
        cv.put(COLUMN_ENDDATE,end_date.toString());
        cv.put(COLUMN_DISCOUNT,discount);
        long result=db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Added succesfully!",Toast.LENGTH_SHORT).show();
        }

    }
    public Product getProductById(int productId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = null;

        String[] projection = {
                COLUMN_PRODUCT_ID,
                COLUMN_PRODUCT_NAME,
                COLUMN_PRODUCT_QUANTITY,
                COLUMN_PRODUCT_PRICE
        };

        String selection = COLUMN_PRODUCT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(productId)};

        Cursor cursor = db.query(
                TABLE_PRODUCTS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Récupérer les valeurs de la base de données
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_QUANTITY));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE));

            // Créer un objet Product
            product = new Product(id, name, quantity, price);

            // Fermer le curseur
            cursor.close();
        }

        // Retourner le produit (peut être null si le produit n'est pas trouvé)
        return product;
    }

    Cursor readAllData(){
        String query="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=null;
        if(db!=null){
           cursor= db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id,String product, String start_date,String end_date,String discount){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_PRODUCTID,product);
        cv.put(COLUMN_STARTDATE,start_date);
        cv.put(COLUMN_ENDDATE,end_date);
        cv.put(COLUMN_DISCOUNT,discount);

        long result=db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

}
