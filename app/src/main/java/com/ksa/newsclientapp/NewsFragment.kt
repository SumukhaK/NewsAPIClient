package com.ksa.newsclientapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false

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
        newsAdapter = (activity as MainActivity).newsheadlinesAdapter
        newsAdapter.setOnitemClicklistner {

            val bundle = Bundle().apply {
                putSerializable("selected_article",it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,bundle)
        }
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
                        var pages : Int
                        if(it.totalResults%20 == 0){
                            pages = it.totalResults/20
                        }else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages

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

        //newsAdapter = NewsheadlinesAdapter()
        binding.newsheadlinesRecyclerview.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }


    private fun showProgressBar(){
        isLoading = true
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressCircular.visibility = View.INVISIBLE
    }


    private val onScrollListener = object  : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.newsheadlinesRecyclerview.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentlist = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPos = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPos+visibleItems >= sizeOfTheCurrentlist
            val shouldPaginate = !isLoading &&  !isLastPage && hasReachedToEnd && isScrolling

            if(shouldPaginate){
                page++
                viewModel.getHeadlines(country, page)
                isScrolling = false
            }
        }
    }
}