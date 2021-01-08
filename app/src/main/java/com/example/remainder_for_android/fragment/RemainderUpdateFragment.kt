package com.example.remainder_for_android.fragment


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.remainder_for_android.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RemainderUpdateFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val remainderListFragment = fragmentManager?.findFragmentById(R.id.fragmentRemainderList)
        // 画面のサイズを判定
        if (remainderListFragment == null){
            _isLayoutXLarge = false
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_remainder, container, false)

        val contents: String?
        val date:String?
        val time:String?
        val tag :String?
        val complete: Boolean?
        if(_isLayoutXLarge) {
            // 隣のフラグメントから埋め込まれた引数を反映
            val extras = arguments
            contents = extras!!.getString("contents")
            date = extras!!.getString("date")
            time = extras!!.getString("time")
            tag = extras!!.getString("tag")
            complete = extras!!.getBoolean("complete")
        } else {
            // 前画面から受け取ったデータを画面に反映
            Log.d("DEBUG", ">>>>test")
            contents = activity?.intent?.getStringExtra("contents")
            date = activity?.intent?.getStringExtra("date")
            time = activity?.intent?.getStringExtra("time")
            tag = activity?.intent?.getStringExtra("tag")
            complete = activity?.intent?.getBooleanExtra("complete", false)
        }
        // パラメータを画面に反映
        val contentsEditText = view.findViewById<EditText>(R.id.contents)
        val dateEditText = view.findViewById<TextView>(R.id.date)
        val timeEditText = view.findViewById<TextView>(R.id.time)
        val tagEditText = view.findViewById<Spinner>(R.id.tag_spinner)
        val completeEditText = view.findViewById<Switch>(R.id.complete)
        contentsEditText.setText(contents)
        dateEditText.setText(date)
        timeEditText.setText(time)
        tagEditText.setSelection(0)
        completeEditText.isChecked = complete!!

        // リスナクラスを設定
        val btClear = view.findViewById<Button>(R.id.btClear)
        val btCancel = view.findViewById<Button>(R.id.btCancel)
        val btSubmit = view.findViewById<Button>(R.id.btSubmit)

        val createActionInstance = createActionListner()
        arrayOf(btSubmit, btCancel, btClear).map { it.setOnClickListener(createActionInstance) }


        return view
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val str = String.format(Locale.US, "%d/%d/%d", year, monthOfYear+1, dayOfMonth)
        val dateTextView = view.findViewById<TextView>(R.id.date)
        dateTextView.setText(str)
    }


    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerDialogFragment()

        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val str = String.format(Locale.US, "%d:%d", hourOfDay, minute)
        val timeTextView = view.findViewById<TextView>(R.id.time)
        timeTextView.text = str
    }


    fun showTimePickerDialog(v: View) {
        val newFragment = TimePickerDialogFragment()
        newFragment.show(requireActivity().supportFragmentManager, "timePicker")
    }


    private inner class createActionListner:View.OnClickListener {
        override fun onClick(view:View) {
            Log.d("DEBUG", ">>>>>click!!!!")
            val contents = view.findViewById<EditText>(R.id.contents)
            val date = view.findViewById<TextView>(R.id.date)
            val time= view.findViewById<TextView>(R.id.time)
            val tag = view.findViewById<Spinner>(R.id.tag_spinner)
            val complete = view.findViewById<Switch>(R.id.complete)

            when(view.id) {
                R.id.btSubmit -> {
                    if (_isLayoutXLarge) {
                        val transaction = parentFragmentManager?.beginTransaction()
                        transaction.remove(this@RemainderUpdateFragment)
                    } else {
                        activity?.finish()
                    }
                }
                R.id.btCancel -> {
                    if (_isLayoutXLarge) {
                        val transaction = parentFragmentManager?.beginTransaction()
                        transaction.remove(this@RemainderUpdateFragment)
                    } else {
                        activity?.finish()
                    }
                }
                R.id.btClear -> {
                    contents.setText("")
                    date.setText("")
                    time.setText("")
                    tag.setSelection(0)
                    complete.isChecked=false
                }
            }
        }
    }


}
