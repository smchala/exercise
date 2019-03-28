package com.otssso.samimchala.flick.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.otssso.samimchala.flick.R
import com.otssso.samimchala.flick.databinding.SaleFragmentBinding
import com.otssso.samimchala.flick.models.Day
import com.otssso.samimchala.flick.models.TicketsExtra
import com.otssso.samimchala.flick.models.TicketsType
import com.otssso.samimchala.flick.utils.formatPrice
import com.otssso.samimchala.flick.viewModels.SaleViewModel
import com.otssso.samimchala.flick.viewModels.SharedViewModel.Companion.selectedMovie

class SaleFragment : Fragment() {

    private lateinit var saleViewModel: SaleViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saleViewModel = ViewModelProviders.of(this).get(SaleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val saleFragmentBinding: SaleFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.sale_fragment, container, false)
        val view = saleFragmentBinding.root
        saleFragmentBinding.setLifecycleOwner(this)
        saleFragmentBinding.viewModel = saleViewModel

        navController = findNavController(this)

        createRadioButtons(view)
        setRadioButtonsListeners(saleFragmentBinding)

//        hide keyboard and force user to use buttons, no need to reduce real estate when we have increase and decrease functions
        view.findViewById<EditText>(R.id.textView2).showSoftInputOnFocus = false

        handlePayment()
        handleErrors()

        return view
    }

    private fun handlePayment() {
        saleViewModel.makePaymentEvent.observe(this, Observer {
            Toast.makeText(context, getString(R.string.payment_success), Toast.LENGTH_SHORT).show()
            //should also clear/reset ui (radio buttons, quantity...)
        })
    }

    private fun handleErrors() {
        saleViewModel.errorEvent.observe(this, Observer {
            when (it) {
                3333 -> Toast.makeText(context, getString(R.string.validation_error_3333), Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(context, getString(R.string.validation_error_unknown), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createRadioButtons(view: View) {
        createTicketsTypeRadioButtons(view, saleViewModel.tickets.ticketsType)
        createTicketsExtraRadioButtons(view, saleViewModel.tickets.ticketsExtra)
        selectedMovie.value?.let {
            createMoviesDatesAndDiscountsRadioButtons(view, saleViewModel.tickets.movies[it].days)
        }
    }

    private fun setRadioButtonsListeners(saleFragmentBinding: SaleFragmentBinding) {
        saleFragmentBinding.radiogroupTicketsType.setOnCheckedChangeListener { _, checkedId ->
            saleViewModel.addTicket(checkedId)
        }
        saleFragmentBinding.radiogroupTicketsExtras.setOnCheckedChangeListener { _, checkedId ->
            saleViewModel.addExtra(checkedId)
        }
        saleFragmentBinding.radioGroupDays.setOnCheckedChangeListener { _, checkedId ->
            saleViewModel.addSelectedDay(checkedId)
        }
    }


    //don't like this here as it should be in the viewModel, but I decided to create radio buttons dynamically
    private fun disableConcessionPG15(
        ticket: Array<TicketsType>,
        i: Int,
        rd: RadioButton
    ) {
        if (!saleViewModel.isAllowed(ticket[i].name)) {
            rd.isEnabled = false
            val concessionMessage = rd.text.toString() + getString(R.string.pg)
            rd.text = concessionMessage
        }
    }

    // this will create radio buttons dynamically for the ticket types
    private fun createTicketsTypeRadioButtons(view: View, ticket: Array<TicketsType>) {
        val rg = view.findViewById<RadioGroup>(R.id.radiogroup_tickets_type)
        for (i in 0 until ticket.size) {
            //assumed that concession are for a person younger than 15 years old
            val rd = RadioButton(context)
            val radioButtonText = "${ticket[i].name}   ->   ${ticket[i].price.formatPrice(saleViewModel.tickets.currency)}"
            rd.text =radioButtonText
            rd.id = i //cheating as I want to map the id to the actual type I'm not using the view.generateId()
            if (i == 0) {
                //to avoid doing validation when going forward with the purchase
                rd.isChecked = true
            }
            //if the movie is PG 15, that movie ticket will only be available as a standard ticket, concessions are not allowed
            disableConcessionPG15(ticket, i, rd)
            rg?.addView(rd)
        }
    }

    /*
    ideally I would create an abstract class which TicketsType and TicketsExtra inherit from so we can have only one of these functions
    or even better just use a recyclerview for the tickets, extras and days
     */
    private fun createTicketsExtraRadioButtons(view: View, ticket: Array<TicketsExtra>) {
        val rg = view.findViewById<RadioGroup>(R.id.radiogroup_tickets_extras)
        for (i in 0 until ticket.size) {
            val rd = RadioButton(context)
            val radioButtonText = "${ticket[i].name}   ->   ${ticket[i].price.formatPrice(saleViewModel.tickets.currency)}"
            rd.text =radioButtonText
            rd.id = i //cheating as I want to map the id to the actual extra, I'm not using the view.gerateId()
            rg?.addView(rd)
        }
    }

    private fun createMoviesDatesAndDiscountsRadioButtons(view: View, days: Array<Day>) {
        val rg = view.findViewById<RadioGroup>(R.id.radio_group_days)
        for (i in 0 until days.size) {
            val rd = RadioButton(context)
            val radioButtonText = "${days[i].day} ${saleViewModel.addOffer(days[i])}"
            rd.text = radioButtonText
            rd.id = i //cheating as I want to map the id to the actual day, I'm not using the view.generateId()
            rg?.addView(rd)
        }
    }
}