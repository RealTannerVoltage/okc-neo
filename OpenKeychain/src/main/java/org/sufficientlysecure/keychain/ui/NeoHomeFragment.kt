package org.sufficientlysecure.keychain.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.platform.setContent
import androidx.fragment.app.Fragment

class NeoHomeFragment : Fragment() {
    enum class HomeAction {
        KEYS,
        ENCRYPT_DECRYPT,
        APPS,
        BACKUP
    }

    interface Listener {
        fun onHomeAction(action: HomeAction)
    }

    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Listener
            ?: throw IllegalStateException("Host activity must implement NeoHomeFragment.Listener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            NeoHomeScreen { action -> listener?.onHomeAction(action) }
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
}