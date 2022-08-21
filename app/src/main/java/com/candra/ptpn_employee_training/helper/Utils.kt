package com.candra.ptpn_employee_training.helper

import android.content.Context
import android.content.res.Configuration
import android.view.View
import androidx.core.widget.NestedScrollView
import com.google.android.material.textview.MaterialTextView

object Utils {

    fun showEmptyText(isShow: Boolean,textShow: MaterialTextView,nestedScrollView: NestedScrollView){
        textShow.visibility = if (isShow) View.VISIBLE else View.GONE
        nestedScrollView.visibility = if (isShow) View.GONE else View.VISIBLE
    }

    val Context.isDarkMode get() = this.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

}