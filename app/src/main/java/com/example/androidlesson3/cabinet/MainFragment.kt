package com.example.androidlesson3.cabinet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson3.R
import com.example.androidlesson3.data.api.ApiProvider
import com.example.androidlesson3.data.api.RetrofitClient
import com.example.androidlesson3.data.model.Balance
import com.example.androidlesson3.data.model.Tariff
import com.example.androidlesson3.data.model.User
import com.example.androidlesson3.databinding.MainFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.androidlesson3.data.api.IApi
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewLink: View

    private var progressCounter = 0

    private lateinit var bar: TextView

    private val api = ApiProvider(RetrofitClient()).getApi()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = R.layout.main_fragment
        binding = MainFragmentBinding.inflate(requireActivity().layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLink = view
        bar = viewLink.findViewById<TextView>(R.id.loading)
        CoroutineScope(Dispatchers.IO).launch {
            setUsers(api.getUserInfo())
            progressCounter++
            isQueryFinished()
        }

        CoroutineScope(Dispatchers.IO).launch {
            setBalance(api.getBalanceInfo())
            progressCounter++
            isQueryFinished()
        }

        CoroutineScope(Dispatchers.IO).launch {
            setTariffs(api.getTariffInfo())
            progressCounter++
            isQueryFinished()
        }
//            viewLink.findViewById<TextView>(R.id.loading).isVisible = true
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
                        text = getString(R.string.services_list)
                    )
                )
            )
            recyclerViewButtons.adapter = adapter
        }
    }

    private fun setTariffs(tariffsList: List<Tariff>) {
        activity?.runOnUiThread {
            val recyclerViewTariff = viewLink.findViewById<RecyclerView>(R.id.tariff_list)
            val tariffAdapter = TariffAdapter()
            tariffAdapter.submitList(tariffsList)
            recyclerViewTariff.adapter = tariffAdapter
        }
    }

    private fun isQueryFinished(){
        activity?.runOnUiThread{
            bar.isVisible = progressCounter != 3
        }

    }
}
