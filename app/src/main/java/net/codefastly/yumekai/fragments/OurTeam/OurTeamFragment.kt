package net.codefastly.yumekai.fragments.OurTeam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentOurTeamBinding
import net.codefastly.yumekai.fragments.WebView.WebViewFragment


class OurTeamFragment : Fragment() {

    private lateinit var binding : FragmentOurTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_our_team , container, false  )


        binding.ourTeamScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        binding.ourTeamScreenBtnWebsite.setOnClickListener {
            val mContext = context as FragmentActivity
            val transaction = mContext.supportFragmentManager.beginTransaction()
            transaction.hide( this ).add(
                R.id.nav_host_fullscreen_fragment,
                WebViewFragment("https://www.codefastly.net" ),
                "WebViewFragment" )
            transaction.commit()
        }




        return binding.root
    }

}