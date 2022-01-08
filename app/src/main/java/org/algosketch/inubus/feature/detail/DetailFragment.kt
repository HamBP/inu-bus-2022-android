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
        viewModel.restTime.postValue("\"버스 ${arguments?.getInt("restTime")}분 뒤에 와요\"")
        viewModel.busNumber.postValue(arguments?.getString("busNumber"))
        viewModel.exit.postValue("정류장은 ${arguments?.getString("where")}역 ${arguments?.getInt("exit")}번 출구에서")
        viewModel.distance.postValue("${arguments?.getInt("distance")}m")
    }
}