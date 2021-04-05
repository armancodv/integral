package com.armanco.integral.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Formula(@PrimaryKey val id: Int, @ColumnInfo(name = "category_id", index = true) val categoryId: Int, @ColumnInfo(name = "drawable_res") @DrawableRes val drawableRes: Int, @ColumnInfo(name = "string_res") @StringRes val stringRes: Int)