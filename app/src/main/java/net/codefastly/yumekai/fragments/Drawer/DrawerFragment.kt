package net.codefastly.yumekai.fragments.Drawer

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentDrawerBinding
import net.codefastly.yumekai.helpers.ViewPagers.DrawerViewPager

class DrawerFragment : Fragment() {

    private lateinit var binding: FragmentDrawerBinding

    private val viewModel by lazy { ViewModelProvider(this).get(DrawerViewModel::class.java) }

    private lateinit var viewPagerAdapter: DrawerViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater ,R.layout.fragment_drawer, container, false )


        initViewModel()
        initViewPager()

        binding.drawerScreenBtnBack.setOnClickListener {
            requireActivity().finish()
        }

        return binding.root
    }

    private fun initViewPager(){

        viewPagerAdapter = DrawerViewPager(viewModel.list, requireContext())
        with(binding.drawerScreenViewpager){
            adapter = viewPagerAdapter
        }
        synchronizeTabLayout()
    }

    private fun synchronizeTabLayout(){
        TabLayoutMediator(binding.drawerScreenTabs, binding.drawerScreenViewpager){ tab, position ->
            tab.text = viewModel.tabList[position]
            /*tab.icon = ContextCompat.getDrawable(requireContext(), viewModel.icons[position])*/
        }.attach()
    }

    private fun initViewModel(){
        viewModel.attach( this , requireContext() )
        viewModel.fetchMagicDrawerData()
    }


}