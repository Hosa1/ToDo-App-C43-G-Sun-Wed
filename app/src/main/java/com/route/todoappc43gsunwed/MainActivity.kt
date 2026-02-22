package com.route.todoappc43gsunwed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.todoappc43gsunwed.callbacks.OnTaskAddedListener
import com.route.todoappc43gsunwed.databinding.ActivityMainBinding
import com.route.todoappc43gsunwed.fragments.AddTaskBottomSheetFragment
import com.route.todoappc43gsunwed.fragments.SettingsFragment
import com.route.todoappc43gsunwed.fragments.TaskListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // null
    private val taskListFragment = TaskListFragment()
    private val settingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavView()


    }

    private fun initBottomNavView() {
        binding.todoBottomAppBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_tasks -> {
                    pushFragment(taskListFragment)
                }

                R.id.navigation_settings -> {
                    pushFragment(settingsFragment)
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.todoBottomAppBar.selectedItemId = R.id.navigation_tasks
        binding.addFab.setOnClickListener {
            val bottomSheet = AddTaskBottomSheetFragment()
            bottomSheet.onTaskAddedListener = object : OnTaskAddedListener {
                override fun onTaskAdded() {
                    if (taskListFragment.isVisible)
                        taskListFragment.initTaskListAdapter()
                }
            }
            bottomSheet.show(supportFragmentManager, null)
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.taskFragmentContainer.id, fragment)
            .commit()
    }
}