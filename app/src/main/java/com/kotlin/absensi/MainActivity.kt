package com.kotlin.absensi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.kotlin.absensi.utils.SessionLogin
import com.kotlin.absensi.view.AbsenActivity
import com.kotlin.absensi.view.HistoryActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var strTitle: String
    lateinit var session: SessionLogin

    // menyimpan session login
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitLayout()
    }

    private fun setInitLayout() {
        session = SessionLogin(this)
        session.checkLogin()

        // membuat absen masuk dengan menggunakan setOneClickListener
        cvAbsenMasuk.setOnClickListener {
            strTitle = "Absen Masuk"
            val intent = Intent(this@MainActivity, AbsenActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)

            // melihat history/riwayat dari inputan menggunakan setOneClickListener
            cvHistory.setOnClickListener {
                val intent = Intent(this@MainActivity, HistoryActivity::class.java)
                startActivity(intent)
            }

            // Tombol keluar dari aplikasi menggunakan setOneClickListener
            imageLogout.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                // Pesan pop up alert
                builder.setMessage("Yakin Anda ingin Logout?")
                builder.setCancelable(true)

                // Jika user memilih "Batal" maka yang akan dijalankan
                builder.setNegativeButton("Batal") { dialog, which -> dialog.cancel() }

                // Jika user memilih "Ya" maka ini yang akan dijalankan
                builder.setPositiveButton("Ya") { dialog, which ->
                    session.logoutUser()
                    finishAffinity()
                }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }
}