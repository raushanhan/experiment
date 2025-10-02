package ru.kpfu.itis.springpractice.experiment.presentation.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.kpfu.itis.springpractice.experiment.AdventurerApp
import ru.kpfu.itis.springpractice.experiment.R
import ru.kpfu.itis.springpractice.experiment.databinding.FragmentNoteAddingBinding
import ru.kpfu.itis.springpractice.experiment.domain.model.Note
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodel.NoteAddingViewModel
import ru.kpfu.itis.springpractice.experiment.presentation.viewmodelfactory.NoteAddingViewModelFactory
import ru.kpfu.itis.springpractice.experiment.presentation.locationManager.LocationManager
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime

class NoteAddingFragment : Fragment() {

    private val viewBinding: FragmentNoteAddingBinding by viewBinding(FragmentNoteAddingBinding::bind)

    private lateinit var pickImageLauncher: ActivityResultLauncher<String>

    private var imageFile: File? = null

    companion object {
        fun newInstance() = NoteAddingFragment()
    }


    private val viewModel: NoteAddingViewModel by viewModels {
        val app = requireActivity().application as AdventurerApp
        NoteAddingViewModelFactory(
            app.addNoteUseCase,
            app.uploadImageUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val locationManager = LocationManager(activity = requireActivity())



        with(viewBinding) {
            proceedAddingANoteButton.setOnClickListener { view ->
                val title = noteTitleEt.text.toString()
                val content = noteDescriptionEt.text.toString()
                noteTitleInputLayout.error = if (title.isEmpty()) getString(R.string.note_title_et_error_text) else null
                noteDescriptionInputLayout.error = if (content.isEmpty()) getString(R.string.note_description_et_error_text) else null
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    var imageUrl = ""
                    imageFile?.let {
                        imageUrl = it.name
                    }
                    val now = LocalDateTime.now()
                    val note = Note(
                        id = 0,
                        imageUrl = imageUrl,
                        title = title,
                        content = content,
                        createdAt = now,
                        updatedAt = now,
                        latitude = 0.0,
                        longitude = 0.0
                    )
                    println("NOTE ADDING FRAGMENT TEST TAG - note created: $note")
                    viewModel.addNote(note, locationManager, imageFile)
                    view.findNavController().navigate(
                        R.id.notes_fragment,
                        Bundle().apply {
                            putBoolean("added_new_note", true)
                        }
                    )
                }
            }
            pickImageLauncher = registerForActivityResult(
                ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {
                    addedNotePictureIv.visibility = View.VISIBLE
                    addedNotePictureIv.setImageURI(it)

                    removePhotoBtn.visibility = View.VISIBLE

                    imageFile = uriToFile(uri)
                }
            }
            addAPhotoBtn.setOnClickListener {

                if (checkPermissions()) {
                    pickImageLauncher.launch("image/*")
                }
            }
            removePhotoBtn.setOnClickListener {
                imageFile = null
                addedNotePictureIv.visibility = View.GONE
                removePhotoBtn.visibility = View.GONE
            }
        }
    }

    private fun checkPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // Android 14+
            checkAndRequest(
                arrayOf(
                    Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13
            checkAndRequest(
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            )
        } else {
            // Android 12 и ниже
            checkAndRequest(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun checkAndRequest(permissions: Array<String>): Boolean {
        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) != PackageManager.PERMISSION_GRANTED
        }
        return if (missing.isNotEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(), missing.toTypedArray(), 100)
            false
        } else {
            true
        }
    }

    private fun uriToFile(uri: Uri): File {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val file = File(requireContext().cacheDir, "note_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return file
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_note_adding, container, false)
    }
}