package com.example.after_hours;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    String donor_email, donor_phno, donor_name, donor_pass, ngo_email, ngo_phno, ngo_name, ngo_pass;
    String res;

    public DBHelper(Context context) {
        super(context, "After_Hours.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table donor(donor_email text primary key,donor_phno text,donor_name text,donor_pass text)");
        db.execSQL("Create Table NGO(ngo_email text primary key,ngo_phno text,ngo_name text,ngo_pass text)");
        db.execSQL("Create Table donation(d_id integer primary key autoincrement,d_status text,d_receipient text,donor_id text,NGO_id text,d_quantity integer,d_type integer,d_deadline Timestamp,d_submitdate timestamp,d_acceptdate timestamp,d_desc text,d_img text)");
        db.execSQL("Create Table Contact(name text,email text,message text,type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists donor");
        db.execSQL("Drop table if exists donation");
        db.execSQL("Drop table if exists NGO");
        onCreate(db);
    }

    public int addNewDonor(String email, String ph_no, String name, String pass) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO DONOR VALUES ('" + email + "','" + ph_no + "','" + name + "','" + pass + "')";
            db.execSQL(query);
            db.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int addNewNGO(String email, String ph_no, String name, String pass) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO NGO VALUES ('" + email + "','" + ph_no + "','" + name + "','" + pass + "')";
            db.execSQL(query);
            db.close();
            return 0;
        }
        catch (Exception e) {
            return -1;
        }
    }
    public int check_donor_login(String email,String password){
        try {
            String[] columns = {
                    "donor_email"
            };
            SQLiteDatabase db = this.getReadableDatabase();
            // selection criteria
            String selection = "donor_email" + " = ?" + " AND " + "donor_pass" + " = ?";
            // selection arguments
            String[] selectionArgs = {email, password};
            // query user table with conditions
            Cursor cursor = db.query("donor", //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return 1;
            }
            return 0;
        }
        catch(Exception e){
            return -1;
        }
    }
    public int check_ngo_login(String email,String password){
        try {
            String[] columns = {
                    "ngo_email"
            };
            SQLiteDatabase db = this.getReadableDatabase();
            // selection criteria
            String selection = "ngo_email" + " = ?" + " AND " + "ngo_pass" + " = ?";
            // selection arguments
            String[] selectionArgs = {email, password};
            // query user table with conditions
            Cursor cursor = db.query("ngo", //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return 1;
            }
            return 0;
        }
        catch(Exception e){
            return -1;
        }
    }

    public String make_a_donation(String email, String type, String quantity, String deadline, String desc){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String d="datetime('now','+"+deadline+" hours','localtime')";
            String now="datetime('now','localtime')";
            String e = "'"+email+"'";
            String t = "'"+type+"'";
            String q = "'"+quantity+"'";
            String de = "'"+desc+"'";

            String query = "INSERT INTO DONATION(DONOR_ID,D_TYPE,D_QUANTITY,D_DEADLINE,D_SUBMITDATE,D_DESC) VALUES ("+e+","+t+","+q+","+d+","+now+","+de+")";
            db.execSQL(query);
            db.close();
            return "0";
        }
        catch (Exception e) {
            return "-1";
        }
    }

    public ArrayList<donations> get_donations(){
        try {
            String[] columns = {
                    "d_id","donor_id","d_quantity","d_type","d_deadline","d_submitdate","d_desc"
            };
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT d_id,donor_id,d_quantity,d_type,d_deadline,d_submitdate,d_desc FROM donation WHERE NGO_id is null AND d_deadline>datetime('now','localtime')",null);
            ArrayList<donations> available= new ArrayList<>();
            while(cursor.moveToNext()){
                donations d= new donations();
                d.d_id=cursor.getString(0);
                d.donor_id=cursor.getString(1);
                d.d_quantity=cursor.getString(2);
                d.d_type=cursor.getString(3);
                d.d_deadline=cursor.getString(4);
                d.d_submit=cursor.getString(5);
                d.d_desc=cursor.getString(6);
                available.add(d);
            }
            cursor.close();
            db.close();
            return available;
        }
        catch(Exception e){
            return null;
        }
    }

    public String take_a_donation(String email,String d_id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String now="datetime('now','localtime')";
            String e = "'"+email+"'";
            String query = "UPDATE DONATION SET NGO_ID= "+e+",D_ACCEPTDATE="+now+" where D_ID="+d_id;
            db.execSQL(query);
            db.close();
            return "0";
        }
        catch (Exception e) {
            return "-1";
        }
    }
    public String take_contact(String email, String name, String message,String type){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String e = "'"+email+"'";
            String n = "'"+name+"'";
            String m = "'"+message+"'";
            String t = "'"+type+"'";
            String query = "INSERT INTO CONTACT VALUES ("+e+","+n+","+m+","+t+")";
            db.execSQL(query);
            db.close();
            return "0";
        }
        catch (Exception e) {
            return "-1";
        }
    }
    public ArrayList<donations> get_ngo_history(String email){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT d_id,donor_id,d_quantity,d_type,d_deadline,d_submitdate,d_desc FROM donation WHERE NGO_id is ?",new String[] {email});
            ArrayList<donations> available= new ArrayList<>();
            while(cursor.moveToNext()){
                donations d= new donations();
                d.d_id=cursor.getString(0);
                d.donor_id=cursor.getString(1);
                d.d_quantity=cursor.getString(2);
                d.d_type=cursor.getString(3);
                d.d_deadline=cursor.getString(4);
                d.d_submit=cursor.getString(5);
                d.d_desc=cursor.getString(6);
                available.add(d);
            }
            cursor.close();
            db.close();
            return available;
        }
        catch(Exception e){
            return null;
        }
    }
    public ArrayList<donations> get_donor_history(String email){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT d_id,donor_id,d_quantity,d_type,d_deadline,d_submitdate,d_desc FROM donation WHERE donor_id is ?",new String[] {email});
            ArrayList<donations> available= new ArrayList<>();
            while(cursor.moveToNext()){
                donations d= new donations();
                d.d_id=cursor.getString(0);
                d.donor_id=cursor.getString(1);
                d.d_quantity=cursor.getString(2);
                d.d_type=cursor.getString(3);
                d.d_deadline=cursor.getString(4);
                d.d_submit=cursor.getString(5);
                d.d_desc=cursor.getString(6);
                available.add(d);
            }
            cursor.close();
            db.close();
            return available;
        }
        catch(Exception e){
            return null;
        }
    }

    public int check_feedback(String email){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT d_id FROM donation WHERE d_status is null and datetime('now','-24 hours','localtime')>d_acceptdate and NGO_id=?", new String[]{email});
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return 1;
            }
            return 0;
        }
        catch(Exception e){
            return -1;
        }
    }

    public ArrayList<donations> get_feedback_donation(String email){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT d_id,donor_id,d_quantity,d_type,d_deadline,d_submitdate,d_desc,d_acceptdate FROM donation WHERE d_status is null and datetime('now','-24 hours','localtime')>d_acceptdate and NGO_id=?",new String[] {email});
            ArrayList<donations> available= new ArrayList<>();
            while(cursor.moveToNext()){
                donations d= new donations();
                d.d_id=cursor.getString(0);
                d.donor_id=cursor.getString(1);
                d.d_quantity=cursor.getString(2);
                d.d_type=cursor.getString(3);
                d.d_deadline=cursor.getString(4);
                d.d_submit=cursor.getString(5);
                d.d_desc=cursor.getString(6);
                d.d_acceptdate=cursor.getString(7);
                available.add(d);
            }
            cursor.close();
            db.close();
            return available;
        }
        catch(Exception e){
            return null;
        }
    }

    public String update_feedback(String d_id,String status,String recipient){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String s = "'"+status+"'";
            String r="'"+recipient+"'";
            String query = "UPDATE DONATION SET d_status= "+s+",d_receipient="+r+" where D_ID="+d_id;
            db.execSQL(query);
            db.close();
            return "1";
        }
        catch (Exception e) {
            return "-1";
        }
    }
}