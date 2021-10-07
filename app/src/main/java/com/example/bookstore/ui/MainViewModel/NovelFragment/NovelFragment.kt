package com.example.bookstore.ui.MainViewModel.NovelFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentNovelBinding
import com.example.bookstore.model.data.BookShopItem


class NovelFragment : Fragment(), onNovelCilckListener {

    private lateinit var binding:FragmentNovelBinding
    private val novelAdapter: NovelAdapter by lazy { NovelAdapter() }
    private lateinit var novelViewModel : NovelViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNovelBinding.inflate(inflater,container,false)

        binding.NovelRecyclerView.setHasFixedSize(true)
        binding.NovelRecyclerView.layoutManager = GridLayoutManager(activity,2)
        binding.NovelRecyclerView.setItemViewCacheSize(R.layout.book_item)
        binding.NovelRecyclerView.adapter = novelAdapter
        novelAdapter.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        novelViewModel = ViewModelProvider(requireActivity()).get(NovelViewModel::class.java)
        getAllNovel()
        novelViewModel.novelLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.NovelProgressBar.visibility = View.GONE
            } else {
                binding.NovelProgressBar.visibility = View.GONE
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getAllNovel() {
        novelViewModel.getNovelFromAPI()
        binding.NovelProgressBar.visibility = View.VISIBLE
    }

    override fun onNovelClick(book: BookShopItem) {
        Toast.makeText(context, book.title, Toast.LENGTH_SHORT).show()
    }
}