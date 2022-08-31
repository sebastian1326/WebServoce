package com.lopezing.webserviceram.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lopezing.webserviceram.databinding.FragmentHomeBinding
import com.lopezing.webserviceram.server.model.Person

class HomeFragment : Fragment() {

    private lateinit var homeViewModel : HomeViewModel
    private lateinit var homeBinding: FragmentHomeBinding
    private var personList: ArrayList<Person> = ArrayList()
    private lateinit var personAdapter:PersonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = homeBinding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personAdapter =PersonAdapter(personList, onItemClicked = { onPersonItemClicked(it)})
        homeViewModel.personObserve.observe(viewLifecycleOwner){ result ->
            onMoviesLoadedSubscribe(result)
        }
        homeBinding.moviesRecyclerView.apply {
            layoutManager=LinearLayoutManager(this@HomeFragment.requireContext())
            adapter=personAdapter
            setHasFixedSize(false)
        }
        homeViewModel.getRaM()

    }

    private fun onPersonItemClicked(person: Person) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToDetailsFragment(person))
    }
    private fun onMoviesLoadedSubscribe(personsList: ArrayList<Person>?) {
        personsList?.let { personsList->
            personAdapter.appendItems(personsList)
        }
    }
}