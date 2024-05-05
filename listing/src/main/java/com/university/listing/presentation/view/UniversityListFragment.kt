package com.university.listing.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.university.core.extension.observe
import com.university.listing.R
import com.university.listing.presentation.viewmodel.UniversityListState
import com.university.listing.presentation.viewmodel.UniversityListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UniversityListFragment : Fragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private val viewModel: UniversityListViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory)[UniversityListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_university_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe(viewModel.screenState, ::onScreenStateChanged)
    }

    private fun onScreenStateChanged(state: UniversityListState) {

    }

    private fun initViews() {

    }

}