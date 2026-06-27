package com.example.test.ui.components

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.test.R

object ResourceHelper {
    
    @DrawableRes
    fun getDrawableResId(context: Context, resName: String): Int {
        val id = context.resources.getIdentifier(resName, "drawable", context.packageName)
        return if (id != 0) id else R.drawable.tees // fallback to tees if not found
    }
}
