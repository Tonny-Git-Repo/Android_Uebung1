package de.thm.ap.records

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import de.thm.ap.records.databinding.ActivityRecordFormBinding
import de.thm.ap.records.model.Record
import de.thm.ap.records.persistence.RecordDAO

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

        binding.moduleName.setAdapter(adapter)

        // configure year spinner
        binding.year.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, getYears())

    }

    private fun  getYears(): Array<out String> {
        return resources.getStringArray(R.array.years)
    }

    fun onSave(view: View) {
        // validate user input
        var isValid = true

        record.moduleNum = binding.moduleNum.text.toString().trim().ifEmpty {
            binding.moduleNum.error = getString(R.string.module_num_not_empty)
            isValid = false
            ""
        }

        record.crp = if (binding.crp.text.toString().trim().isEmpty()) {
            binding.crp.error = getString(R.string.credit_point_not_empty)
            isValid = false
            0
        } else {
            0
        }
        record.moduleName = binding.moduleName.text.toString().trim().ifEmpty {
            binding.moduleName.error = getString(R.string.module_name_not_empty)
            isValid = false
            ""
        }


        if (isValid) {
            record.year = (binding.year.selectedItem.toString()).toInt()
            record.isHalfWeighted = binding.isHalfWeighted.isChecked
            record.isSummerTerm = binding.isSummerTerm.isChecked
            record.mark = binding.mark.text.toString().toInt()
            record.crp = binding.crp.text.toString().toInt()
            if (record.id == null) {
                RecordDAO.get(this).persist(record)
            } else {
                RecordDAO.get(this).update(record)
            }
            finish()
        }
    }
}