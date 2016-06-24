package com.qf.kanfang.db.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.qf.kanfang.db.MyDBSQLite;

/**
 * Created by Administrator on 2016/6/1.
 */
public class HouseDBUtils {

    private SQLiteDatabase db;
    private String[] column;
    private String tableName;
    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";

    public HouseDBUtils(Context context) {

        MyDBSQLite myDBSQLite = new MyDBSQLite(context);
        this.db = myDBSQLite.getReadableDatabase();
        column = MyDBSQLite.HouseItem.getColumn();
        tableName = MyDBSQLite.HouseItem.getTableName();
    }

    public HouseDBUtils(Context context, int tableId) {

        MyDBSQLite myDBSQLite = new MyDBSQLite(context);
        this.db = myDBSQLite.getReadableDatabase();
        column = MyDBSQLite.HouseItem.getColumn();
        switch (tableId) {
            case 0:
                tableName = MyDBSQLite.HouseItem.getTableName();
                break;
        }

    }

    public long add(String[] str) {

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < column.length; i++) {
//            if(i == 0){
//                contentValues.put("_id", str[i]);
//            }else
                contentValues.put(column[i], str[i]);
            Log.d("LIN", "tea 63 : " + column[0]);
        }
        return db.insert(tableName, null, contentValues);
    }

    public int delete(int id) {
        if (id > 0)
            return db.delete(tableName, column[0] +
                    " = " + id, null);
        else
            return db.delete(tableName, null, null);
    }

    public int delete() {
        return delete(-1);
    }

    public int swop(String[] str) {

        ContentValues contentValues = new ContentValues();

        for (int i = 1; i < column.length; i++) {
            if (str[i] != null) {
                contentValues.put(column[i], str[i]);
            }
        }

        return db.update(tableName, contentValues, column[0] +
                " = " + str[0], null);
    }

    public Cursor search() {

        return search(0, null, 0, false);
    }

    public Cursor search(String id) {

        return db.query(tableName, column,"_id = '" + id + "' ", null, null, null, null);
    }

    // 搜索条件：1，条件对应列 2，条件对应值 3，需要排序的列下标 4，fasle升序，true降序
    public Cursor search(int item, String statement, int itemOrder, boolean order) {

        if (statement == null) {
            if (order)
                return db.query(tableName, column, null,
                        null, null, null, column[itemOrder] + " " + ORDER_DESC);
            else
                return db.query(tableName, column, null,
                        null, null, null, column[itemOrder] + " " + ORDER_ASC);
        }

        if (order)
            return db.query(tableName, column, column[item] + " like '%" + statement + "%'",
                    null, null, null, column[itemOrder] + " " + ORDER_DESC);
        else
            return db.query(tableName, column, column[item] + " like '%" + statement + "%'",
                    null, null, null, column[itemOrder] + " " + ORDER_ASC);
    }

    public boolean contain(String id) {

        Cursor cursor = search(id);
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }
}
