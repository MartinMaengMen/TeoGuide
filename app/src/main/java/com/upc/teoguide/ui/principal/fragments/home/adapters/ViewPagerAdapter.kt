package com.upc.teoguide.ui.principal.fragments.home.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.upc.teoguide.data.entities.CentroHistorico
import com.upc.teoguide.ui.principal.fragments.home.CommentsFragment
import com.upc.teoguide.ui.principal.fragments.home.ImagesFragment
import com.upc.teoguide.ui.principal.fragments.home.InformationFragment

class ViewPagerAdapter(fragment: Fragment, private val centro: CentroHistorico) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val fragment = InformationFragment()
                fragment.arguments = Bundle().apply {
                    putString("arg1", centro.descripcion)
                }
                return fragment
            }
            1 -> {
                val fragment = CommentsFragment()
                fragment.arguments = Bundle().apply {
                    putInt("arg1", centro.id)
                }
                return fragment
            }
            else -> ImagesFragment()
        }
    }

    /*private val fragmentTitleList = mutableListOf("Información", "Comentarios", "Imágenes")

    override fun getCount(): Int {
        return fragmentCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> return InformationFragment()
            1 -> return ImagesFragment()
            2 -> return CommentsFragment()
            else -> return InformationFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }*/


}