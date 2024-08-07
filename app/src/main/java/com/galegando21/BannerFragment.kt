package com.galegando21

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.galegando21.menu.AxustesActivity
import com.galegando21.menu.ProfileActivity
import com.galegando21.menu.StatisticsRoadmapActivity
import com.galegando21.menu.SuggestedGamesActivity
import com.galegando21.menu.TendaActivity

class BannerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    fun setBannerText(text: String) {
        val bannerText = requireView().findViewById<TextView>(R.id.bannerText)
        bannerText.text = text
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivMenu = view.findViewById<ImageButton>(R.id.iv_menu)
        ivMenu.setOnClickListener { v ->
            showPopupMenu(v)
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_profile -> {
                    val intent = Intent(requireContext(), ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_statistics -> {
                    val intent = Intent(requireContext(), StatisticsRoadmapActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_tenda -> {
                    val intent = Intent(requireContext(), TendaActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_suggested_games -> {
                    val intent = Intent(requireContext(), SuggestedGamesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.action_settings -> {
                    val intent = Intent(requireContext(), AxustesActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    fun showBannerMenu() {
        val ivMenu = view?.findViewById<ImageView>(R.id.iv_menu)
        ivMenu?.visibility = View.VISIBLE
    }
}
