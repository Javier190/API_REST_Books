package com.example.evaluacionindividualmodulo4

import com.google.gson.annotations.SerializedName

data class BooksResponse (@SerializedName("status") var status:String,
                             @SerializedName("code") var code: Int,        //no era lista, es solo objeto employee esto tb se arreglo
                             @SerializedName("total") var total:Int,
                          @SerializedName("data") var data:ArrayList<Book>   //MUCHO OJO CON COMO SE RECIBE EL DATO Y QUE TIPO !!
)
