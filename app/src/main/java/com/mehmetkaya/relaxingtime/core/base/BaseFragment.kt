package com.mehmetkaya.relaxingtime.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.databinding.FragmentBaseBinding
import com.mehmetkaya.utils.exts.hide
import com.mehmetkaya.utils.exts.show
import com.mehmetkaya.utils.viewbinding.viewBinding

abstract class BaseFragment : Fragment() {

    private val binding by viewBinding(FragmentBaseBinding::bind)

    @get:LayoutRes
    abstract val layoutId: Int

    // Used to bind child views
    var contentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_base, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderContent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentView = null
    }

    private fun renderContent() {
        binding.contentViewStub.layoutResource = layoutId
        contentView = binding.contentViewStub.inflate()
    }

    protected fun onProgress(loading: Boolean) {
        if (loading) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    private fun showProgress() = with(binding) {
        if (progressLayout.isVisible) return

        progressLayout.show()
    }

    private fun hideProgress() {
        binding.progressLayout.hide()
    }

    protected fun onError(exception: Exception, buttonClicked: () -> Unit = {}) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(exception.message)
            .setPositiveButton(getString(R.string.common_ok)) { _, _ -> buttonClicked() }
            .show()
    }
}
