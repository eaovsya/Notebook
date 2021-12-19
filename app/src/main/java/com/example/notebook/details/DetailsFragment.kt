package com.example.notebook.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notebook.R
import com.example.notebook.databinding.*
import com.example.notebook.db.Contact
import com.example.notebook.main.MainActivity
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsContract.View {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivityComponent.detailsComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        initView()
        presenter.bindView(this)
        presenter.onViewCreated(requireArguments().getParcelable(DETAILS_CONTACT_EXTRA))
        return binding.root
    }

    private fun initView() {
        binding.detailsToolbarLayout.detailsToolbar.setNavigationOnClickListener {
            presenter.onBackPressed()
        }
        binding.detailsToolbarLayout.toolbarButtonEdit.setOnClickListener {
            presenter.onEditClicked(requireArguments().getParcelable(DETAILS_CONTACT_EXTRA))
        }
    }

    override fun addProfilePic(picUrl: String?) {
        Picasso.get().load(picUrl).placeholder(binding.detailsProfilePic.drawable)
            .error(R.drawable.ic_baseline_error_64)
            .fit().centerCrop()
            .into(binding.detailsProfilePic)
    }

    override fun addFullName(fullName: String) {
        binding.fullName.text = fullName
    }

    override fun addNumbers(phoneNumbers: List<String?>?) {
        if (phoneNumbers != null) {
            val phoneNumbersLayoutBinding = PhoneNumbersLayoutBinding.inflate(layoutInflater)
            phoneNumbers.forEach {
                val phoneNumberItemBinding = PhoneNumberItemBinding.inflate(
                    layoutInflater,
                    phoneNumbersLayoutBinding.phoneNumbersItemsLayout,
                    false
                )
                phoneNumberItemBinding.root.text = it
                phoneNumbersLayoutBinding.phoneNumbersItemsLayout.addView(phoneNumberItemBinding.root)
            }
            binding.detailsLayout.addView(phoneNumbersLayoutBinding.root)
        }
    }

    override fun addSocialMedia(socialMedia: List<String?>?) {
        if (socialMedia != null) {
            val socialMediaLayoutBinding = SocialMediaLayoutBinding.inflate(layoutInflater)
            socialMedia.forEach {
                val socialMediaItemBinding =
                    SocialMediaItemBinding.inflate(
                        layoutInflater,
                        socialMediaLayoutBinding.socialMediaItemsLayout,
                        false
                    )
                socialMediaItemBinding.root.text = it
                socialMediaLayoutBinding.socialMediaItemsLayout.addView(socialMediaItemBinding.root)
            }
            binding.detailsLayout.addView(socialMediaLayoutBinding.root)
        }
    }

    override fun addDescription(description: String?) {
        if (description != null) {
            val descriptionLayoutBinding = DescriptionLayoutBinding.inflate(layoutInflater)
            val descriptionItemBinding =
                DescriptionItemBinding.inflate(layoutInflater, descriptionLayoutBinding.root, false)
            descriptionItemBinding.root.text = description
            descriptionLayoutBinding.root.addView(descriptionItemBinding.root)
            binding.detailsLayout.addView(descriptionLayoutBinding.root)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unBindView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_DETAILS_TAG = "FRAGMENT_DETAILS_TAG"
        const val DETAILS_CONTACT_EXTRA = "DETAILS_CONTACT_EXTRA"

        @JvmStatic
        fun newInstance(contact: Contact) = DetailsFragment().also {
            it.arguments = Bundle().apply {
                putParcelable(DETAILS_CONTACT_EXTRA, contact)
            }
        }
    }
}