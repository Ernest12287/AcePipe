/*
 * SPDX-FileCopyrightText: 2025 NewPipe e.V. <https://newpipe-ev.de>
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.schabi.newpipe.BuildConfig
import org.schabi.newpipe.R
import org.schabi.newpipe.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set app version
        binding.appVersion.text = "v${BuildConfig.VERSION_NAME}"

        // Set up social channel buttons with equal width
        binding.socialButtonsContainer.weightSum = 2f
        binding.telegramButton.layoutParams.width = 0
        binding.whatsappButton.layoutParams.width = 0
        binding.telegramButton.layoutParams.weight = 1f
        binding.whatsappButton.layoutParams.weight = 1f

        binding.telegramButton.setOnClickListener {
            openUrl(getString(R.string.telegram_channel_url))
        }

        binding.whatsappButton.setOnClickListener {
            openUrl(getString(R.string.whatsapp_channel_url))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}