package org.algosketch.inubus.feature.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentDetailBinding
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.store.Store

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override val layoutResourceId = R.layout.fragment_detail
    private val viewModel: DetailViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
        viewModel.shouldUndo.observe(this, {
            findNavController().popBackStack()
        })
    }

    override fun initState() {
        viewModel.information.postValue(
            "버스 정류장이 ${arguments?.getString("exit")}역 ${arguments?.getInt("exit")}번 출구에서 ${arguments?.getInt("distance")}m 떨어져 있어요."
        )
        viewModel.busNumber.postValue(arguments?.getString("busNumber"))
    }
}