package com.vervenest.week3

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vervenest.week3.fragments.FragmentListView
import com.vervenest.week3.fragments.FragmentProfileView
import com.vervenest.week3.fragments.FragmentRegistration
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val connectionmanager= this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activenetwrok: NetworkInfo?=connectionmanager.activeNetworkInfo
        val isconnected:Boolean=activenetwrok?.isConnectedOrConnecting==true
        if(isconnected){
            Toast.makeText(this, "Connected to the internet", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "You are offline!!!!", Toast.LENGTH_SHORT).show()
        }
        listView()
    }

    private fun listView() {
        supportFragmentManager.beginTransaction().add(R.id.frame1, FragmentListView())
            .commit()
    }

    fun listView(userList: List<UserInfo?>) {
        val flv = FragmentListView()
        val b = Bundle()
        b.putParcelableArrayList("userList", userList as ArrayList<out Parcelable?>)
        flv.arguments = b
        supportFragmentManager.beginTransaction().replace(R.id.frame1, flv)
            .commit()
    }

    fun display(user: UserInfo?, list: List<UserInfo?>?) {
        val fdv = FragmentProfileView()
        val b = Bundle()
        b.putParcelable("user", user)
        b.putParcelableArrayList("userList", list as ArrayList<out Parcelable?>?)
        fdv.arguments = b
        supportFragmentManager.beginTransaction().replace(R.id.frame1, fdv)
            .commit()
    }

    fun replace(list: List<UserInfo?>?) {
        val fr = FragmentRegistration()
        val b = Bundle()
        b.putParcelableArrayList("userList", list as ArrayList<out Parcelable?>?)
        fr.arguments = b
        supportFragmentManager.beginTransaction().replace(R.id.frame1, fr)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

}