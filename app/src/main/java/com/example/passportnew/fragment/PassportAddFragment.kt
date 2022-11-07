package com.example.passportnew.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.passportnew.R
import com.example.passportnew.databinding.FragmentPassportAddBinding
import com.example.passportnew.models.AppDatabase
import com.example.passportnew.models.RvAdapter
import com.example.passportnew.models.User
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PassportAddFragment : Fragment() {
    private lateinit var appDatabase: AppDatabase
    private lateinit var rvAdapter: RvAdapter
    private lateinit var list: ArrayList<User>
    private lateinit var uriPath: String

    private lateinit var binding:FragmentPassportAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           binding=FragmentPassportAddBinding.inflate(layoutInflater)

         uriPath=""
        binding.imageLoad.setOnClickListener {
            getImageContent.launch("image/*")

        }

        appDatabase = AppDatabase.getInstance(binding.root.context)
        var listfuqaro = appDatabase.myuserDao().getAllContact()
        var listSeriya = ArrayList<String>()
        for (contact in listfuqaro) {
            listSeriya.add(contact.seriya!!)
            listSeriya.distinct()
        }
        list = ArrayList()
        list.addAll(appDatabase.myuserDao().getAllContact())



        binding.buttonSave.setOnClickListener {
            val contact = User(
                name = binding.edtName.text.toString().trim(),
                imagePath = uriPath.trim(),
                seriya = SeriyaBer(listSeriya).trim(),
                surname = binding.edtSurname.text.toString().trim(),
                data = binding.edtData.text.toString()
            )

            appDatabase.myuserDao().addUser(contact)
            binding.edtSurname.text.clear()
            binding.edtData.text.clear()
            binding.edtName.text.clear()
            binding.imageLoad.setImageURI(null)
                findNavController().navigate(R.id.homeFragment)

            Toast.makeText(binding.root.context, "save", Toast.LENGTH_SHORT).show()

        }

//        binding.buttonSave.setOnClickListener {
//            binding.edtSurname.text.clear()
//            binding.edtData.text.clear()
//            binding.edtName.text.clear()
//            binding.imageLoad.setImageURI(null)
//        }

//        binding.buttonOtish.setOnClickListener {
//            findNavController().navigate(R.id.homeFragment)
//        }



        return binding.root
    }


    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.imageLoad.setImageURI(it)
            val inputStream = requireActivity().contentResolver.openInputStream(it)
            val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
            val file = File(requireActivity().filesDir, "$title.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            uriPath = file.absolutePath
        }

    fun SeriyaBer(listSeriya: List<String>): String {
        var seriya = ""
        var n1 = Random().nextInt(25)
        var n2 = Random().nextInt(25)
        var q = 0
        for (i in 'A'..'Z') {
            if (q == n1) {
                seriya += i
            }
            if (q == n2) {
                seriya += i

            }
            q++
        }
        for (i in 0..6) {
            seriya += Random().nextInt(10)
        }
        for (s in listSeriya) {
            if (s == seriya)
                SeriyaBer(listSeriya)
            break
        }
        return seriya
    }

}