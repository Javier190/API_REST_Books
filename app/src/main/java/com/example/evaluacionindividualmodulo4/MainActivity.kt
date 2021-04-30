package com.example.evaluacionindividualmodulo4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), android.widget.SearchView.OnQueryTextListener {

    lateinit var list_books: ArrayList<Book>
    lateinit var adapterBook: AdapterBook
    lateinit var recycler_books:RecyclerView
    lateinit var searchBreed:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBreed = findViewById(R.id.searchBreed)
        searchBreed.setOnQueryTextListener(this)
        recycler_books = findViewById(R.id.recycler_books)
    }

    private fun setUpRecycler(books: BooksResponse) {
        //if(books.status == "OK"){ list_books = books.data }
        list_books = books.data
        adapterBook = AdapterBook(list_books)                //esto se modifoco
        recycler_books.setHasFixedSize(true)
        recycler_books.layoutManager = LinearLayoutManager(this)
        recycler_books.adapter = adapterBook
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://fakerapi.it/api/v1/") //tiene que terminar con una barra y se agrego https
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    override fun onQueryTextSubmit(query: String): Boolean {        //estos metodos se implementan del OnQueryTextListener
        searchByName(query!!.toLowerCase())             //Metodo paralanzar la petición cuando el usuario termina de escribir en el buscador, por lo que modificaremos la función para llamar a searchByName().
        System.out.println("***TextSubmit***")
        return true
    }

    private fun searchByName(query: String) {           //Recordar que las peticion a BD o que requieren internet deben hacerse por Hilos, Para eso usamos ANKO
        doAsync {
            System.out.println("***INICIO ASYNC***")
            val call = getRetrofit().create(APIServiceBook::class.java).getBookByName("books?_quantity=$query&_locale=en_US").execute()
            val employees = call.body() as BooksResponse       //Body es para obtener solo el "cuerpo" o la info que nesecitamos nada mas. Se devuelve un objeto generico por lo que hay que hacer cast
            System.out.println("***FIN ASYNC***")
            uiThread {
                employees
                if(employees.status == "OK") {
                    setUpRecycler(employees)      //Si la resp es ok, inicia la vista, o echa a andar el Recycler con los datos que recibimos y setea el adapter, algo asi
                    System.out.println("***FIN UITHREAD***")
                }else{
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        alert("Ha ocurrido un error, inténtelo de nuevo.") {
            yesButton { }
        }.show()
    }

    override fun onQueryTextChange(newText: String?): Boolean {     //estos metodos se implementan del OnQueryTextListener
        return true
    }

}