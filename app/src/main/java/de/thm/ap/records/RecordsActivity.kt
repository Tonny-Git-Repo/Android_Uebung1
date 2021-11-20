package de.thm.ap.records

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import de.thm.ap.records.databinding.ActivityRecordsBinding

class RecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recordListView.emptyView = binding.recordListEmptyView

    }

    override fun onStart() {
        super.onStart()

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