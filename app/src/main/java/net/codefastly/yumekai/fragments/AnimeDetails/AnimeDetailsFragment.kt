package net.codefastly.yumekai.fragments.AnimeDetails

import android.animation.Animator
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import net.codefastly.yumekai.R
import net.codefastly.yumekai.databinding.FragmentAnimeDetailsBinding
import net.codefastly.yumekai.fragments.Calendar.CalendarFragment
import net.codefastly.yumekai.helpers.RecyclesViews.CategoryAnimeAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.CharacterAnimeAdapter
import net.codefastly.yumekai.helpers.RecyclesViews.StaffAnimeAdapter
import net.codefastly.yumekai.helpers.ViewPagers.AnimeDetailsViewPager
import net.codefastly.yumekai.helpers.ViewPagers.DrawerViewPager
import net.codefastly.yumekai.models.AnimeCharacters.CharacterAnimeResponse
import net.codefastly.yumekai.models.anime.AnimeResponse
import net.codefastly.yumekai.models.anime.Genre
import net.codefastly.yumekai.models.room.LocalAnime

class AnimeDetailsFragment(val anime: Int, val previousFragment: Fragment?) : Fragment() {

    private lateinit var binding: FragmentAnimeDetailsBinding

    /* JOSE UGAL */
    private lateinit var animeDetailsAdapter: AnimeDetailsViewPager

    private val viewModel by lazy { ViewModelProvider(this).get( AnimeDetailsViewModel::class.java ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anime_details, container, false)

        viewModel.setAttach( requireContext(), this)

        viewModel.fetchAnimeDetails( anime )

        viewModel.anime.observe( viewLifecycleOwner, { animeData ->
            if( animeData.image_url.isNullOrEmpty() ){
                binding.animeDetailsScreenImgPortrait.setImageResource(R.drawable.yumekai_failed_portrait)
            }else{
                Picasso.get().load( animeData.image_url ).into( binding.animeDetailsScreenImgPortrait )
            }
            viewModel.characterAndStaff.observe( viewLifecycleOwner, { dataResp ->
                initViewPager( animeData, dataResp )
            })
        })

        /*
        COMPROBAR SI PODEMOS PASAR LOS ADAPTADORES AL RECYCLER Y CONTROLARLOS DESDE AQUI
         */


        viewModel.fetching.observe(viewLifecycleOwner, { fetchCount ->
            if( fetchCount == 2 ){
                /* binding.animeDetailsScreenLoadingLayout.visibility = View.GONE */
                binding.animeDetailsScreenLoadingLayout.animate()
                    .alpha(0.0f)
                    .setDuration(300)
                    .setListener( object: Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            binding.animeDetailsScreenLoadingLayout.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                        }
                    });
            }
        })

        binding.animeBtnBack.setOnClickListener {
            Log.d("TAG_FRAGMENT", previousFragment.toString())
            Log.d("TAG_FRAGMENT", CalendarFragment().toString())
            if(previousFragment == null ){
                requireActivity().finish()
            } else{
                val mContext = context as FragmentActivity
                val transaction = mContext.supportFragmentManager.beginTransaction()
                transaction.remove(this).show(previousFragment)
                transaction.commit()
            }

        }


        // Inflate the layout for this fragment
        return binding.root
    }



    private fun initViewPager( animeData: AnimeResponse, dataCharStaff: CharacterAnimeResponse ){

        animeDetailsAdapter = AnimeDetailsViewPager( requireContext(), viewModel.tabList, animeData, dataCharStaff )
        with(binding.animeDetailsScreenViewpager){
            adapter = animeDetailsAdapter
        }
        synchronizeTabLayout()
    }

    private fun synchronizeTabLayout(){
        TabLayoutMediator(binding.animeDetailsScreenTabs, binding.animeDetailsScreenViewpager){ tab, position ->
            tab.text = viewModel.tabList[position]
        }.attach()
    }


}