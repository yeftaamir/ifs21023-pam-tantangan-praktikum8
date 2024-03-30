package com.ifs21023.pampraktikum8

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21023.pampraktikum8.HomeFragment
import com.ifs21023.pampraktikum8.MeetingFragment
import com.ifs21023.pampraktikum8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()
    }

    private fun setupView() {
        binding.navView.setCheckedItem(R.id.nav_all_inbox)
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)
        loadFragment(FLAG_FRAGMENT_HOME)
    }
    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_user -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Akun User")
                    true
                }
                R.id.action_pengaturan -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Setting!")
                    true
                }
                R.id.action_keluar -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Keluar")
                    true
                }
                else -> true
            }
        }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_all_inbox -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Semua Kotak Masuk")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_inbox -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Utama")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_promotions -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Promotions!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_social -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Social!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_starred -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Pesan Berbintang!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_terkirim-> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Email terkirim")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_sampah-> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Item Sampah!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> true
            }
        }
        binding.inAppBar.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_mail -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    true
                }

                R.id.navigation_meet -> {
                    loadFragment(FLAG_FRAGMENT_MEETING)
                    true
                }
                else -> true
            }
        }
    }
    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inAppBar.inContentMain.frameContainer.id
        when (flag) {
            FLAG_FRAGMENT_HOME -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_mail)
                    .setChecked(true)
                val homeFragment = HomeFragment()
                val bundle = Bundle().apply {
                    this.putString(
                        HomeFragment.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                homeFragment.arguments = bundle
                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        homeFragment,
                        homeFragment::class.java.simpleName
                    )
                    .commit()
            }
            FLAG_FRAGMENT_MEETING -> {
                val meetingFragment = MeetingFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(MeetingFragment::class.java.simpleName)
                if (fragment !is MeetingFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            meetingFragment,
                            MeetingFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
        }
    }
    companion object {
        const val FLAG_FRAGMENT_HOME = "fragment_home"
        const val FLAG_FRAGMENT_MEETING = "fragment_meeting"
    }
}