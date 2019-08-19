package com.bikloo.berpandroid.DataBase

import android.content.ContentValues
import android.content.Context
import com.bikloo.berpandroid.Classes.Owner
import com.bikloo.berpandroid.Classes.User
import java.util.ArrayList


class DBUser(context: Context) {
    companion object {
        val TABLE_NAME = "tblUser"
        val USER_ID = "userid"
        val USER_EMAIL = "useremail"
        val USER_FULL_NAME = "userfullname"
        val USER_PASSWORD = "userpass"
        val USER_TYPE = "usertype"
        val USER_ADDRESS = "useraddress"
    }

    private val dbHelper: DBHelper
    init {
        dbHelper = DBHelper(context)
    }

    fun insert(user: Owner) {
        val db = dbHelper.writableDatabase

        val cv = ContentValues()
        cv.put(USER_ID, user.userId)
        cv.put(USER_EMAIL, user.email)
        cv.put(USER_FULL_NAME,user.fullName)
        cv.put(USER_PASSWORD, user.password)
        cv.put(USER_TYPE,user.userType.toString())
        cv.put(USER_ADDRESS,user.address)
        db.insert(TABLE_NAME, null, cv)
        db.close()

    }
    fun insert(userID: Int,user: Owner) {
        val db = dbHelper.writableDatabase

        val cv = ContentValues()
        cv.put(USER_ID, userID)
        cv.put(USER_EMAIL, user.email)
        cv.put(USER_FULL_NAME,user.fullName)
        cv.put(USER_PASSWORD, user.password)
        cv.put(USER_TYPE,user.userType.toString())
        cv.put(USER_ADDRESS,user.address)
        db.insert(TABLE_NAME, null, cv)
        db.close()

    }

    fun update(user: Owner) {
        val db = dbHelper.writableDatabase

        val cv = ContentValues()
        cv.put(USER_ID, user.userId)
        cv.put(USER_EMAIL, user.email)
        cv.put(USER_FULL_NAME,user.fullName)
        cv.put(USER_PASSWORD, user.password)
        cv.put(USER_TYPE,user.userType.toString())
        cv.put(USER_ADDRESS,user.address)

        db.update(
            TABLE_NAME, cv,
            "$USER_ID=?",
            arrayOf(user.userId.toString())
        )

        db.close()
    }


    val allUsers: ArrayList<Owner>
        get()
        {
            val db = dbHelper.readableDatabase
            val mCursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

            val mUsers = ArrayList<Owner>()
            if (mCursor != null) {
                if (mCursor.count != 0) {
                    mCursor.moveToFirst()
                    while (!mCursor.isAfterLast) {
                        val mUser = Owner()
                        mUser.userId=Integer.parseInt(mCursor.getString(0))
                        mUser.email=mCursor.getString(1)
                        mUser.fullName=mCursor.getString(2)
                        mUser.password=mCursor.getString(3)
                        val userType=mCursor.getString(4)
                        if(userType.equals("Owner"))
                        {
                            mUser.userType = User.UserType.Owner
                        }
                        else
                        {
                            mUser.userType = User.UserType.Employee
                        }
                        mUser.address=mCursor.getString(5)
                        mUsers.add(mUser)
                        mCursor.moveToNext()
                    }
                }
            }

            db.close()
            return mUsers
        }

    fun updateByEmail(user: User) {
        val db = dbHelper.writableDatabase

        val cv = ContentValues()
        cv.put(USER_ID, user.userId)
        cv.put(USER_EMAIL, user.email)
        cv.put(USER_FULL_NAME,user.fullName)
        cv.put(USER_PASSWORD, user.password)
        cv.put(USER_TYPE,user.userType.toString())
        cv.put(USER_ADDRESS,user.address)

        db.update(
            TABLE_NAME, cv,
            "$USER_EMAIL=?",
            arrayOf(user.email)
        )

        db.close()

    }

    fun deleteByID(userID: Int) {
        val db = dbHelper.writableDatabase
        db.delete(
            TABLE_NAME, "$USER_ID=?",
            arrayOf(userID.toString())
        )
        db.close()
    }

    fun deleteByEmail(studentEmail: String) {
        val db = dbHelper.writableDatabase
        db.delete(
            TABLE_NAME, "$USER_EMAIL=?",
            arrayOf(studentEmail)
        )
        db.close()
    }

    fun getUserByEmail(userEmail: String): ArrayList<User> {
        val db = dbHelper.readableDatabase
        val mCursor = db.query(
            TABLE_NAME, null,
            "$USER_EMAIL=?", arrayOf(userEmail), null, null, null, null
        )

        val mUsers = ArrayList<User>()
        if (mCursor != null) {
            if (mCursor.count != 0) {
                mCursor.moveToFirst()
                while (!mCursor.isAfterLast) {
                    val mUser = User()
                    mUser.userId=Integer.parseInt(mCursor.getString(0))
                    mUser.email=mCursor.getString(1)
                    mUser.fullName=mCursor.getString(2)
                    mUser.password=mCursor.getString(3)
                    val userType=mCursor.getString(4)
                    if(userType.equals("Owner"))
                    {
                        mUser.userType = User.UserType.Owner
                    }
                    else
                    {
                        mUser.userType = User.UserType.Employee
                    }
                    mUser.address=mCursor.getString(5)

                    mUsers.add(mUser)

                    mCursor.moveToNext()
                }
            }
        }

        db.close()
        return mUsers
    }


}