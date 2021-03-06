package de.thm.ap.records.persistence

import android.annotation.SuppressLint
import android.content.Context
import de.thm.ap.records.model.Record
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class RecordDAO private constructor(private val ctx: Context) {
    private var nextId = 1
    private val records = initRecords()

    fun findAll(): List<Record> = records.toList()

    fun findById(id: Int): Record {
        var tempRecord = Record()
        for(record in records){
            if(record.id == id){
                tempRecord = record
            }
        }
        return tempRecord
    }

    /**
     * Ersetzt das übergebene [Record] Objekt mit einem bereits gespeicherten [Record] Objekt mit gleicher id.
     *
     * @param record
     * @return true = update ok, false = kein [Record] Objekt mit gleicher id im Speicher gefunden
     */
    fun update(record: Record): Boolean {

        record.id?.let {
            records.remove(findById(it))
            records.add(record)
            this.saveRecords()
            return true
        }

        return false
    }

    /**
     * Persistiert das übergebene [Record] Objekt und liefert die neue id zurück.
     * (Seiteneffekte: record.id = nextId; nextId += 1 )
     *
     * @param record
     * @return neue record id
     */
    fun persist(record: Record): Int {
        record.id = nextId
        records.add(record)
        this.saveRecords()
        return nextId+1
    }


    /**
     * Löscht das übergebene [Record] Objekt anhand der id aus dem Speicher.
     *
     * @param record
     * @return true = ok, false = kein [Record] Objekt mit gleicher id im Speicher gefunden
     */
    fun delete(record: Record): Boolean {
        record.id?.let {
            findById(it)?.let{
                records.remove(it)
                return true
            }
        }
        return false
    }

    private fun initRecords(): MutableList<Record> {
        val records = mutableListOf<Record>()
        val f = ctx.getFileStreamPath(FILE_NAME)
        if (f.exists()) {
            ctx.openFileInput(FILE_NAME).use { fin ->
                ObjectInputStream(fin).readObject().let { obj ->
                    records.addAll(obj as MutableList<Record>)
                    // init next id
                    if (records.size > 0) {
                        nextId = records.maxOf { it.id ?: 0 } + 1
                    }
                }

            }
        }
        return records;
    }

    private fun saveRecords() {
        ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            .use { ObjectOutputStream(it).writeObject(records) }
    }

    companion object {
        private const val FILE_NAME = "records.obj"

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: RecordDAO? = null

        fun get(context: Context): RecordDAO {
            return INSTANCE ?: synchronized(this) {
                RecordDAO(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
