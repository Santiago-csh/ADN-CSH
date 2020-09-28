package com.ceiba.adn_csh.rental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ceiba.adn_csh.R
import com.ceiba.adn_csh.databinding.RentalListFragmentBinding
import com.ceiba.adn_csh.viewmodel.RentalViewModel
import com.ceiba.domain.model.Rental
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RentalListFragment : Fragment() {

    private lateinit var rentalListFragmentBinding: RentalListFragmentBinding

    @Inject
    lateinit var modelViewFactory: ViewModelProvider.Factory

    private val rentalViewModel by lazy {
        ViewModelProvider(this, modelViewFactory)[RentalViewModel::class.java]
    }

    private lateinit var rentalListAdapter: RentalListAdapter
    private var suscriptionRentalList: Disposable? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this@RentalListFragment)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rentalListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.rental_list_fragment, container, false)
        return rentalListFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rentalListAdapter = RentalListAdapter(object : RentalListAdapter.RentalDetailClickCallback {
            override fun onClick(rental: Rental) {
                val bundle = bundleOf(getString(R.string.rental) to rental)
                findNavController().navigate(R.id.actionRentalDetailFragment, bundle)
            }
        })
        rentalListFragmentBinding.recyclerRentalList.adapter = rentalListAdapter
        callRentalList()
    }

    private fun callRentalList() {
        suscriptionRentalList = rentalViewModel.rentals
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Rental>>(){
                override fun onComplete() {

                }
                override fun onNext(rentalList: List<Rental>) {
                    rentalListAdapter.replace(rentalList)
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Toast.makeText(requireActivity(), getString(R.string.something_went_wrong_try_again_later), Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onStop() {
        super.onStop()
        unsuscribeRentalList()
    }

    private fun unsuscribeRentalList() {
        if(suscriptionRentalList == null || suscriptionRentalList!!.isDisposed){
            suscriptionRentalList!!.dispose()
        }
    }
}