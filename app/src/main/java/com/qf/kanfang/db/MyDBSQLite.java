package com.qf.kanfang.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyDBSQLite extends SQLiteOpenHelper {

    public static String NAME = "house";
    public static int VERSION = 1;
    private String[] column;

    public MyDBSQLite(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        column = HouseItem.getColumn();

        //“首页新闻”数据表
        createTable(db,HouseItem.getTableName());
    }

    private void createTable(SQLiteDatabase db,String tableName) {

        StringBuffer sql = new StringBuffer();

        sql.append("create table " + tableName + " ( ");
        sql.append(column[0] + " integer primary key autoincrement ");
        for (int i = 1; i < column.length; i++) {
            sql.append(" , " + column[i]);
        }
        sql.append(" ) ; ");
        db.execSQL(sql + "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >=  newVersion) {
            return;
        }
        String sql = "drop table if exists " + HouseItem.getTableName();

        db.execSQL(sql);
        onCreate(db);

    }


    public static class HouseItem {
        private static String TABLE_NAME = "item";
        private static String COMMENTID = "_id";
        private static String ID = "id";
        private static String TYPE = "type";
        private static String TITLE = "title";
        private static String SUMMARY = "summary";
        private static String THUMBNAIL = "thumbnail";
        private static String GROUPTHUMBNAIL = "groupthumbnail";

        public static String[] getColumn() {
            return new String[]{COMMENTID, ID, TYPE, TITLE,
                    SUMMARY, THUMBNAIL, GROUPTHUMBNAIL};
        }

        public static String getTableName() {
            return TABLE_NAME;
        }

    }
}
