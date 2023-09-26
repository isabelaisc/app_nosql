package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalProvider
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.myapplication.ui.theme.Purple40
import com.example.myapplication.ui.theme.PurpleGrey40
import java.nio.file.WatchEvent
import java.util.Calendar
import java.util.Date

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout) {
            MyApplicationTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),

                                title = {
                                    Text(
                                        text = "GFG",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        color = Color.White
                                    )
                                })
                        }) {
                        addDataToDatabase<Any>(LocalContext.current)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <DBHandler> addDataToDatabase(
    context: Context
) {
    val activity = context as Activity
    val courseName = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseDuration = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseTracks = remember {
        mutableStateOf(TextFieldValue())
    }
    val courseDescription = remember {
        mutableStateOf(TextFieldValue())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        var dbHandler: DBHandler = DBHandler(context)

        Text(
            text = "SQlite Database in Android",
            color = PurpleGrey40, fontSize = 20.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseName.value,
            onValueChange = { courseName.value = it },
            placeholder = { Text(text = "Enter your course name") },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseDuration.value,
            onValueChange = { courseDuration.value = it },
            placeholder = { Text(text = "Enter your course duration") },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseTracks.value,
            onValueChange = { courseTracks.value = it },
            placeholder = { Text(text = "Enter your course tracks") },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = courseDescription.value,
            onValueChange = { courseDescription.value = it },
            placeholder = { Text(text = "Enter your course description") },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(15.dp))


        Button(onClick = {
            val i = Intent(context, ViewCourses::class.java)
            context.startActivity(i)
        }) {
            Text(text = "Read Courses to Database", color = color.White)
        }


        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your course duration") },
            singleLine = true
        )
    }
}


