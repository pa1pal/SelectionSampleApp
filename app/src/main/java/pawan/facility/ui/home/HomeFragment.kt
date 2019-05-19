package pawan.facility.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import pawan.facility.R
import pawan.facility.data.DataManager
import pawan.facility.data.HomeDatabase
import pawan.facility.data.model.Facility
import pawan.facility.data.model.Option
import pawan.facility.job.GetDataFromApiJob

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var dataManager: DataManager
    private lateinit var homeFragmentAdapter: HomeFragmentAdapter
    private lateinit var facList: MutableList<Facility>
    private lateinit var homeDatabase: HomeDatabase

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataManager = DataManager(activity?.applicationContext)
        facList = ArrayList<Facility>()
        homeDatabase = HomeDatabase.getDatabase(activity!!.applicationContext)!!
        homeFragmentAdapter = HomeFragmentAdapter(facList, context)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GetDataFromApiJob.startJobNow()
        GetDataFromApiJob.scheduleJob()
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setUpRecyclerView()
        viewModel.facilityData.observe(viewLifecycleOwner, object : Observer<List<Facility>> {
            override fun onChanged(fac: List<Facility>?) {

                var list: MutableList<Facility>? = ArrayList<Facility>()
                for (facility in fac!!.listIterator()) {
                    var optList: MutableList<Option>? = ArrayList<Option>()
                    for (opt in homeDatabase.optionDao().getOptionByFacilityId(facility.facilityId).listIterator()) {
                        optList?.add(opt)
                    }
                    facility.options = optList
                    list?.add(facility)
                }
                homeFragmentAdapter = HomeFragmentAdapter(list, context)
                recyclerview.adapter = homeFragmentAdapter
                homeFragmentAdapter.notifyDataSetChanged()
            }
        })

    }

    private fun setUpRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = homeFragmentAdapter
    }
}
