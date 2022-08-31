package com.lopezing.webserviceram.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.lopezing.webserviceram.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private val args:DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding=FragmentDetailsBinding.inflate(inflater,container,false)
        detailsViewModel=ViewModelProvider(this)[DetailsViewModel::class.java]
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val person=args.person
        with(detailsBinding){
            personTitleTextView.text=person.name
            specieDateTextView.text=person.species
            genderAverageTextView.text=person.gender
            summaryTextView.text=person.name+" life in "+person.location?.name+", is origin the "+person.origin?.name+" and created in "+person.created
            Picasso.get().load(person.image).into(posterImageView)
        }
    }
}