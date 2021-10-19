package com.example.bookstore.ui.MainFragment.ProgrammingFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentProgrammingBinding
import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.ui.MainFragment.NovelFragment.Adapter.NovelAdapter
import com.example.bookstore.ui.MainFragment.NovelFragment.Adapter.onNovelCilckListener


class ProgrammingFragment : Fragment(), onNovelCilckListener {

    private lateinit var binding: FragmentProgrammingBinding
    private lateinit var programmingViewModel: ProgrammingViewModel

    private val novelAdapter: NovelAdapter by lazy { NovelAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProgrammingBinding.inflate(inflater, container, false)
        binding.ProgrammingRecyclerView.adapter = novelAdapter
        binding.ProgrammingRecyclerView.setHasFixedSize(true)
        binding.ProgrammingRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.ProgrammingRecyclerView.setItemViewCacheSize(R.layout.book_item)
        novelAdapter.listener = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        programmingViewModel =
            ViewModelProvider(requireActivity()).get(ProgrammingViewModel::class.java)
        gatBooks()
        programmingViewModel.programmingLiveData.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.ProgrammingProgressBar.visibility = View.GONE
                novelAdapter.setNovelData(it)
            } else {
                binding.ProgrammingProgressBar.visibility = View.GONE
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun gatBooks() {
        binding.ProgrammingProgressBar.visibility = View.VISIBLE
        programmingViewModel.getAllProgrammingBook()
    }

    override fun onNovelClick(book: BookShopItem) {
        Toast.makeText(context, book.title, Toast.LENGTH_SHORT).show()
    }

}