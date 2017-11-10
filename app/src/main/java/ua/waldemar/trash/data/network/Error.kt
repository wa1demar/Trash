package ua.waldemar.trash.data.network

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import java.util.*

@JsonObject class Error {

    @JsonField(name = arrayOf("errors"))
    var errorList: List<String> = ArrayList()

    constructor(vararg errors: String) {
        errorList = mutableListOf(*errors)
    }

    fun getFirstError(): String? {
        return if (errorList.isEmpty()) null else errorList[0]
    }
}