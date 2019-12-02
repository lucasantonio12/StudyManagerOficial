package com.example.studymanageroficial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.studymanageroficial.Fragment.Tabs
import com.example.studymanageroficial.shared.SecurityPreferences
import com.example.studymanageroficial.viewlogin.LoginView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = SecurityPreferences(this)

        logado()

        supportActionBar?.setTitle("Study Manager")

        val pageAdapter = Tabs(supportFragmentManager)

        viewpager.adapter = pageAdapter

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position) {
                    0 -> {
                        tab.getTabAt(0)!!.setText("Disciplina")
                    }
                    1 -> {
                        tab.getTabAt(1)!!.setText("Tarefa")
                    }
                    else -> return
                }
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    fun logado(){
        if(sharedPreferences.getPreferences("LoginUser").equals("")){
            startActivity(Intent(this,LoginView::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.setPreferences("LoginUser","")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle item selection
        return when (item.itemId) {
            R.id.menu_sair -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

