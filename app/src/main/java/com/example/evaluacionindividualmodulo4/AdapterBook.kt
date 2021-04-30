package com.example.evaluacionindividualmodulo4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterBook(var list_books: List<Book>) : RecyclerView.Adapter<AdapterBook.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list_books[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_books, parent, false))
    }

    override fun getItemCount(): Int {
        return list_books.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titulo = itemView.findViewById(R.id.tv_titulo) as TextView
        private val autor = itemView.findViewById(R.id.tv_autor) as TextView
        private val genero = itemView.findViewById(R.id.tv_genero) as TextView
        private val descripcion = itemView.findViewById(R.id.tv_descripcion) as TextView
        private val image = itemView.findViewById(R.id.ig_libro) as ImageView   //falta picasso

        fun bind(libro: Book) {
            titulo.text = libro.title
            autor.text = libro.author
            genero.text = libro.genre
            descripcion.text = libro.description
        }
    }
}
