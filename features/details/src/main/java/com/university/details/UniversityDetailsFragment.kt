package com.university.details

import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.university.core.constant.Constant
import com.university.core.extension.viewBinding
import com.university.details.databinding.FragmentUniversityDetailsBinding
import com.university.core.entity.University
import com.university.core.navigation.AppScreen

class UniversityDetailsFragment(
    private val navigateTo: (AppScreen) -> Unit = {}
) : Fragment(R.layout.fragment_university_details) {

    private val binding by viewBinding(FragmentUniversityDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(getUniversityFromBundle(arguments))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(UNIVERSITY_DETAILS, getUniversityFromBundle(arguments))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        getUniversityFromBundle(savedInstanceState)?.let {
            bindViews(getUniversityFromBundle(savedInstanceState))
        }
    }

    private fun bindViews(university: University?) {
        university?.let {
            with(binding) {
                tvName.text = university.name
                tvState.text = university.stateProvince ?: university.country
                tvCountry.text = university.country
                tvCountryCode.text = university.alphaTwoCode

                tvWebPages.movementMethod = LinkMovementMethod.getInstance()
                tvWebPages.text = university.webPages.joinToString(separator = "\n")

                imgRefresh.setOnClickListener { handleOnRefreshClicked() }
            }
        }
    }

    private fun handleOnRefreshClicked() {
        setFragmentResult(
            Constant.REFRESH_LISTING,
            bundleOf()
        )
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    private fun getUniversityFromBundle(arguments: Bundle?): University? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(UNIVERSITY_DETAILS, University::class.java)
        } else {
            arguments?.getParcelable(UNIVERSITY_DETAILS)
        }
    }

    companion object {
        private const val UNIVERSITY_DETAILS = "UNIVERSITY_DETAILS"
        fun buildBundle(university: University): Bundle {
            return Bundle().apply {
                putParcelable(UNIVERSITY_DETAILS, university)
            }
        }
    }
}