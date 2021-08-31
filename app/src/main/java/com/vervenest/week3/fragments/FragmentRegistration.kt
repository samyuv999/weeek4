package com.vervenest.week3.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.vervenest.week3.MainActivity
import com.vervenest.week3.R
import com.vervenest.week3.UserInfo
import java.util.*
import java.util.regex.Pattern


class FragmentRegistration : Fragment() {
    private var userName: TextView? = null
    private var email: TextView? = null
    private var age: EditText? = null
    private var datePicker: EditText? = null
    private var timePicker: EditText? = null
    private var radioGroup: RadioGroup? = null
    private var radioButton: RadioButton? = null
    private var okToProceed: Button? = null
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private var mHour = 0
    private var mMinute = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        val b = arguments
        println("Bundle:oncreateview:$b")
        okToProceed = view.findViewById<View>(R.id.btnOk) as Button
            datePicker = view.findViewById<View>(R.id.dateOfBirth) as EditText
            timePicker = view.findViewById<View>(R.id.time) as EditText
        datePicker!!.setOnClickListener {
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth -> datePicker!!.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                mYear,
                mMonth,
                mDay
            )
            datePickerDialog.show()
        }
        timePicker!!.setOnClickListener {
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]
            val timePickerDialog = TimePickerDialog(
                context,
                { view, hourOfDay, minute -> timePicker!!.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                false
            )
            timePickerDialog.show()
        }
        okToProceed!!.setOnClickListener {
            userName = view.findViewById<View>(R.id.userName) as TextView
            email = view.findViewById<View>(R.id.emailId) as TextView
            radioGroup = view.findViewById(R.id.radioGroup)
            age = view.findViewById<View>(R.id.age) as EditText
            if (checkAllFields(view)) {
                radioButton = view.findViewById(radioGroup!!.checkedRadioButtonId)
                val user = UserInfo(
                    userName!!.text.toString(),
                    email!!.text.toString(),
                    radioButton!!.text.toString(),
                    age!!.text.toString(),
                    datePicker!!.text.toString(),
                    timePicker!!.text.toString()
                )
                var userList: MutableList<UserInfo?>? =
                    ArrayList<UserInfo?>()
                val b = arguments
                if (b != null) {
                    userList = b.getParcelableArrayList<UserInfo?>("userList")
                    userList!!.add(user)
                }
                val snackbar = Snackbar
                    .make(view, "Registration Successful......", Snackbar.LENGTH_LONG)
                snackbar.show()
                val ma = activity as MainActivity?
                ma!!.listView(userList!!)
            }
        }
        return view
    }

    private fun checkAllFields(view: View): Boolean {
        var noErrors = true
        if (userName!!.length() == 0 || userName!!.length() < 5 || userName!!.length() > 30) {
            userName!!.error = "Please Enter Valid User Name."
            noErrors = false
        }
        if (email!!.length() == 0 || !validateEmail()) {
            email!!.error = "Enter valid email id !"
            noErrors = false
        }
        if (radioGroup!!.checkedRadioButtonId == -1) {
            val snackbar = Snackbar
                .make(view, "Please Select Gender", Snackbar.LENGTH_LONG)
            snackbar.show()
            noErrors = false
        }
        return noErrors
    }

    fun validateEmail(): Boolean {
        val emailStr = email!!.text.toString()
        val pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(emailStr)
        return if (matcher.matches()) {
            email!!.error = null
            true
        } else {
            email!!.error = "Enter valid email id !"
            false
        }
    }

    companion object {
        private const val EMAIL_REGEX =
            "[a-zA-Z0-9\\.\\_\\&\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})"
    }

}