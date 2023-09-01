package com.example.pruebafinalsuperheroes.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.pruebafinalsuperheroes.R
import com.example.pruebafinalsuperheroes.databinding.FragmentListaHeroesBinding


class FragmentListaHeroes : Fragment() {
 lateinit var binding: FragmentListaHeroesBinding
 private val heroeVM: HeroeVM by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaHeroesBinding.inflate(layoutInflater,container,false)
        heroeVM.getTodosHeroes()
        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterHeroe()
        binding.RVheroes.adapter = adapter
        heroeVM.heroeLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }
    }


}