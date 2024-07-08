package com.codingtest.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingtest.app.R
import com.codingtest.app.data.model.Articles
import com.codingtest.app.databinding.FragmentCodingTestBinding
import com.codingtest.app.presentation.adapter.NewsPagingAdapter
import com.codingtest.app.presentation.viewmodel.NewsApiViewModel
import com.google.gson.Gson
import org.koin.android.ext.android.inject

class CodingFragment : Fragment(), NewsPagingAdapter.NewsWithPagingAdapterListener {
    private lateinit var binding: FragmentCodingTestBinding
    private val viewModel: NewsApiViewModel by inject()
    private var newsAdapter: NewsPagingAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodingTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNewsData()
        setToolbar()
        initialRecyclerView()
        observeViewModel()
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initialRecyclerView() = with(binding) {
        recyclerView.apply {
            newsAdapter = NewsPagingAdapter(this@CodingFragment)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
            adapter = newsAdapter
            onFlingListener = null
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
        }
    }

    private fun observeViewModel() {
        viewModel.observeNewsLiveData().observe(viewLifecycleOwner) { data ->
            newsAdapter?.submitData(lifecycle, data)
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClickItem(articles: Articles) {
        val action =
            CodingFragmentDirections.actionCodingFragmentToDetailsFragment(Gson().toJson(articles))
        findNavController().navigate(action)
    }
}