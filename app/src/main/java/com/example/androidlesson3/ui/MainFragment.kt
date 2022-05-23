package com.example.androidlesson3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson3.R
import com.example.domain.model.Balance
import com.example.domain.model.Tariff
import com.example.domain.model.User
import com.example.androidlesson3.databinding.MainFragmentBinding
import com.example.androidlesson3.findAppComponent
import com.example.androidlesson3.viewmodels.MainViewModel


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewLink: View

    private val viewModel by viewModels<MainViewModel> {
        requireContext().findAppComponent().viewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(requireActivity().layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(view)
    }


    private fun setData(view: View){
        viewLink = view
        viewModel.isLoading.observe(viewLifecycleOwner){
            binding.loading.isVisible = it
        }
        viewModel.userInfo.observe(viewLifecycleOwner) {
            setUsers(it)
        }
        viewModel.balance.observe(viewLifecycleOwner) {
            setBalance(it)
        }
        viewModel.tariffs.observe(viewLifecycleOwner) {
            setTariffs(it)
        }
    }

    private fun setBalance(balanceList: List<Balance>) {
        activity?.runOnUiThread {
            viewLink.findViewById<TextView>(R.id.personal_number).text =
                balanceList[0].id.toString()
            viewLink.findViewById<TextView>(R.id.balance).text =
                getString(R.string.money_string, balanceList[0].amount)
            viewLink.findViewById<TextView>(R.id.to_pay).text =
                getString(R.string.to_pay, "сентябрь", balanceList[0].toPay)
        }
    }

    private fun setUsers(userList: List<User>) {
        activity?.runOnUiThread {
            val recyclerViewButtons = viewLink.findViewById<RecyclerView>(R.id.buttons_list)
            val adapter = UserButtonAdapter()
            adapter.submitList(
                mutableListOf(
                    UserButton(
                        id = 1,
                        icon = R.drawable.ic_user,
                        text = userList[0].names // names
                    ),
                    UserButton(
                        id = 2,
                        icon = R.drawable.ic_home,
                        text = userList[0].address // address
                    ),
                    UserButton(
                        id = 3,
                        icon = R.drawable.ic_menu,
                        text = getString(R.string.services_list),
                    ),
                )
            )
            recyclerViewButtons.adapter = adapter
        }
    }
    private fun mapTariffToItem(tariff: Tariff) =
        ListItemTariff(
            id = tariff.id,
            name = tariff.name,
            description = tariff.description,
            amount = tariff.amount,
            onClick = { viewModel.delete(tariff) },
        )

    private fun setTariffs(tariffsList: List<Tariff>) {
        activity?.runOnUiThread {
            val recyclerViewTariff = viewLink.findViewById<RecyclerView>(R.id.tariff_list)
            val tariffAdapter = TariffAdapter()
            tariffAdapter.submitList(tariffsList.map { Tariff -> mapTariffToItem(Tariff) })
            recyclerViewTariff.adapter = tariffAdapter
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
