package es.menasoft.rockpaperscissors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import es.menasoft.rockpaperscissors.databinding.CreatePlayerBinding
import es.menasoft.rockpaperscissors.rest.Player
import es.menasoft.rockpaperscissors.rest.PlayerApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CreatePlayerFragment : Fragment() {

    private var _binding: CreatePlayerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = CreatePlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCreatePlayerBack.setOnClickListener {
            findNavController().navigate(R.id.action_CreatePlayerFragment_to_PlayerManagementFragment)
        }

        binding.buttonCreatePlayerCreatePlayer.setOnClickListener {
            binding.createPlayerProgressBar.visibility=View.VISIBLE
            val build = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = build.create(PlayerApiService::class.java)
            api.createPlayer(
                Player(
                    binding.createPlayerPlayerId.text.toString(),
                    binding.createPlayerPlayerName.text.toString(),
                    binding.createPlayerPlayerSurname.text.toString()
                )
            ).enqueue(object : Callback<Player> {
                override fun onResponse(call: Call<Player>, response: Response<Player>) {
                    binding.createPlayerProgressBar.visibility=View.GONE
                    Snackbar.make(view, "Player created: ${response.body()?.id}", LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Player>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}