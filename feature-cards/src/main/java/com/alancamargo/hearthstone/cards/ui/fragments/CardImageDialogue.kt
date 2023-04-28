package com.alancamargo.hearthstone.cards.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import coil.load
import com.alancamargo.hearthstone.cards.databinding.DialogueCardImageBinding
import com.alancamargo.hearthstone.core.design.R
import com.alancamargo.hearthstone.core.extensions.args
import com.alancamargo.hearthstone.core.extensions.putArguments
import kotlinx.parcelize.Parcelize

internal class CardImageDialogue : DialogFragment() {

    private var _binding: DialogueCardImageBinding? = null
    private val binding: DialogueCardImageBinding
        get() = _binding!!

    private val args by args<Args>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogueCardImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        binding.img.load(args.imageUrl) {
            placeholder(R.drawable.ic_image)
            val width = 800
            val height = 1024
            size(width, height)
        }
    }

    @Parcelize
    data class Args(val imageUrl: String) : Parcelable

    companion object {
        fun newInstance(args: Args): CardImageDialogue {
            return CardImageDialogue().putArguments(args)
        }
    }
}
