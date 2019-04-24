package com.meypar.pruebalivedata.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.meypar.pruebalivedata.R
import com.meypar.pruebalivedata.view.viewmodel.TestDataState
import com.meypar.pruebalivedata.view.viewmodel.TestViewModel
import kotlinx.android.synthetic.main.fragment_mvvm.*

class FragmentMVVM : Fragment(), View.OnClickListener{
    lateinit var viewModel: TestViewModel

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMVVM()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mvvm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[TestViewModel::class.java]
        observeViewModel(viewModel)

        btnTestGetText.setOnClickListener(this)
        btnTestClean.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            btnTestGetText.id -> {onButtonTestClicked()}
            btnTestClean.id -> {viewModel.onCleanClicked()}
        }
    }

    private fun onButtonTestClicked() {
        loading.visibility = View.VISIBLE
        viewModel.onClicked()
    }

    private fun showData(data: String) {
        loading.visibility = View.GONE
        text.text = data
    }

    private fun observeViewModel(viewModel: TestViewModel) {
        // Update the list when the data changes
        viewModel.changeData().observe(this,
            Observer<TestDataState> { data ->
                if (data != null) {
                    when(data.state) {
                        TestDataState.State.showData -> {showData(data.value)}
                        TestDataState.State.cleanData -> {text.text = data.value}
                    }
                }
            })
    }
}
