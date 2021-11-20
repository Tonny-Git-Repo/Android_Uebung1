package de.thm.ap.records

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import de.thm.ap.records.databinding.ActivityRecordFormBinding
import de.thm.ap.records.model.Record

class RecordFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordFormBinding
    private var record = Record()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure suggestions in auto complete tet view
        val names = resources.getStringArray(R.array.module_names)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, names)


    }
}