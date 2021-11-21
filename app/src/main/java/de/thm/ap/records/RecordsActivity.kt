package de.thm.ap.records

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import de.thm.ap.records.databinding.ActivityRecordsBinding
import de.thm.ap.records.model.Record
import de.thm.ap.records.model.Stats
import de.thm.ap.records.persistence.RecordDAO

class RecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordsBinding
    private var records =  listOf(Record())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordListView.emptyView = binding.recordListEmptyView

    }

    override fun onStart() {
        super.onStart()
        records = RecordDAO.get(this).findAll()

        binding.recordListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, records)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_add -> {
                val i = Intent(this, RecordFormActivity::class.java)
                startActivity(i)
                true
            }

            R.id.action_stats -> {
                AlertDialog.Builder(this)
                    .setTitle(R.string.stats)
                    .setMessage("${Stats(records)}")
                    .setNeutralButton(R.string.close, null)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.record, menu)
        return true
    }

    fun onSave(view: android.view.View) {}

}