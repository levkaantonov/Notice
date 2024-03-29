package levkaantonov.com.study.notice.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import levkaantonov.com.study.notice.R
import levkaantonov.com.study.notice.databinding.FragmentMainBinding
import levkaantonov.com.study.notice.models.AppNotice
import levkaantonov.com.study.notice.utils.APP_ACTIVITY
import levkaantonov.com.study.notice.utils.AppPreference

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    private lateinit var mObserverList: Observer<List<AppNotice>>

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        mViewModel.allNotices.observe(this, mObserverList)
        mBinding.btnAddNotice.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNoticeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mViewModel.allNotices.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                mViewModel.signOut()
                AppPreference.setInitUser(false)
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        fun click(notice: AppNotice) {
            val bundle = Bundle()
            bundle.putSerializable("notice", notice)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noticeFragment, bundle)
        }
    }
}