package com.cieslak.lab05

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val _ID = "_id"
const val DEBTOR_NAME = "name"
const val DEBTOR_PHONE = "phone"
const val DEBTOR_PHOTO_URI = "photo_uri"
const val TABLE_NAME = "debtors"
val columns = arrayOf(_ID, DEBTOR_NAME, DEBTOR_PHONE, DEBTOR_PHOTO_URI)
private const val NAME = "debtors_db"
private const val VERSION = 1
private const val CREATE_CMD = ("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
+ DEBTOR_NAME + " TEXT NOT NULL, "
+ DEBTOR_PHONE + " TEXT NOT NULL, "
+ DEBTOR_PHOTO_URI + " TEXT );")
class DebtorsData(context: Context): SQLiteOpenHelper(context, NAME, null, VERSION) {
    var context = context
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_CMD);
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun deleteDatabase(){
        context.deleteDatabase(NAME)
    }
    fun getAllDebtorsList() :MutableList<Debtor> {
        val debtors: MutableList<Debtor> = ArrayList()
        val cursor: Cursor = readableDatabase.query(TABLE_NAME, null, null, null, null, null, _ID)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            var idx: Int = cursor.getColumnIndex(DEBTOR_NAME)
            var name = cursor.getString(idx)
            idx = cursor.getColumnIndex(DEBTOR_PHONE)
            var phone = cursor.getString(idx)
            idx = cursor.getColumnIndex(DEBTOR_PHOTO_URI)
            var photoURI = cursor.getString(idx)
            idx = cursor.getColumnIndex(_ID)
            var id = cursor.getInt(idx)
            debtors.add(Debtor(id, name, phone, photoURI))
            cursor.moveToNext()
        }
        cursor.close()
        return debtors
    }
}