package com.university.listing.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.university.core.constant.Constant
import com.university.core.exception.AppException
import com.university.core.exception.getMessageShouldDisplay
import com.university.core.extension.gone
import com.university.core.extension.observe
import com.university.core.extension.show
import com.university.core.extension.showErrorSnackBar
import com.university.core.extension.viewBinding
import com.university.core.entity.University
import com.university.core.navigation.AppScreen
import com.university.core.navigation.Navigator
import com.university.listing.R
import com.university.listing.databinding.FragmentUniversityListBinding
import com.university.listing.presentation.viewmodel.UniversityListState
import com.university.listing.presentation.viewmodel.UniversityListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UniversityListFragment(
    private val navigateTo: (AppScreen) -> Unit = {}
) : Fragment(R.layout.fragment_university_list) {

    private val binding by viewBinding(FragmentUniversityListBinding::bind)
    private val universityAdapter: UniversityAdapter by lazy { UniversityAdapter(::onItemClicked) }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: UniversityListViewModel by lazy {
        ViewModelProvider(this, viewModelProviderFactory)[UniversityListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpFragmentResultListener()
        observe(viewModel.screenState, ::onScreenStateChanged)
    }

    private fun setUpFragmentResultListener() {
        setFragmentResultListener(Constant.REFRESH_LISTING) { requestKey, _ ->
            if (requestKey == Constant.REFRESH_LISTING)
                viewModel.getUniversityList()
        }
    }

    private fun initViews() {
        with(binding.rvUniversities) {
            layoutManager =
                LinearLayoutManager(context ?: return, LinearLayoutManager.VERTICAL, false)
            adapter = universityAdapter
        }
    }

    private fun onItemClicked(university: University) {
        navigator.navigateTo(AppScreen.Details(university))
    }

    private fun onScreenStateChanged(state: UniversityListState) {
        when (state) {
            is UniversityListState.Loading -> showSkeleton()
            is UniversityListState.Success -> handleSuccessState(state.data)
            is UniversityListState.Error -> handleErrorState(state.e)
            is UniversityListState.Initial -> Unit
        }
    }

    private fun handleErrorState(e: AppException) {
        binding.root.showErrorSnackBar(errorMessage = e.getMessageShouldDisplay(context ?: return))
    }

    private fun handleSuccessState(data: List<University>) {
        hideSkeleton()
        universityAdapter.setItems(data)
    }

    private fun showSkeleton() {
        binding.skView.root.show()
    }

    private fun hideSkeleton() {
        binding.skView.root.gone()
    }

}