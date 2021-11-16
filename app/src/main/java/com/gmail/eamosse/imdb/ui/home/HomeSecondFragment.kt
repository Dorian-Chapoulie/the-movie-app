package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import kotlinx.android.synthetic.main.fragment_home_second.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSecondFragment : Fragment() {
    val args: HomeSecondFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeSecondBinding

    companion object {
        const val ARG_GENRE = "genre"
    }

    var page: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    // On charge les films
                    getDiscover(id = args.myArg.toInt())

                    // On ajoute un listener qui détecte le fin fond de la liste et qui va charger les films suivants. On le fait à ce moment la pour pas qu'il se crée avant d'avoir des films
                    discover_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(
                            recyclerView: RecyclerView,
                            newState: Int
                        ) {
                            super.onScrollStateChanged(recyclerView, newState)
                        }
                    })
                }
            )

            // Click sur le fragment
            discoveries.observe(
                viewLifecycleOwner,
                Observer {
                    /*binding.categoryList.adapter = DiscoverAdapter(it) {
                        val action = HomeSecondFragmentDirections
                            .actionHomeSecondFragmentToHomeFragment()
                        NavHostFragment.findNavController(this@HomeSecondFragment)
                            .navigate(action)
                    }
                    println("cick on movie: " + it)*/
                }
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(homeViewModel) {
            discoveries.observe(
                viewLifecycleOwner,
                Observer {
                    binding.discoverList.adapter = DiscoverAdapter(it) {
                        val action = HomeSecondFragmentDirections
                            .actionHomeSecondFragmentToHomeFragment()
                        NavHostFragment.findNavController(this@HomeSecondFragment)
                            .navigate(action)
                    }
                }
            )
        }
    }
}

