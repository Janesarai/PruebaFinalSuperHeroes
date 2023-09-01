package com.example.pruebafinalsuperheroes.vistas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.pruebafinalsuperheroes.R
import com.example.pruebafinalsuperheroes.databinding.FragmentDetalleHeroesBinding

private const val ARG_PARAM1 = "id"
class FragmentDetalleHeroes : Fragment() {
    lateinit var binding: FragmentDetalleHeroesBinding
    private val heroeVM: HeroeVM by activityViewModels()
    private  var heroeId: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            heroeId= it.getInt("id")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleHeroesBinding.inflate(layoutInflater,container,false)
        heroeVM.getDetallesHeroe(heroeId)
        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        heroeVM.detalleLiveData(heroeId).observe(viewLifecycleOwner){hereoDetail ->
            if (hereoDetail != null){
                binding.txtNombre.text = hereoDetail.nombre
                binding.txtOrigen.text = hereoDetail.origen
                binding.txtPoder.text = hereoDetail.poder
                binding.txtColor.text = hereoDetail.color
                binding.txtCreacion.text = hereoDetail.creacion.toString()
                binding.imageSuperHeroe.load(hereoDetail.imagen)
                if (!hereoDetail.traduccion){
                    binding.txtTraduccion.text = "Sin traducción"
                }else{
                    binding.txtTraduccion.text = "Cuenta con traducción al español"
                }
                binding.btnCorreo.setOnClickListener { _->
                    val email = "Comicconchile@hotmail.com"
                    val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(email))
                    intentEmail.type = "plain/text"
                    intentEmail.putExtra(
                        Intent.EXTRA_SUBJECT, "Votación ${hereoDetail.nombre} ")
                    intentEmail.putExtra(
                        Intent.EXTRA_TEXT, "“Hola\n" +
                            "Quiero que el siguiente super héroes ${hereoDetail.nombre} aparezca, en la nueva edición de\n" +
                                "biografías animadas.\n" +
                            "Número contacto: _________\n" +
                            "Gracias”")
                    intentEmail.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    startActivity(Intent.createChooser(intentEmail, "Enviar correo"))

                }
            }
        }
    }


}