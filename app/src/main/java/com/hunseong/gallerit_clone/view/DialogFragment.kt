package com.hunseong.gallerit_clone.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hunseong.gallerit_clone.R
import com.hunseong.gallerit_clone.databinding.FragmentDialogBinding
import com.hunseong.gallerit_clone.viewmodel.DialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    private val args: DialogFragmentArgs by navArgs()
    private val viewModel: DialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setUI()
        setButton()
        return binding.root
    }

    private fun setUI() {
        val image = args.image

        with(binding) {
            subredditTv.text = getString(R.string.subreddit_name_prefixed, image.subreddit)
            titleTv.text = image.title
        }
    }

    private fun setButton() {
        binding.saveBtn.setOnClickListener {
            viewModel.saveImage()
            Toast.makeText(context, R.string.add_to_favorite, Toast.LENGTH_SHORT).show()
            this.dismiss()
        }

        binding.rmBtn.setOnClickListener {
            viewModel.removeImage()
            Toast.makeText(context, R.string.remove_from_favorite, Toast.LENGTH_SHORT).show()
            this.dismiss()
        }
    }
}