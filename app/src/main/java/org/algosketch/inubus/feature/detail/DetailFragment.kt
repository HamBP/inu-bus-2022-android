package org.algosketch.inubus.feature.detail

import androidx.fragment.app.viewModels
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentDetailBinding
import org.algosketch.inubus.global.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutResourceId = R.layout.fragment_detail
    private val viewModel: DetailViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }
}