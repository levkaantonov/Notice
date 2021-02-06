package levkaantonov.com.study.notice.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_start.*
import levkaantonov.com.study.notice.R
import levkaantonov.com.study.notice.databinding.FragmentStartBinding
import levkaantonov.com.study.notice.utils.TYPE_ROOM

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: StartFragmentVewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this).get(StartFragmentVewModel::class.java)
        start_btn_room.setOnClickListener {
            mViewModel.initDb(TYPE_ROOM)
        }

    }
}