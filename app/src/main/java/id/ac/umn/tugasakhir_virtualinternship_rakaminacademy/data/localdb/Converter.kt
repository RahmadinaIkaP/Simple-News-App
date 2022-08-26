package id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.data.localdb

import androidx.room.TypeConverter
import id.ac.umn.tugasakhir_virtualinternship_rakaminacademy.model.Source

class Converter {
    @TypeConverter
    fun convertSource(source: Source) : String? {
        return source.name
    }

    @TypeConverter
    fun convertedSource(name : String) : Source{
        return Source(name, name)
    }
}