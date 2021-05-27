package net.codefastly.yumekai.fragments.NoInternet

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import net.codefastly.yumekai.R
import net.codefastly.yumekai.activities.Splash.SplashActivity
import net.codefastly.yumekai.databinding.FragmentNoInternetBinding


class NoInternetFragment : Fragment() {
    private lateinit var binding: FragmentNoInternetBinding
    private var status: MutableLiveData<Boolean> = MutableLiveData()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_no_internet, container, false)
        binding.itemNoInternetTitle.text = "Internet Failed"
        binding.itemNoInternetDesc.text = "Check that the device has an internet connection"
        binding.itemNoInternetRetry.setOnClickListener {
            val intent = Intent(context, SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}

