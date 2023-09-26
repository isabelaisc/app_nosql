package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.model.CourseModel;
import java.sql.SQLData;

class DBHandler {
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(db:SQLDatabase) {
            val query = ("CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME_COL + " TEXT,"
                    + DURATION_COL + " TEXT,"
                    + DESCRIPTION_COL + " TEXT,"
                    + TRACKS_COL + "TEXT)")
            db.execSQL(query)
        }

        fun addNewCourse(
                courseName: String?,
                courseDuration: String?,
                courseDescription: String?,
                courseTracks: String?
        ) {
            val db = this.writableDatabase
            val values = ContentValues()

            values.put(NAME_COL, courseName)
            values.put(DURATION_COL, courseDuration)
            values.put(DESCRIPTION_COL, courseDescription)
            values.put(TRACKS_COL, courseTracks)

            db.insert(TABLE_NAME, null, values)
            db.close()
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }

        companion object {
            private const val DB_NAME = "coursedb"
            private const val DB_VERSION = 1
            private const val TABLE_NAME = "mycourses"
            private const val ID_COL = "id"
            private const val NAME_COL = "name"
            private const val DURATION_COL = "duration"
            private const val DESCRIPTION_COL = "description"
            private const val TRACKS_COL = "tracks"
        }

        fun readCourses(): ArrayList<CourseModel>? {
            val db = this.readableDatabase
            val cursorCourses: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            val courseModelArrayList: ArrayList<courseModel> = ArrayList()

            if (cursorCourses.moveToFirst()) {
                do {
                    courseModelArrayList.add(
                        CourseModel(
                           cursorCourses.getString(1),
                           cursorCourses.getString(4),
                           cursorCourses.getString(2),
                           cursorCourses.getString(3)
                    )
                )
            } while (cursorCourses.moveToNext())
        }
        cursorCourses.close()
        return courseModelArrayList
    }
}
