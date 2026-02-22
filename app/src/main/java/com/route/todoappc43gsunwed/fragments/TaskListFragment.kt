package com.route.todoappc43gsunwed.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import com.route.todoappc43gsunwed.adapter.ItemDayViewContainer
import com.route.todoappc43gsunwed.adapter.TaskListAdapter
import com.route.todoappc43gsunwed.database.TaskDatabase
import com.route.todoappc43gsunwed.databinding.FragmentTaskListBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TaskListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTaskListAdapter()
        initCalendarView()
    }

    fun initCalendarView() {
        binding.weekCalendarView.dayBinder = object : WeekDayBinder<ItemDayViewContainer> {
            override fun create(view: View): ItemDayViewContainer {
                return ItemDayViewContainer(view)
            }

            override fun bind(
                container: ItemDayViewContainer,
                data: WeekDay
            ) {
                container.weekDayTextView.text = data.date.dayOfWeek.getDisplayName(
                    TextStyle.SHORT,
                    Locale.getDefault()
                )
                container.monthDayTextView.text = "${data.date.dayOfMonth}"
            }
        }
        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now()
        val startDate = currentMonth.minusMonths(100).atStartOfMonth() // Adjust as needed
        val endDate = currentMonth.plusMonths(100).atEndOfMonth() // Adjust as needed
        val firstDayOfWeek = firstDayOfWeekFromLocale() // Available from the library
        binding.weekCalendarView.setup(startDate, endDate, firstDayOfWeek)
        binding.weekCalendarView.scrollToWeek(currentDate)
    }

    fun initTaskListAdapter() {
        val tasks =
            TaskDatabase.getInstance(requireContext().applicationContext).getTaskDao().getAllTasks()
        adapter = TaskListAdapter(tasks)
        binding.tasksRecyclerView.adapter = adapter
    }
    // How to make 2 Fragments Communicate With Each other ?
    //              1-  Interface Callback / Delegates
    //              2-  Shared View Model
}
