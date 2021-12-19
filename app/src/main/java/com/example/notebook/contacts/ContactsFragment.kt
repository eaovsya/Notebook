package com.example.notebook.contacts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notebook.R
import com.example.notebook.contacts.adapters.ContactsAdapter
import com.example.notebook.databinding.FragmentContactsBinding
import com.example.notebook.db.Contact
import com.example.notebook.main.MainActivity
import javax.inject.Inject

class ContactsFragment : Fragment(), ContactsContract.View, ContactsAdapter.OnItemClickListener,
    ContactsAdapter.OnDeleteClickListener, ContactsAdapter.OnEditClickListener {

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: ContactsPresenter

    private lateinit var sortMode: SortMode

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivityComponent.contactsComponent().create()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sortMode = SortMode.values()[requireActivity().getSharedPreferences(
            SHARED_PREF,
            Context.MODE_PRIVATE
        ).getInt(
            SORT_MODE, DEFAULT_SORT_MODE.ordinal
        )]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        initView()
        presenter.bindView(this)
        presenter.onViewCreated(
            SortMode.values()[requireActivity().getSharedPreferences(
                SHARED_PREF,
                Context.MODE_PRIVATE
            ).getInt(
                SORT_MODE, DEFAULT_SORT_MODE.ordinal
            )]
        )
        return binding.root
    }

    private fun initView() {
        binding.fab.setOnClickListener {
            presenter.onAddContact()
        }
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.custom_spinner_item,
            resources.getStringArray(R.array.sortBy)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.toolbarLayout.spinner.adapter = adapter
        binding.toolbarLayout.spinner.setSelection(sortMode.ordinal, false)
        binding.toolbarLayout.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    presenter.onSortModeChanged(SortMode.values()[binding.toolbarLayout.spinner.selectedItemId.toInt()])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    override fun showContacts(contacts: List<Contact>) {
        binding.contactsRecyclerView.adapter =
            ContactsAdapter(contacts.toMutableList(), this, this, this)
    }

    override fun onItemDeleted(position: Int) {
        (binding.contactsRecyclerView.adapter as ContactsAdapter).deleteContactListItem(position)
        presenter.checkEmpty((binding.contactsRecyclerView.adapter as ContactsAdapter).itemCount)
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(contact: Contact) {
        presenter.onItemClicked(contact)
    }

    override fun onDeleteClick(contact: Pair<Contact, Int>) {
        presenter.onDeleteClicked(contact)
    }

    override fun onEditClick(contact: Contact) {
        presenter.onEditClicked(contact)
    }

    override fun sortContactsBy(mode: SortMode) {
        val scrollPosition = binding.contactsRecyclerView.scrollState
        (binding.contactsRecyclerView.adapter as ContactsAdapter).sortBy(mode)
        binding.contactsRecyclerView.scrollToPosition(scrollPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit().putInt(
            SORT_MODE, binding.toolbarLayout.spinner.selectedItemId.toInt()
        ).apply()
        presenter.unBindView()
        _binding = null
    }

    companion object {
        const val FRAGMENT_CONTACTS_TAG = "FRAGMENT_CONTACTS_TAG"
        private const val SORT_MODE = "SORT_MODE"
        private val DEFAULT_SORT_MODE = SortMode.FIRSTNAME
        private const val SHARED_PREF = "SHARED_PREF"

        @JvmStatic
        fun newInstance() = ContactsFragment()
    }
}