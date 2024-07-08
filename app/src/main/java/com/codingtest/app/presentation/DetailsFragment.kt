package com.codingtest.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.codingtest.app.R
import com.codingtest.app.data.model.Articles
import com.codingtest.app.databinding.FragmentDetailsBinding
import com.codingtest.app.presentation.extension.formatToDisplay
import com.google.gson.Gson

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs? by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = context?.let {
            TransitionInflater.from(it).inflateTransition(android.R.transition.move)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()
        initialArgs()
    }

    private fun setToolbar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initialArgs() = with(binding) {
        args?.let {
            val article = Gson().fromJson(it.article, Articles::class.java)
            Glide.with(thumbnailImageView)
                .load(article.urlToImage)
                .placeholder(R.mipmap.ic_loading)
                .error(R.mipmap.ic_no_photo)
                .into(thumbnailImageView)
            titleTextView.text = article.title
            descTextView.text = article.description
            publishedAtTextView.text = article.publishedAt?.formatToDisplay()

        }
    }

}