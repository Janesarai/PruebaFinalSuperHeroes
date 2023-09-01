package com.example.pruebafinalsuperheroes.vistas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pruebafinalsuperheroes.R
import com.example.pruebafinalsuperheroes.data.local.HeroeEntity
import com.example.pruebafinalsuperheroes.databinding.ItemHeroeBinding

class AdapterHeroe: RecyclerView.Adapter <AdapterHeroe.ItemHeroeViewHolder>() {

    lateinit var binding: ItemHeroeBinding
    private val listaItemHeroe = mutableListOf<HeroeEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterHeroe.ItemHeroeViewHolder {
        binding = ItemHeroeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemHeroeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterHeroe.ItemHeroeViewHolder, position: Int) {
        val heroe = listaItemHeroe[position]
        holder.bind(heroe)
    }

    override fun getItemCount(): Int {
        return listaItemHeroe.size
    }

    fun setData(heroe: List<HeroeEntity>){
        this.listaItemHeroe.clear()
        this.listaItemHeroe.addAll(heroe)
        notifyDataSetChanged()
    }
    class ItemHeroeViewHolder(val heroeVista: ItemHeroeBinding):RecyclerView.ViewHolder(heroeVista.root) {
        fun bind(heroe: HeroeEntity){
            heroeVista.txNombre.text = heroe.nombre
            heroeVista.txOrigen.text = heroe.origen
            heroeVista.imgHeroe.load(heroe.imagen)
            heroeVista.CVheroe.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", heroe.id)
                Navigation.findNavController(heroeVista.root).navigate(R.id.action_fragmentListaHeroes_to_fragmentDetalleHeroes,bundle)
            }
        }
    }
}