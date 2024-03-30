package com.ifs21023.pampraktikum8

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ifs21023.pampraktikum8.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val dataEmail: ArrayList<EmailItem> = ArrayList()
    private lateinit var tulis_btn: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Initialize views
        tulis_btn = view.findViewById<FloatingActionButton>(R.id.tulis_btn)

        tulis_btn.setOnClickListener {
            Toast.makeText(context, "Kamu memilih menu Buat Email Baru!", Toast.LENGTH_SHORT).show()
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val message = it.getString(EXTRA_MESSAGE)
            binding.tvFragmentHomeMessage.text = message
        }

        binding.apply {
            svFragmentHome.setupWithSearchBar(sbFragmentHome)
            svFragmentHome
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    val keyword = svFragmentHome.text.toString().toLowerCase() // Keyword pencarian
                    val filteredEmails = dataEmail.filter { // Filter email sesuai keyword
                        it.nama.toLowerCase().contains(keyword) ||
                                it.subjek.toLowerCase().contains(keyword) ||
                                it.teks.toLowerCase().contains(keyword)
                    }.toMutableList()
                    showRecyclerList(ArrayList(filteredEmails))
                    svFragmentHome.hide()
                    false
                }
        }
        dataEmail.addAll(getListEmail())
        showRecyclerList()
        binding.rvEmail.setHasFixedSize(false)
    }


    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }

    @SuppressLint("Recycle")
    private fun getListEmail(): ArrayList<EmailItem> {
        val dataPengirim =
            resources.getStringArray(R.array.pengirim_email)
        val dataIcon =
            resources.obtainTypedArray(R.array.icon_email)
        val dataSubjek =
            resources.getStringArray(R.array.subjek_email)
        val dataTeks =
            resources.getStringArray(R.array.text_isi)

        val listEmail = ArrayList<EmailItem>()
        for (i in dataPengirim.indices) {
            val email = EmailItem(
                dataPengirim[i],
                dataIcon.getResourceId(i, -1),
                dataSubjek[i],
                dataTeks[i])
            listEmail.add (email)
        }
        return listEmail
    }

    private fun showRecyclerList(emails: ArrayList<EmailItem> = dataEmail) {
        val layoutManager = if (requireActivity().resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvEmail.layoutManager = layoutManager
        val listEmailAdapter = ListEmailAdapter(emails)
        binding.rvEmail.adapter = listEmailAdapter
    }



}
