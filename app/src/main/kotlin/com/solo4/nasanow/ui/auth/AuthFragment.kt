package com.solo4.nasanow.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.solo4.nasanow.data.base.BaseFragment
import com.solo4.nasanow.databinding.AuthFragmentBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.solo4.nasanow.R

class AuthFragment : BaseFragment(R.layout.auth_fragment) {

    override val views by viewBinding(AuthFragmentBinding::bind)
    override val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
