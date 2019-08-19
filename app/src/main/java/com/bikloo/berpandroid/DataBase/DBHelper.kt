package com.bikloo.berpandroid.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper
{
    constructor(context: Context?) : super(context, DB_NAME, null, DB_VERSION)

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {

        val UserTable = ("CREATE TABLE " + DBUser.TABLE_NAME + "(" + DBUser.USER_ID + " INT," + DBUser.USER_EMAIL + " TEXT," + DBUser.USER_FULL_NAME + " TEXT," + DBUser.USER_PASSWORD + " TEXT," + DBUser.USER_TYPE + " TEXT," + DBUser.USER_ADDRESS + " TEXT)")

        sqLiteDatabase.execSQL(UserTable)
    }
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL("DROP TABLE " + DBUser.TABLE_NAME)
        onCreate(sqLiteDatabase)
    }

    companion object {
        private val DB_NAME = "dbUser"
        private val DB_VERSION = 1
    }
}