package com.route.todoappc43gsunwed.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.view.isVisible
import com.route.todoappc43gsunwed.MainActivity
import com.route.todoappc43gsunwed.R
import com.route.todoappc43gsunwed.database.Task
import com.route.todoappc43gsunwed.database.TaskDatabase
import com.route.todoappc43gsunwed.databinding.FragmentEditTaskBinding
import com.route.todoappc43gsunwed.fragments.TaskListFragment.Companion.KEY_OF_TASK
import java.util.Calendar

class EditTaskFragment : Fragment() {

    lateinit var binding: FragmentEditTaskBinding
    private lateinit var calendar2: Calendar
    var task: Task? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task = arguments?.getSerializable(KEY_OF_TASK) as? Task
        Log.e("TAG", "title: ${task?.title}")
        Log.e("TAG", "description: ${task?.description}")
        calendar2 = Calendar.getInstance()

        binding.etTitle.setText(task?.title)
        binding.etDescriptionEditText.setText(task?.description)

        hideViews()
        showDatePickerDialog()
        arrowBack()
        saveChanges()


    }

    private fun arrowBack() {
        binding.btnArrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
    private fun hideViews(){
        (activity as MainActivity).binding.addFab.isVisible = false
        (activity as MainActivity).binding.todoBottomAppBar.isVisible = false
        (activity as MainActivity).binding.bottomAppBar.isVisible = false
    }
    private fun showDatePickerDialog(){
        binding.dateTextView.setOnClickListener {
            val onDateSet = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    datePicker: DatePicker?,
                    year: Int,
                    month: Int,
                    dayOfMonth: Int
                ) {

                    binding.dateTextView.text = "$dayOfMonth / ${month + 1} / $year"
                    calendar2.set(Calendar.YEAR, year)
                    calendar2.set(Calendar.MONTH, month)
                    calendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth)


                }
            }
            val datePicker = DatePickerDialog(
                requireContext(),
                R.style.CustomDatePickerDialog,
                onDateSet,
                calendar2.get(Calendar.YEAR),
                calendar2.get(Calendar.MONTH),
                calendar2.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }



    }

    fun saveChanges(){
            binding.saveChangesBtn.setOnClickListener {
                task?.title = binding.etTitle.text.toString()
                task?.description = binding.etDescriptionEditText.text.toString()
                task?.date = calendar2.time
                TaskDatabase.getInstance(requireContext()).getTaskDao().updateTask(task!!)
                parentFragmentManager.popBackStack()
            }
        }



}