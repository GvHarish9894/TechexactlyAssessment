package com.techgv.techexactlyassessment.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.techgv.techexactlyassessment.data.remote.dto.App
import com.techgv.techexactlyassessment.databinding.FragmentAppListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppListFragment : Fragment() {

    private val viewModel: AppListFragmentViewModel by viewModels()

    private var _binding: FragmentAppListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AppListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAppListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        searchAppList()
        initObservers()

    }

    private fun initObservers() {
        viewModel.appListLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.masterUpdateList(it)
            }
        }
    }

    private fun initAdapter() {
        adapter = AppListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun searchAppList() {

        binding.searchBar.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrEmpty()){
                    adapter.filter(p0.toString())
                }else{
                    adapter.restList()
                }
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}