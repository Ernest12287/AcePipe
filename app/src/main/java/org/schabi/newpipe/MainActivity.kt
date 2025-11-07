package org.schabi.newpipe

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.schabi.newpipe.about.AboutActivity
import org.schabi.newpipe.databinding.ActivityMainBinding
import org.schabi.newpipe.fragments.ExploreFragment
import org.schabi.newpipe.fragments.LibraryFragment
import org.schabi.newpipe.util.ThemeHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ThemeHelper.getSettingsThemeStyle(this))
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 2
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> ExploreFragment()
                    1 -> LibraryFragment()
                    else -> throw IllegalStateException("Unexpected position $position")
                }
            }
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_explore)
                1 -> getString(R.string.tab_library)
                else -> ""
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                return true
            }
            R.id.nav_telegram -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.telegram_channel_url)))
                startActivity(intent)
                return true
            }
            R.id.nav_whatsapp -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.whatsapp_channel_url)))
                startActivity(intent)
                return true
            }
        }
        return super.onNavigationItemSelected(item)
    }
}