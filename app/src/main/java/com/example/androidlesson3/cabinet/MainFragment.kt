package com.example.androidlesson3.cabinet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson3.R
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = R.layout.main_fragment
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userData = UserData()

        view.findViewById<TextView>(R.id.personal_number).text = userData.balanceData.id.toString();
        view.findViewById<TextView>(R.id.balance).text = getString(R.string.money_string, userData.balanceData.amount)
        view.findViewById<TextView>(R.id.to_pay).text = getString(R.string.to_pay, "сентябрь", userData.balanceData.toPay)

        val recyclerViewTariff = view.findViewById<RecyclerView>(R.id.tariff_list)
        val tariffAdapter = TariffAdapter()
        tariffAdapter.submitList(userData.tariffs)
        recyclerViewTariff.adapter = tariffAdapter

        val recyclerViewButtons = view.findViewById<RecyclerView>(R.id.buttons_list)
        val adapter = UserButtonAdapter()
        adapter.submitList(mutableListOf(
            UserButton(
                id = 1,
                icon = R.drawable.ic_user,
                text = userData.user.names
            ),
            UserButton(
                id = 2,
                icon = R.drawable.ic_home,
                text = userData.user.address
            ),
            UserButton(
                id = 3,
                icon = R.drawable.ic_menu,
                text = getString(R.string.services_list)
            )
        ))
        recyclerViewButtons.adapter = adapter
    }


}