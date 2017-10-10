package com.example.leeyenn.workrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by leeyenn on 30-09-17.
 * DBHandler to handle database CRUD
 */

public class RecordDBHandler extends SQLiteOpenHelper{

    //Variables declaration

    //Database Version
    //If database schema is changed, increment the database version.
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "WorkRecord.db";

    //Table Name
    private static final String TABLE_NAME = "workRecordInfor";

    //Table columns
    private static final String COLUMN_RECORDID = "recordID";
    private static final String COLUMN_TRIPDATE = "tripDate";
    private static final String COLUMN_TRIPTIME = "tripTime";
    private static final String COLUMN_COMPANYNAME = "companyName";
    private static final String COLUMN_COMPANYLOCATION = "companyLocation";
    private static final String COLUMN_COMPANYDOOR = "companyDoor";
    private static final String COLUMN_TYPEOFRUBBISH = "typeOfRubbish";
    private static final String COLUMN_NUMOFTRIP = "numOfTrip";
    private static final String COLUMN_TRIPPRICE = "tripPrice";

    //SQL entries
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_RECORDID + " INTEGER PRIMARY KEY," +
                    COLUMN_TRIPDATE + " TEXT," + COLUMN_TRIPTIME + " TEXT," + COLUMN_COMPANYNAME + " TEXT," + COLUMN_COMPANYLOCATION + " TEXT," +
                    COLUMN_COMPANYDOOR + " TEXT," + COLUMN_TYPEOFRUBBISH + " TEXT, " + COLUMN_NUMOFTRIP + " INTEGER," +
                    COLUMN_TRIPPRICE + " REAL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;



    public RecordDBHandler(Context context){
        //Initialize the parent class first
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Run create script
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Cache for online data. It simply discard data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    //CRUD Function
    //Create
    public void createRecord(Record record) {
        //Write to db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Insert parameters
        values.put(COLUMN_TRIPDATE, record.getdTripDate());
        values.put(COLUMN_TRIPTIME, record.getdTripTime());
        values.put(COLUMN_COMPANYNAME, record.getsCompanyName());
        values.put(COLUMN_COMPANYLOCATION, record.getsCompanyLocation());
        values.put(COLUMN_COMPANYDOOR, record.getsCompanyDoor());
        values.put(COLUMN_TYPEOFRUBBISH, record.getsTypeOfRubbish());
        values.put(COLUMN_NUMOFTRIP, record.getIntNumOfTrip());
        values.put(COLUMN_TRIPPRICE, record.getfTripPrice());

        //Inserting rows
        db.insert(TABLE_NAME, null, values);

        //Close db connection
        db.close();
    }

    //Read record by specific ID
    public Record getRecordById(int recordID){
        //Read to db
        SQLiteDatabase db = this.getReadableDatabase();

        //Retrieve record
        //query( tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy );
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_RECORDID + "=?", new String[] { String.valueOf(recordID) },
                null, null, null, null);

        //If there is data
        if (cursor != null){
            //Go to the first record
            cursor.moveToFirst();
        }

        //Store the data into object
        Record record = new Record(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getFloat(8));

        return record;
    }

    //Read all record
    public List<Record> getAllRecords() {
        List<Record> recordList = new ArrayList<Record>();

        //Select all query
        String selectQuery = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Loop thru all records and add to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setIntRecordID(cursor.getInt(0));
                record.setdTripDate(cursor.getString(1));
                record.setdTripTime(cursor.getString(2));
                record.setsCompanyName(cursor.getString(3));
                record.setsCompanyLocation(cursor.getString(4));
                record.setsCompanyDoor(cursor.getString(5));
                record.setsTypeOfRubbish(cursor.getString(6));
                record.setIntNumOfTrip(cursor.getInt(7));
                record.setfTripPrice(cursor.getFloat(8));
                recordList.add(record);
            } while (cursor.moveToNext());
        }
        return recordList;
    }

    //Update
    public int updateRecord(Record record){
        //Get write access
        SQLiteDatabase db = this.getWritableDatabase();

        //Insert parameters
        ContentValues values = new ContentValues();
        values.put(COLUMN_TRIPDATE, record.getdTripDate());
        values.put(COLUMN_TRIPTIME, record.getdTripTime());
        values.put(COLUMN_COMPANYNAME, record.getsCompanyName());
        values.put(COLUMN_COMPANYLOCATION, record.getsCompanyLocation());
        values.put(COLUMN_COMPANYDOOR, record.getsCompanyDoor());
        values.put(COLUMN_TYPEOFRUBBISH, record.getsTypeOfRubbish());
        values.put(COLUMN_NUMOFTRIP, record.getIntNumOfTrip());
        values.put(COLUMN_TRIPPRICE, record.getfTripPrice());

        //Update query
        return db.update(TABLE_NAME, values, COLUMN_RECORDID + " = ?", new String[]{String.valueOf(record.getIntRecordID())});
    }

    //Delete Record
    public void deleteRecord(Record record){
        //Get write access
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_RECORDID + " = ?", new String[]{ String.valueOf(record.getIntRecordID()) });
        db.close();
    }





}
