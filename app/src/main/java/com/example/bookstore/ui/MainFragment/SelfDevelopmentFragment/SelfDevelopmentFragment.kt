package com.example.bookstore.ui.MainFragment.SelfDevelopmentFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentSelfDevelopmentBinding
import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.remote.BookShopAPI
import com.example.bookstore.ui.MainFragment.NovelFragment.Adapter.NovelAdapter
import com.example.bookstore.ui.MainFragment.NovelFragment.Adapter.onNovelCilckListener
import com.example.bookstore.ui.MainFragment.ProgrammingFragment.ProgrammingViewModel


class SelfDevelopmentFragment : Fragment(), onNovelCilckListener{

    private lateinit var binding: FragmentSelfDevelopmentBinding
    private val adapter: NovelAdapter by lazy { NovelAdapter() }
    private lateinit var selfDevelopmentViewModel: SelfDevelopmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSelfDevelopmentBinding.inflate(inflater,container,false)

        binding.SelfDevRecyclerView.setHasFixedSize(true)
        binding.SelfDevRecyclerView.adapter = adapter
        binding.SelfDevRecyclerView.layoutManager = GridLayoutManager(activity,2)
        binding.SelfDevRecyclerView.setItemViewCacheSize(R.layout.book_item)
        adapter.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selfDevelopmentViewModel = ViewModelProvider(requireActivity()).get(SelfDevelopmentViewModel::class.java)
        getSelfDevBooks()
        selfDevelopmentViewModel.selfLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.SelfDevProgressBar.visibility = View.GONE
                adapter.setNovelData(it)
            } else {
                binding.SelfDevProgressBar.visibility = View.GONE
                Toast.makeText(context,  "Error", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getSelfDevBooks() {
        binding.SelfDevProgressBar.visibility = View.VISIBLE
        selfDevelopmentViewModel.getSelfDevelopmentBooks()
    }

    override fun onNovelClick(book: BookShopItem) {
        Toast.makeText(context, book.title, Toast.LENGTH_SHORT).show()
    }
}