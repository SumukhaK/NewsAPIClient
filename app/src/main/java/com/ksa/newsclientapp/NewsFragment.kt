package com.ksa.newsclientapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ksa.newsclientapp.data.util.Resource
import com.ksa.newsclientapp.databinding.FragmentNewsBinding
import com.ksa.newsclientapp.presentation.adapter.NewsheadlinesAdapter
import com.ksa.newsclientapp.presentation.viewmodel.NewsViewModel


class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    lateinit var newsAdapter : NewsheadlinesAdapter
    private var country = "in"
    private var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {

        viewModel.getHeadlines(country, page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{

                    hideProgressBar()
                    it.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }

                is Resource.Error ->{
                    hideProgressBar()
                    it.message?.let {
                        Toast.makeText(activity,"An error occured : $it",Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecyclerView() {

        newsAdapter = NewsheadlinesAdapter()
        binding.newsheadlinesRecyclerview.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


    private fun showProgressBar(){
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressCircular.visibility = View.INVISIBLE
    }

}