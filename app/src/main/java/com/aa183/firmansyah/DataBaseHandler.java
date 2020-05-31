package com.aa183.firmansyah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataBaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_book";
    private final static String TABLE_BOOK = "t_book";
    private final static String KEY_ID_BOOK = "ID_Book";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_GENRE = "Genre";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_ISI_SINOPSIS = "Isi_Sinopsis";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());
    private Context context;

    public DataBaseHandler (Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK
                + "(" + KEY_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_GAMBAR + " TEXT, " + KEY_JUDUL + " TEXT, "
                + KEY_PENULIS + " TEXT," + KEY_GENRE + " TEXT, "
                + KEY_TGL + " DATE," + KEY_ISI_SINOPSIS + " TEXT);";

        db.execSQL(CREATE_TABLE_BOOK);
        inialisasiBookAwal(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BOOK;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahBook (Book dataBook){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_GAMBAR, dataBook.getGambarBook());
        cv.put(KEY_JUDUL, dataBook.getJudulBook());
        cv.put(KEY_PENULIS, dataBook.getPenulis());
        cv.put(KEY_GENRE, dataBook.getGenre());
        cv.put(KEY_TGL, sdFormat.format(dataBook.getTanggal()));
        cv.put(KEY_ISI_SINOPSIS, dataBook.getSinopsisBook());

        db.insert(TABLE_BOOK, null, cv);
        db.close();
    }

    public void tambahBook (Book dataBook, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_GAMBAR, dataBook.getGambarBook());
        cv.put(KEY_JUDUL, dataBook.getJudulBook());
        cv.put(KEY_PENULIS, dataBook.getPenulis());
        cv.put(KEY_GENRE, dataBook.getGenre());
        cv.put(KEY_TGL, sdFormat.format(dataBook.getTanggal()));
        cv.put(KEY_ISI_SINOPSIS, dataBook.getSinopsisBook());

        db.insert(TABLE_BOOK, null, cv);
    }

    public void editBook (Book dataBook){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_GAMBAR, dataBook.getGambarBook());
        cv.put(KEY_PENULIS, dataBook.getPenulis());
        cv.put(KEY_GENRE, dataBook.getGenre());
        cv.put(KEY_TGL, sdFormat.format(dataBook.getTanggal()));
        cv.put(KEY_ISI_SINOPSIS, dataBook.getSinopsisBook());

        db.update(TABLE_BOOK, cv, KEY_ID_BOOK + "=?", new String[]{String.valueOf(dataBook.getIdBook())});
        db.close();
    }

    public void hapusBook (int idBook){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BOOK, KEY_ID_BOOK + "=?", new String[]{String.valueOf(idBook)});
        db.close();
    }

    public ArrayList<Book> getAllBook(){
        ArrayList<Book> dataBook = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BOOK;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr =  db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(5));
                } catch (ParseException er){
                    er.printStackTrace();
                }

                Book tempBook = new Book(
                        csr.getInt ( 0),
                        csr.getString( 1),
                        csr.getString( 2),
                        csr.getString( 3),
                        csr.getString( 4),
                        tempDate,
                        csr.getString( 6)
                );

                dataBook.add(tempBook);
            } while (csr.moveToNext());
        }
        return dataBook;
    }

    private String storeImagesFile(int id){
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputBookActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inialisasiBookAwal(SQLiteDatabase db) {
        int idBook = 0;
        Date tempDate = new Date();

        try {
            tempDate = sdFormat.parse("27/05/2020 06:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Book book1 = new Book(
                idBook,
                storeImagesFile(R.drawable.novel1),
                "Full Marks Hidden Marriage: Pick Up a Son, Get a Free Husband",
                "Jiong Jiong You Yao",
                "Romance",
                tempDate,
                "After five years, Ning Xi returned to the place that had pushed her away — home. \n" +
                        "With a sister who turned her parents against her and made her childhood sweetheart betray her, the odds seemed grey.\n" +
                        "However, five years abroad had changed her, and she came home to fulfil her childhood dream of becoming an actress. \n" +
                        "Despite her evil sister still out to get her, tables will be turned.\n" +
                        "One day, after falling into one of her sister's schemes, she saves an adorable kid and found herself staying at his house to help him come out of his shell. \n" +
                        "Slowly, his father Lu Tingxiao began falling for her. This was before they realised how their lives had been intertwined all this while without their knowledge."
        );
        tambahBook(book1, db);
        idBook++;

        try {
            tempDate = sdFormat.parse("27/05/2020 07:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Book book2 = new Book(
                idBook,
                storeImagesFile(R.drawable.novel2),
                "Release That Witch",
                "Er Mu",
                "Fantasy",
                tempDate,
                "Chen Yan travels through time, only to end up becoming an honorable prince in the Middle Ages of Europe. \n" +
                        "Yet this world was not quite as simple as he thought. \n" +
                        "Witches with magical powers abound, and fearsome wars between churches and kingdoms rage throughout the land.\n" +
                        "Roland, a prince regarded as hopeless by his own father and assigned to the worst fief, spends his time developing a poor and backward town into a strong and modern city, while fighting against his siblings for the throne and absolute control over the kingdom. \n" +
                        "Join Roland as he befriends and allies with witches and, through fighting and even farming, pushes back invaders from the realm of evil."
        );
        tambahBook(book2, db);
        idBook++;


        try {
            tempDate = sdFormat.parse("27/05/2020 08:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Book book3 = new Book(
                idBook,
                storeImagesFile(R.drawable.novel3),
                "True Martial World",
                "Cocooned Cow",
                "Fantasy",
                tempDate,
                "With the strongest experts from the 33 Skies the Human Emperor, Lin Ming, and his opponent, the Abyssal Demon King, were embroiled in a final battle. \n" +
                        "In the end, the Human Emperor destroyed the Abyssal World and killed the Abyssal Demon King. By then, a godly artifact, the mysterious purple card that had previously sealed the Abyssal Demon King, had long since disappeared into the space-time vortex, tunneling through infinite spacetime together with one of Lin Ming’s loved ones.\n" +
                        "In the vast wilderness, where martial arts was still slowly growing in its infancy, several peerless masters tried to find their path in the world of martial arts. \n" +
                        "A young adult named Yi Yun from modern Earth unwittingly stumbles into such a world and begins his journey with a purple card of unknown origin. \n" +
                        "This is a magnificent yet unknown true martial world! This is the story of a normal young adult and his adventures!! "
        );
        tambahBook(book3, db);
        idBook++;


        try {
            tempDate = sdFormat.parse("27/05/2020 09:22");
        } catch (ParseException er) {
            er.printStackTrace();
        }

        Book book4 = new Book(
                idBook,
                storeImagesFile(R.drawable.novel4),
                "Legend of Swordsman",
                "Mr. Money",
                "Fantasy",
                tempDate,
                "Jian Wushuang was reborn in adversity. \n" +
                        "In order to get his revenge, he began to cultivate Heavenly Creation Skill. \n" +
                        "With the help of the Heaven defying cultivation method, Jian Wushuang gradually grew into a peerless genius from an ordinary practitioner. \n" +
                        "With a sword in hand, no one is his match. Using his extraordinary Sword Principle, he killed all his opponents and eventually became number one Sword Master from time immemorial."
        );
        tambahBook(book4, db);
        idBook++;
    }
}
