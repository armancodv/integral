package com.armanco.integral.data.db

import androidx.room.TypeConverter
import com.armanco.integral.data.models.Section


class SectionTypeConverter {

    @TypeConverter
    fun toSection(value: Int) = enumValues<Section>()[value]

    @TypeConverter
    fun fromSection(value: Section) = value.ordinal

}