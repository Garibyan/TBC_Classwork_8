package com.garibyan.armen.tbc_task_8.presentation.mainfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.garibyan.armen.tbc_task_8.common.base.BaseFragment
import com.garibyan.armen.tbc_task_8.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {

    private val viewModel: MainViewModel by viewModels()
    private val itemAdapter: ItemAdapter by lazy { ItemAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewModel.getAllCourses()

            viewModel.itemsState.collectLatest {
                if (it.data != null) {
                    binding.rvMain.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.tvErrorMsg.visibility = View.GONE
                    itemAdapter.submitList(it.data)
                    initRecyclerView()
                }
                if(it.loading){
                    binding.rvMain.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvErrorMsg.visibility = View.GONE
                }
                if(it.error != ""){
                    binding.rvMain.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.tvErrorMsg.visibility = View.VISIBLE
                    binding.tvErrorMsg.text = it.error
                }
            }
        }
    }

    private fun initRecyclerView() = with(binding.rvMain) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = itemAdapter
    }

}