package de.thm.ap.records.model

class Stats (val records: List<Record>){
    var sumCrp = setDateForSats()
    var crpToEnd = 180 - sumCrp
    var sumHalfWeighted = calculateSumHalfWeighted()
    var averageMark= 0
    var listSize = records.size


    fun setDateForSats(): Int {
        var sum = 0
        for(item in records){
            sum += item.crp!!
        }
        return sum
    }
    fun calculateSumHalfWeighted(): Int {
        var sum = 0
        for(item in records){
            sum += if(item.isHalfWeighted == true) 1 else 0
        }
        return sum
    }

    fun calculateAverageM() : Int {
        var sum = 0
        var avg  = 0
        var crp = 0
        records.let { it ->
            it.forEach {
                if (it.mark != 0) {
                    sum += (if (it.isHalfWeighted == true) ((it.crp?.let { it1 -> it.mark?.times(it1) })?.div(
                        2
                    )) else (it.crp?.let { it1 -> it.mark?.times(it1) }))!!
                    crp += (if (it.isHalfWeighted == true) (it.crp?.div(2)) else it.crp)!!
                }
            }
            avg = if(sum > 0 ) sum / crp else 0
        }
        return avg
    }
    init {
        setDateForSats()
        calculateSumHalfWeighted()
        averageMark = calculateAverageM()
    }

    override fun toString(): String {
        super.toString()

        return "Leistungen  ${listSize}\n50% Leistungen ${sumHalfWeighted}\nSumme Crp ${sumCrp}\n" +
                "Crp bis Ziel ${crpToEnd}\nDurchschnitt ${averageMark}%"
    }
}