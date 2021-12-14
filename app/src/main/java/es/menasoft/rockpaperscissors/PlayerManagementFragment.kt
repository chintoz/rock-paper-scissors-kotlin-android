package es.menasoft.rockpaperscissors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import es.menasoft.rockpaperscissors.databinding.PlayerManagementBinding

class PlayerManagementFragment : Fragment() {

    private var _binding: PlayerManagementBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = PlayerManagementBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCreatePlayer.setOnClickListener {
            findNavController().navigate(R.id.action_PlayerManagementFragment_to_CreatePlayerFragment)
        }

    }
}