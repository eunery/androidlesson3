package com.example.androidlesson3.cabinet

import android.os.Bundle
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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception


class MainFragment : Fragment() {
    private lateinit var adapter: Adapter
    private lateinit var binding: MainFragmentBinding

    private val api = ApiProvider(RetrofitClient()).getApi()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
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

        MainScope().launch {
            view.findViewById<TextView>(R.id.loading).isVisible = true

            val balanceCallBack = object : Callback<List<Balance>> {
                override fun onResponse(
                    call: Call<List<Balance>>,
                    response: Response<List<Balance>>
                ) {
                    val balance = response.body()?.get(0) ?: onFailure(call, Exception())
                    val casted = balance as Balance
                    with(binding) {
                        view.findViewById<TextView>(R.id.personal_number).text = balance.id.toString()
                        view.findViewById<TextView>(R.id.balance).text = getString(R.string.money_string, balance.amount)
                        view.findViewById<TextView>(R.id.to_pay).text = getString(R.string.to_pay, "сентябрь", balance.toPay)
                    }
                }
                override fun onFailure(call: Call<List<Balance>>, t: Throwable) {
                    view.findViewById<TextView>(R.id.loading).isVisible = true
                    view.findViewById<TextView>(R.id.loading).text = "No working"
                }
            }

            val tariffsCallback = object : Callback<List<Tariff>> {
                override fun onResponse(
                    call: Call<List<Tariff>>,
                    response: Response<List<Tariff>>
                ) {
                    val recyclerViewTariff = view.findViewById<RecyclerView>(R.id.tariff_list)
                    val tariffAdapter = TariffAdapter()

                    val tariffs = response.body() ?: onFailure(call, Exception())
                    val items = (tariffs as List<Tariff>).map(::mapTariffToItem)
                    with(binding) {
                        tariffAdapter.submitList(items)
                    }
                    recyclerViewTariff.adapter = tariffAdapter
                }

                override fun onFailure(call: Call<List<Tariff>>, t: Throwable) {
                    view.findViewById<TextView>(R.id.loading).isVisible = true
                    view.findViewById<TextView>(R.id.loading).text = "No working"
                }
            }

            val userCallback = object : Callback<List<User>>{
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    val user = response.body()?.get(0) ?: onFailure(call, Exception())
                    val casted = user as User
                    val recyclerViewButtons = view.findViewById<RecyclerView>(R.id.buttons_list)
                    val adapter = UserButtonAdapter()
                    with(binding){
                        adapter.submitList(mutableListOf(
                            UserButton(
                                id = 1,
                                icon = R.drawable.ic_user,
                                text = user.names // names
                            ),
                            UserButton(
                                id = 2,
                                icon = R.drawable.ic_home,
                                text = user.address // address
                            ),
                            UserButton(
                                id = 3,
                                icon = R.drawable.ic_menu,
                                text = getString(R.string.services_list)
                            )
                        ))
                    }
                    recyclerViewButtons.adapter = adapter


                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    view.findViewById<TextView>(R.id.loading).isVisible = true
                    view.findViewById<TextView>(R.id.loading).text = "No working"
                }
            }

            api.getBalanceInfo().enqueue(balanceCallBack)
            api.getTariffInfo().enqueue(tariffsCallback)
            api.getUserInfo().enqueue(userCallback)
            delay(1000)
            view.findViewById<TextView>(R.id.loading).isVisible = false
        }

    }
    private fun mapTariffToItem(tariff: Tariff) =
        Tariff(
            id = tariff.id,
            name = tariff.name,
            description = tariff.description,
            amount = tariff.amount
        )
}
