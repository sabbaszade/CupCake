package com.example.redstackapp.frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.redstackapp.R
import com.example.redstackapp.adapter.KeyWordAdapter
import com.example.redstackapp.databinding.FragmentWriteArticleBinding
import com.example.redstackapp.model.KeyWord


class WriteArticleFragment : Fragment() {
    private lateinit var binding: FragmentWriteArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: WriteArticleFragmentArgs by navArgs()
        val listWord = mutableListOf<KeyWord>()
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_write_article, container, false)
        setActionBarNav()
        repeat(3) {
            listWord.add(KeyWord(getString(R.string.commerce)))
        }
        setupRv(listWord)

        val title = args.title
        val desc = args.desc
        binding.edtTitle.setText(title)
        binding.edtArticleTxt.setText(desc)
        binding.btnEnter.text = getString(R.string.edit_Article)

        return binding.root
    }

    private fun setActionBarNav() {
        binding.imgArrowRight.setOnClickListener {
            setUpNavigation()
        }
    }

    private fun setUpNavigation() {
        findNavController().navigateUp()
    }

    private fun setupRv(
        listWord: MutableList<KeyWord>
    ) {
        binding.rvKeyWord.adapter = KeyWordAdapter(listWord)
        binding.rvKeyWord.setHasFixedSize(true)
    }

}