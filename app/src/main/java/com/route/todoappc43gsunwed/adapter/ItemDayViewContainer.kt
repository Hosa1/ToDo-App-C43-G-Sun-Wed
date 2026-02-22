package com.route.todoappc43gsunwed.adapter

import android.view.View
import com.kizitonwose.calendar.view.ViewContainer
import com.route.todoappc43gsunwed.databinding.ItemDayBinding

class ItemDayViewContainer(val itemView: View) : ViewContainer(itemView) {
    val monthDayTextView = ItemDayBinding.bind(itemView).monthDayTextView
    val weekDayTextView = ItemDayBinding.bind(itemView).weekDayTextView
}