package com.alancamargo.hearthstone.cards.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.alancamargo.hearthstone.cards.R
import com.alancamargo.hearthstone.cards.databinding.DialogueCardDetailsBinding
import com.alancamargo.hearthstone.cards.ui.model.UiCard
import com.alancamargo.hearthstone.core.extensions.args
import com.alancamargo.hearthstone.core.extensions.putArguments
import kotlinx.parcelize.Parcelize

internal class CardDetailsDialogue : DialogFragment() {

    private var _binding: DialogueCardDetailsBinding? = null
    private val binding: DialogueCardDetailsBinding
        get() = _binding!!

    private val args by args<Args>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogueCardDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi(args.card)
    }

    private fun setUpUi(card: UiCard) = with(binding) {
        txtName.text = card.name
        txtAttack.text = getString(R.string.attack_format, card.attack)
        txtCost.text = getString(R.string.cost_format, card.cost)
        txtHealth.text = getString(R.string.health_format, card.health)
        txtFaction.text = getString(R.string.faction_format, card.faction)
        txtPlayerClass.text = getString(R.string.player_class_format, card.playerClass)
        txtQuality.text = getString(R.string.quality_format, card.quality)
        txtRace.text = getString(R.string.race_format, card.race)
        txtType.text = getString(R.string.type_format, card.type)
        txtText.text = card.text
    }

    @Parcelize
    data class Args(val card: UiCard) : Parcelable

    companion object {
        fun newInstance(args: Args): CardDetailsDialogue {
            return CardDetailsDialogue().putArguments(args)
        }
    }
}
