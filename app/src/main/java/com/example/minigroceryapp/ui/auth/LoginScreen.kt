package com.example.minigroceryapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.minigroceryapp.R
import com.example.minigroceryapp.databinding.ScreenLoginBinding

/**
 * LoginScreen: Professional login UI with 2-step verification.
 */
class LoginScreen : Fragment() {

    private var _binding: ScreenLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVerify.setOnClickListener {
            val phone = binding.etPhone.text.toString()
            
            // Logic to handle 2-step verification UI
            if (binding.llOtp.visibility == View.GONE) {
                if (phone.length == 10) {
                    binding.llOtp.visibility = View.VISIBLE
                    binding.btnVerify.text = "Login"
                } else {
                    Toast.makeText(context, "Please enter a valid phone number", Toast.LENGTH_SHORT).show()
                }
            } else {
                val otp = binding.etOtp.text.toString()
                if (otp == "1234") {
                    findNavController().navigate(R.id.action_login_to_home)
                } else {
                    Toast.makeText(context, "Incorrect OTP. Use 1234", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
