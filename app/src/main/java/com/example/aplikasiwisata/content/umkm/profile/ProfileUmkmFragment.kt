package com.example.aplikasiwisata.content.umkm.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.model.Profile
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile_umkm.*


class ProfileUmkmFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_umkm, container, false)
    }

    private fun setUpProfile(umkm: Umkm) {
        val adapter = UmkmProfileAdapter()
        adapter.setLayout(R.layout.profile_umkm_item_layoout)
        val profiles: MutableList<Profile> = mutableListOf()

        profiles.add(Profile("Nama UMKM", umkm.umkmName))
        profiles.add(Profile("Nama Pemilik", umkm.owner))
        profiles.add(Profile("Nomor Handphone", umkm.phone))
        profiles.add(Profile("Email", umkm.email))
        profiles.add(Profile("Website", umkm.website))
        profiles.add(Profile("Alamat", umkm.address))
        profiles.add(Profile("Kode Pos", umkm.postalCode))
        profiles.add(Profile("Kelurahan", umkm.kelurahan))
        profiles.add(Profile("Kecamatan", umkm.kecamatan))
        profiles.add(Profile("Kabupaten", umkm.kabupaten))
        profiles.add(Profile("RT/RW", umkm.rt + "/" + umkm.rw))
        adapter.setDataList(profiles)
        profile_rv.adapter = adapter
        profile_rv.layoutManager = LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = getExtraData<Umkm>("data")
        setUpProfile(data)
    }

}
