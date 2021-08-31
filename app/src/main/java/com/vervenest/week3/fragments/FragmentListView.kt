package com.vervenest.week3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vervenest.week3.MainActivity
import com.vervenest.week3.R
import com.vervenest.week3.UserInfo
import com.vervenest.week3.UsersAdapter
import java.util.*


class FragmentListView : Fragment() {
    private var lv: ListView? = null
    private var fab: FloatingActionButton? = null
    private var list: ArrayList<UserInfo?>? = null
    private var usersAdapter: UsersAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_list_view, container, false)
        lv = v.findViewById(R.id.list1)
        fab = v.findViewById<View>(R.id.fab) as FloatingActionButton
        list = ArrayList<UserInfo?>()
        val b = arguments
        if (b != null) {
            list = b.getParcelableArrayList<UserInfo?>("userList")
        }

        usersAdapter = UsersAdapter(activity, list)
        lv!!.adapter = usersAdapter

        lv!!.onItemClickListener = OnItemClickListener { adapterView, view, position, id ->
            val user: UserInfo? = list!![position]
            val ma = activity as MainActivity?
            ma!!.display(user, list)
        }
        fab!!.setOnClickListener {
            val ma = activity as MainActivity?
            ma!!.replace(list)
        }
        return v
    }

}