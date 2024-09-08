package com.example.storingappsettings

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

//    lateinit var name: EditText
//    lateinit var pick_class: EditText
//    lateinit var pick_num: EditText

    val PREF_NAME = "perfs"
    val PREF_DARK_THEME = "dark_theme"
    val PREF_TITLE = "pref_title"
    val PREF_SIZE = "pref_size"

    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        name = findViewById(R.id.edit1)
//        pick_class = findViewById(R.id.edit2)
//        pick_num = findViewById(R.id.edit3)

        val preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
//        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false)
        if (useDarkTheme){
            setTheme(androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dark)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = findViewById<TextView>(R.id.textView)
        val isUpdatedTitle = preferences.getBoolean(PREF_TITLE, false)
        if (isUpdatedTitle) {
            title.text = getString(R.string.updated_title)
        } else {
            title.text = getString(R.string.original_title)
        }

        val body_text = findViewById<TextView>(R.id.textView2)
        val isLargeFont = preferences.getBoolean(PREF_SIZE, false)
        if (isLargeFont) {
            body_text.textSize = 28f
        } else {
            body_text.textSize = 14f
        }

        val toggle = findViewById<Switch>(R.id.switch1)
        toggle.isChecked = useDarkTheme
        toggle.setOnCheckedChangeListener{
            view, isChecked -> toggleTheme(isChecked)
        }

        val title_switch = findViewById<Switch>(R.id.titleSwitch)
        title_switch.isChecked = isUpdatedTitle
        title_switch.setOnCheckedChangeListener {
            view, isChecked ->
            val editor = preferences.edit()
            if (isChecked) {
                title.text = getString(R.string.updated_title)
                editor.putBoolean(PREF_TITLE, true)
            } else {
                title.text = getString(R.string.original_title)
                editor.putBoolean(PREF_TITLE, false)
            }
            editor.apply()
        }

        val text_size_increase = findViewById<Switch>(R.id.switch3)
        text_size_increase.isChecked = isLargeFont
        text_size_increase.setOnCheckedChangeListener {
            view, isChecked ->
            val  editor = preferences.edit()
            if (isChecked) {
                body_text.textSize = 28f
                editor.putBoolean(PREF_SIZE, true)
            } else {
                body_text.textSize = 14f
                editor.putBoolean(PREF_SIZE, false)
            }
            editor.apply()
        }

    }

    private fun toggleTheme(darkTheme: Boolean) {
        val editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit()

        editor.apply{
            putBoolean(PREF_DARK_THEME, darkTheme)
            apply()
        }

        val intent = intent
        finish()
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)

//        // look for the key value pairs
//        val key1 = SP.getString("name", "")
//        val key2 = SP.getString("pick_class", "")
//        val key3 = SP.getInt("pick_num", 0)
//
//        // assign them to the widjets
//        name!!.setText(key1)
//        pick_class.setText(key2)
//        pick_num!!.setText(key3.toString())

    }

    override fun onPause() {
        super.onPause()

        // should maintain the same name as e the one chosen for onResume()
        // opened in private mode for writing
        val SP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = SP.edit()

        // write to the file
//        myEdit.putString("name", name!!.text.toString())
//        myEdit.putString("pick_class", pick_class!!.text.toString())
//        myEdit.putInt("pick_num", pick_num!!.text.toString().toInt())
        // you could use  commit
        myEdit.apply()

    }
}