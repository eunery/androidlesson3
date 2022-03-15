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

        view.findViewById<TextView>(R.id.personal_number).text = "372534652";
        view.findViewById<TextView>(R.id.balance).text = getString(R.string.money_string, 625.0)
        view.findViewById<TextView>(R.id.to_pay).text = getString(R.string.to_pay, "сентябрь", 0.0)

        val recyclerViewTariff = view.findViewById<RecyclerView>(R.id.tariff_list)
        val tariffAdapter = TariffAdapter()
        tariffAdapter.submitList(mutableListOf(
            Tariff(id = 1, name = "Жаба (бесплатный тариф)", description = "Скорость до 15! Мбит/c", amount = 0.0),
            Tariff(id = 2, name = "Сосиска", description = "Скорость до 0.0001 Мбит/c", amount = 001.0)
        ))
        recyclerViewTariff.adapter = tariffAdapter

        val recyclerViewButtons = view.findViewById<RecyclerView>(R.id.buttons_list)
        val adapter = UserButtonAdapter()
        adapter.submitList(mutableListOf(
            UserButton(
                id = 1,
                icon = R.drawable.ic_user,
                text = "Путин Владимир Владимирович"
            ),
            UserButton(
                id = 2,
                icon = R.drawable.ic_home,
                text = "Кремль, да?"
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