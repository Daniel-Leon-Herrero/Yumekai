package net.codefastly.yumekai.fragments.WebView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentWebViewBinding

class WebViewFragment( private val web_link: String ) : Fragment() {

    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_web_view, container, false)

        binding.webViewScreenWb.loadUrl( web_link )

        return binding.root
    }



}